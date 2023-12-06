package com.example.InventoryMicroService.feignClient;
import com.example.InventoryMicroService.dto.Product;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(name = "productService", url = "http://10.20.3.178:8081")
@EnableFeignClients
public interface ProductServiceFeign {

    @RequestMapping(method = RequestMethod.GET,value = "/product/get-product-by-id/{productId}")
     Product getProductById(@PathVariable("productId")String productId);

}
