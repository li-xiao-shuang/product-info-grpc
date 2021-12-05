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

package client;

import com.example.productinfo.ProductInfoGrpc;
import com.example.productinfo.ProductInfoOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @author lixiaoshuang
 */
public class ProductInfoClient {
    
    public static void main(String[] args) {
        // 添加产品
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
        ProductInfoGrpc.ProductInfoBlockingStub productInfoBlockingStub = ProductInfoGrpc.newBlockingStub(channel);
        
        ProductInfoOuterClass.Product product = ProductInfoOuterClass.Product.newBuilder().setName("苹果13")
                .setDescription("超强性能").build();
        ProductInfoOuterClass.ProductId productId = productInfoBlockingStub.addProduct(product);
        System.out.println("添加商品成功: " + productId);
        
        // 获取产品
        
        ProductInfoOuterClass.Product product1 = productInfoBlockingStub.getProduct(productId);
        System.out.println("获取产品：" + product1.getName() + " " + product1.getDescription());
    }
}
