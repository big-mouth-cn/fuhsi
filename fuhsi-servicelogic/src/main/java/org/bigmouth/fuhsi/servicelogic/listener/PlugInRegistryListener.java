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
package org.bigmouth.fuhsi.servicelogic.listener;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bigmouth.nvwa.cluster.node.IDataDetector;
import org.bigmouth.nvwa.cluster.node.Wrapper;
import org.bigmouth.nvwa.dpl.event.ActivedEvent;
import org.bigmouth.nvwa.dpl.event.ConfigChangedEvent;
import org.bigmouth.nvwa.dpl.event.CreatedEvent;
import org.bigmouth.nvwa.dpl.event.DeActivedEvent;
import org.bigmouth.nvwa.dpl.event.DestroyedEvent;
import org.bigmouth.nvwa.dpl.event.StatusChangedEvent;
import org.bigmouth.nvwa.dpl.event.listener.PlugInListenerAdapter;
import org.bigmouth.nvwa.dpl.plugin.PlugIn;
import org.bigmouth.nvwa.dpl.service.Service;
import org.bigmouth.nvwa.sap.namecode.DefaultNameCodePair;

import com.alibaba.fastjson.JSONArray;


/**
 * 服务自动注册
 * 
 * @see org.bigmouth.nvwa.sap.namecode.NameCodePair
 * @see org.bigmouth.nvwa.sap.namecode.DefaultNameCodePair
 * @since 1.0
 * @author Allen Hu - (big-mouth.cn)
 */
public class PlugInRegistryListener extends PlugInListenerAdapter {

    private final IDataDetector dataDetector;
    
    public PlugInRegistryListener(IDataDetector dataDetector) {
        this.dataDetector = dataDetector;
    }

    @Override
    public void init() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void onCreated(CreatedEvent event) {
        PlugIn plugIn = (PlugIn) event.getSource();
        List<DefaultNameCodePair> pairs = getNameCodePairs();
        addNameCodePair(pairs, plugIn);
        setNameCodePairs(pairs);
    }

    @Override
    public void onDestroyed(DestroyedEvent event) {
        PlugIn plugIn = (PlugIn) event.getSource();
        List<DefaultNameCodePair> pairs = getNameCodePairs();
        remoteNameCodePair(pairs, plugIn);
        setNameCodePairs(pairs);
    }

    @Override
    public void onActived(ActivedEvent event) {
    }

    @Override
    public void onDeActived(DeActivedEvent event) {
    }

    @Override
    public void onStatusChanged(StatusChangedEvent event) {
    }

    @Override
    public void onConfigChanged(ConfigChangedEvent event) {
    }

    private List<DefaultNameCodePair> getNameCodePairs() {
        Wrapper wrapper = getWrapper();
        String watchPath = getWatchPath();
        
        byte[] data = wrapper.getData(watchPath);
        if (null == data || data.length == 0) {
            throw new RuntimeException("Path " + watchPath + " has empty!");
        }
        
        List<DefaultNameCodePair> pairs = JSONArray.parseArray(new String(data), DefaultNameCodePair.class);
        return pairs;
    }
    
    private void addNameCodePair(List<DefaultNameCodePair> pairs, PlugIn plugIn) {
        String cname = plugIn.getKey();
        
        // Find PlugIn-Code & Last Service-Code
        boolean exists = false;
        short plugInCode = 1, serviceCode = 0;
        for (DefaultNameCodePair defaultNameCodePair : pairs) {
            String pname = defaultNameCodePair.getPlugInName();
            if (StringUtils.equals(pname, cname)) {
                exists = true;
                plugInCode = defaultNameCodePair.getPlugInCode();
                serviceCode = defaultNameCodePair.getServiceCode();
            }
            if (! exists) {
                plugInCode = defaultNameCodePair.getPlugInCode(); 
            }
        }
        if (! exists) {
            ++plugInCode;
        }
        
        Iterator<Service> services = plugIn.getAllServices();
        while (services.hasNext()) {
            Service service = services.next();
            String scode = service.getKey();
            
            DefaultNameCodePair pair = new DefaultNameCodePair();
            pair.setPlugInName(cname);
            pair.setPlugInCode(plugInCode);
            pair.setServiceName(scode);
            pair.setServiceCode(serviceCode++);
            pairs.add(pair);
        }
    }
    
    private void remoteNameCodePair(List<DefaultNameCodePair> pairs, PlugIn plugIn) {
        Iterator<DefaultNameCodePair> iterator = pairs.iterator();
        while (iterator.hasNext()) {
            DefaultNameCodePair pair = iterator.next();
            if (StringUtils.equals(pair.getPlugInName(), plugIn.getKey())) {
                iterator.remove();
            }
        }
    }
    
    private void setNameCodePairs(List<DefaultNameCodePair> pairs) {
        Wrapper wrapper = getWrapper();
        String watchPath = getWatchPath();
        
        String json = JSONArray.toJSONString(pairs);
        wrapper.setData(watchPath, json.getBytes());
    }

    private String getWatchPath() {
        String watchPath = dataDetector.getWatchPath();
        return watchPath;
    }

    private Wrapper getWrapper() {
        Wrapper wrapper = dataDetector.getWrapper();
        return wrapper;
    }
}
