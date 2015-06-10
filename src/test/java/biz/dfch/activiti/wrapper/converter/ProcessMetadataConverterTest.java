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
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProcessMetadataConverterTest {

    public static final String SAMPLE_ACTION = "sampleAction";
    public static final String SAMPLE_ASSET_ID = "sampleAssetId";
    public static final String SAMPLE_ASSET_TYPE = "sampleAssetType";
    public static final String SAMPLE_BPE_URI = "sampleBpeURI";
    public static final String SAMPLE_DECISION_ID = "sampleDecisionId";
    public static final String SAMPLE_TENANT_ID = "sampleTenantId";
    public static final String SAMPLE_TYPE = "sampleType";
    public static final String SAMPLE_USER_ID = "sampleUserId";

    @Test
    public void covertToVariablesReturnsProcessMetadataAsProcessVariableList() {
        ProcessMetadata processMetadata = new ProcessMetadata();
        processMetadata.setAction(SAMPLE_ACTION);
        processMetadata.setAssetId(SAMPLE_ASSET_ID);
        processMetadata.setAssetType(SAMPLE_ASSET_TYPE);
        processMetadata.setBpeURI(SAMPLE_BPE_URI);
        processMetadata.setDecisionId(SAMPLE_DECISION_ID);
        processMetadata.setTenantId(SAMPLE_TENANT_ID);
        processMetadata.setType(SAMPLE_TYPE);
        processMetadata.setUserId(SAMPLE_USER_ID);

        List<ProcessVariable> processVariables = ProcessMetadataConverter.convertToProcessVariables(processMetadata);
        assertEquals(8, processVariables.size());
        assertEquals(SAMPLE_ASSET_ID, processVariables.get(0).getValue());
        assertEquals(SAMPLE_ASSET_TYPE, processVariables.get(1).getValue());
        assertEquals(SAMPLE_ACTION, processVariables.get(2).getValue());
        assertEquals(SAMPLE_DECISION_ID, processVariables.get(3).getValue());
        assertEquals(SAMPLE_USER_ID, processVariables.get(4).getValue());
        assertEquals(SAMPLE_TENANT_ID, processVariables.get(5).getValue());
        assertEquals(SAMPLE_TYPE, processVariables.get(6).getValue());
        assertEquals(SAMPLE_BPE_URI, processVariables.get(7).getValue());
    }
}
