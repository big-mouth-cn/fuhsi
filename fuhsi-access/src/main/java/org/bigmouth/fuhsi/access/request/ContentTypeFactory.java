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
package org.bigmouth.fuhsi.access.request;

import org.apache.commons.lang.StringUtils;
import org.bigmouth.nvwa.sap.ContentType;


/**
 * ContentType factory
 * 
 * @since 1.0
 * @author Allen Hu - (big-mouth.cn)
 */
public final class ContentTypeFactory {

    public static final String APPLICATION_JSON = "application/json";
    
    private ContentTypeFactory() {
    }
    
    public static ContentType build(String contentType) {
        if (StringUtils.isNotBlank(contentType)) {
            contentType = contentType.toLowerCase();
            if (contentType.startsWith(APPLICATION_JSON))
                return ContentType.JSON;
        }
        return ContentType.TLV;
    }
}
