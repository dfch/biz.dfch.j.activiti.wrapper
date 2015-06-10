/**
 * Copyright 2015 d-fens GmbH
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package biz.dfch.activiti.wrapper.service;

import biz.dfch.activiti.wrapper.converter.ProcessMetadataConverter;
import biz.dfch.activiti.wrapper.domain.ActivitiProcessMetadata;
import biz.dfch.activiti.wrapper.domain.ProcessMetadata;
import biz.dfch.activiti.wrapper.exception.ActivityException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ActivitiService {

    private static final Logger LOG = Logger.getLogger(ActivitiService.class);

    @Value("${activiti.uri}")
    private String activitiUri;

    @Value("${activiti.user}")
    private String activitiUser;

    @Value("${activiti.password}")
    private String activitiPassword;

    @Autowired
    private ObjectMapper objectMapper;

    public void invokeProcess(ProcessMetadata processMetadata) {

        LOG.info("Sending request to start process at activity server '" + activitiUri + "'");

        try {
            String response = Request
                    .Post(getRequestUri())
                    .setHeader("Authorization", createBasicAuth())
                    .bodyString(objectMapper.writeValueAsString(createBody(processMetadata)), ContentType.APPLICATION_JSON)
                    .execute()
                    .returnContent()
                    .asString();
            LOG.info("Got response from activiti engine: " + objectMapper.writeValueAsString(response));
        } catch (IOException e) {
            throw new ActivityException("IOException while sending request to Activiti", e);
        }
    }

    private ActivitiProcessMetadata createBody(ProcessMetadata processMetadata) {
        ActivitiProcessMetadata activitiProcessMetadata = new ActivitiProcessMetadata();
        activitiProcessMetadata.setProcessDefinitionKey(createProcessDefinitionKey(processMetadata));
        activitiProcessMetadata.setBusinessKey("");
        activitiProcessMetadata.setTenantId(processMetadata.getTenantId());
        activitiProcessMetadata.setVariables(ProcessMetadataConverter.convertToProcessVariables(processMetadata));
        return activitiProcessMetadata;
    }

    private String createProcessDefinitionKey(ProcessMetadata processMetadata) {
        return String.format("%s.%s.%s",
                processMetadata.getAssetType(),
                processMetadata.getAction(),
                processMetadata.getType());
    }

    private URI getRequestUri() {
        try {
            if (activitiUri.endsWith("/")) {
                return new URI(activitiUri + "runtime/process-instances");
            } else {
                return new URI(activitiUri + "/runtime/process-instances");
            }
        } catch (URISyntaxException e) {
            throw new ActivityException("URI-conversion failed (activitiUri: " + activitiUri + ")", e);
        }
    }

    private String createBasicAuth() {
        String authToken = new String(Base64.encodeBase64(String.format("%s:%s", activitiUser, activitiPassword).getBytes()));
        return String.format("Basic %s", authToken);
    }
}
