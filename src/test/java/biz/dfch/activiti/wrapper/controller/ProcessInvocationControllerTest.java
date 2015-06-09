/**
 * Copyright 2015 d-fens GmbH
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package biz.dfch.activiti.wrapper.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ProcessInvocationControllerTest {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new ProcessInvocationController()).build();
    }

    @Test
    public void invokeProcessWithValidPayloadReturnsHttpStatusOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/process-invocation")
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "    \"assetId\": \"123\"," +
                        "    \"assetType\": \"System\"," +
                        "    \"action\": \"create\"," +
                        "    \"decisionId\": \"1234\"," +
                        "    \"userId\": \"12345\"," +
                        "    \"tenantId\": \"123456\"," +
                        "    \"type\": \"PRE-ACTION\"," +
                        "    \"bpeURI\": \"http://localhost\"" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    public void invokeProcessWithInValidPayloadReturnsHttpStatusBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/process-invocation")
                .accept(MediaType.APPLICATION_JSON)
                .content("{" +
                        "    \"assetId\": \"\"," +
                        "    \"assetType\": \"System\"," +
                        "    \"action\": \"create\"," +
                        "    \"decisionId\": \"1234\"," +
                        "    \"userId\": \"12345\"," +
                        "    \"tenantId\": \"123456\"," +
                        "    \"type\": \"PRE-ACTION\"," +
                        "    \"bpeURI\": \"http://localhost\"" +
                        "}"))
                .andExpect(status().isBadRequest());
    }
}
