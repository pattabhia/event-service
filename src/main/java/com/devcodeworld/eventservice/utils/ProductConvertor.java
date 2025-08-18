package com.devcodeworld.eventservice.utils;

import com.devcodeworld.eventservice.dto.ProductDTO;
import com.devcodeworld.eventservice.entity.Product;
import org.springframework.beans.BeanUtils;

public class ProductConvertor {

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }

    public static Product toProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }
}
