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

package biz.dfch.activiti.wrapper.controller;

import biz.dfch.activiti.wrapper.domain.ProcessMetadata;
import biz.dfch.activiti.wrapper.service.ActivitiService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/process-invocation")
public class ProcessInvocationController {

    public static final Logger LOG = Logger.getLogger(ProcessInvocationController.class);

    @Autowired
    private ActivitiService activitiService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> invokeProcess(@Valid @RequestBody ProcessMetadata processMetadata) {
        LOG.info("Got invoke process request");
        activitiService.invokeProcess(processMetadata);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
