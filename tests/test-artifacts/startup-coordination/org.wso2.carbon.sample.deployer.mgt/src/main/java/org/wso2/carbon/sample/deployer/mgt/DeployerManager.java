/*
 *  Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.carbon.sample.deployer.mgt;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import java.util.ArrayList;
import java.util.List;

/**
 * Sample Deployer Manager Service Component class.
 *
 * @since 5.0.0
 */
@Component(
        name = "org.wso2.carbon.sample.deployer.mgt.DeployerManager",
        immediate = true
)
public class DeployerManager {

    private static List<Deployer> deployerList = new ArrayList<>();

    @Activate
    public void activate(BundleContext bundleContext) {
    }

    @Deactivate
    public void deactivate(BundleContext bundleContext) {

    }

    @Reference(
            name = "sample.deployer.service.reference",
            service = Deployer.class,
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            unbind = "deregisterDeployer"
    )
    public void registerDeployer(Deployer deployer) {
        deployerList.add(deployer);
    }

    public void deregisterDeployer(Deployer deployer) {
        deployerList.remove(deployer);
    }

    public int getDeployerCount() {
        return deployerList.size();
    }

}
