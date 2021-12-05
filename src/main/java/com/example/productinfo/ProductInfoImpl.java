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


import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author lixiaoshuang
 */
public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {
    
    private Map<String, ProductInfoOuterClass.Product> prductMap = new HashMap();
    
    /**
     * 添加商品
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void addProduct(ProductInfoOuterClass.Product request,
            StreamObserver<ProductInfoOuterClass.ProductId> responseObserver) {
        System.out.println("调用添加商品--------》port: 50051");
        UUID uuid = UUID.randomUUID();
        String randuomId = uuid.toString();
        prductMap.put(randuomId, request);
        
        ProductInfoOuterClass.ProductId build = ProductInfoOuterClass.ProductId.newBuilder().setValue(randuomId)
                .build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }
    
    /**
     * 获取商品
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void getProduct(ProductInfoOuterClass.ProductId request,
            StreamObserver<ProductInfoOuterClass.Product> responseObserver) {
        ProductInfoOuterClass.Product product = prductMap.get(request.getValue());
        if (null == product) {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }
        responseObserver.onNext(product);
        responseObserver.onCompleted();
    }
}
