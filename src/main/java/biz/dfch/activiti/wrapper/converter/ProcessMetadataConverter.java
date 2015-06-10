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

package biz.dfch.activiti.wrapper.converter;

import biz.dfch.activiti.wrapper.domain.ProcessMetadata;
import biz.dfch.activiti.wrapper.domain.ProcessVariable;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProcessMetadataConverter {

    private static final Logger LOG = Logger.getLogger(ProcessMetadataConverter.class);

    public static List<ProcessVariable> convertToProcessVariables(ProcessMetadata processMetadata) {
        LOG.debug("Process metadata conversion started");
        List<ProcessVariable> processVariables = new ArrayList<>();
        processVariables.add(new ProcessVariable("assetId", processMetadata.getAssetId()));
        processVariables.add(new ProcessVariable("assetType", processMetadata.getAssetType()));
        processVariables.add(new ProcessVariable("action", processMetadata.getAction()));
        processVariables.add(new ProcessVariable("decisionId", processMetadata.getDecisionId()));
        processVariables.add(new ProcessVariable("userId", processMetadata.getUserId()));
        processVariables.add(new ProcessVariable("tenantId", processMetadata.getTenantId()));
        processVariables.add(new ProcessVariable("type", processMetadata.getType()));
        processVariables.add(new ProcessVariable("bpeURI", processMetadata.getBpeURI()));
        LOG.debug("Process metadata conversion finished");
        return processVariables;
    }
}
