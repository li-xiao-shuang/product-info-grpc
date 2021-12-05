/*
 * Copyright 2021 Gypsophila open source organization.
 *
 * Licensed under the Apache License,Version2.0(the"License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.productinfo;

import com.google.common.collect.Lists;
import com.tencent.polaris.api.core.ProviderAPI;
import com.tencent.polaris.factory.api.DiscoveryAPIFactory;
import com.tencent.polaris.grpc.server.ServerAgent;
import io.grpc.BindableService;

import java.util.List;

/**
 * @author lixiaoshuang
 */
public class ProductServer {
    
    private static final ProviderAPI providerAPI = DiscoveryAPIFactory.createProviderAPI();
    
    
    public static void main(String[] args) {
        List<BindableService> services = Lists.newArrayList(new ProductInfoImpl());
        ServerAgent serverAgent = new ServerAgent(50051, "productService", services, providerAPI);
        serverAgent.start();
        providerAPI.destroy();
    }
}
