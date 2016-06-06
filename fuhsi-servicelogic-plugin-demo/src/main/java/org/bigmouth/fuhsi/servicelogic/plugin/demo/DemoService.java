/*
 * Copyright 2016 mopote.com
 *
 * The Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.bigmouth.fuhsi.servicelogic.plugin.demo;

import org.bigmouth.fuhsi.servicelogic.factory.annotation.TransactionService;
import org.bigmouth.fuhsi.servicelogic.handler.CommonBizCode;
import org.bigmouth.fuhsi.servicelogic.handler.ResourceNotFoundException;
import org.bigmouth.fuhsi.servicelogic.handler.TransactionException;
import org.bigmouth.fuhsi.servicelogic.handler.TransactionHandler;
import org.bigmouth.fuhsi.servicelogic.interceptor.SessionAware;
import org.bigmouth.nvwa.dpl.factory.annotation.PlugIn;
import org.bigmouth.nvwa.session.Session;
import org.bigmouth.nvwa.session.annotation.SessionSupport;

@SessionSupport
@PlugIn(name = "demo", code = "demo")
@TransactionService(name = "demo", code = "demo")
public class DemoService implements TransactionHandler<DemoRequest, DemoResponse>, SessionAware {

    private Session session;
    
    @Override
    public void handle(DemoRequest requestModel, DemoResponse responseModel) throws ResourceNotFoundException,
            TransactionException {
        try {
            String name = requestModel.getName();
            responseModel.setEcho(session.getId() + ", Hello " + name);
        }
        catch (Exception e) {
            responseModel.setDesc(e.getMessage());
            responseModel.setBizCode(CommonBizCode.FAIL);
        }
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
