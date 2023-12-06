package com.example.InventoryMicroService.feignClient;

import com.example.InventoryMicroService.dto.Product;
import feign.hystrix.FallbackFactory;


public class ProductServiceFeignImpl implements FallbackFactory<ProductServiceFeign> {

    @Override
    public ProductServiceFeign create(Throwable throwable) {
        return new ProductServiceFeign() {
            @Override
            public Product getProductById(String productId) {
                return new Product();
            }

        };
    }
}
