package com.devcodeworld.eventservice.controller;

import com.devcodeworld.eventservice.dto.ProductDTO;
import com.devcodeworld.eventservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Flux<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDTO> getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @GetMapping("/byRange")
    public Flux<ProductDTO> getProductBetweenRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return productService.getProductInRange(min,max);
    }

    @PostMapping
    public Mono<ProductDTO> saveProduct(@RequestBody Mono<ProductDTO> productDTO) {
        return  productService.createProduct(productDTO);
    }
}
