package com.example.InventoryMicroService.feignClient;

import com.example.InventoryMicroService.dto.Product;
import feign.hystrix.FallbackFactory;

import java.util.Arrays;
import java.util.List;

public class ProductServiceFeignImpl implements FallbackFactory<ProductServiceFeign> {

    @Override
    public ProductServiceFeign create(Throwable throwable) {
        return new ProductServiceFeign() {
            @Override
            public List<Product> getAll() {
                return Arrays.asList(new Product());
            }

        };
    }
}
