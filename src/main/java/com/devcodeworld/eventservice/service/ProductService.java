package com.devcodeworld.eventservice.service;

import com.devcodeworld.eventservice.dto.ProductDTO;
import com.devcodeworld.eventservice.entity.Product;
import com.devcodeworld.eventservice.repository.ProductRepository;
import com.devcodeworld.eventservice.utils.ProductConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDTO> getAllProducts() {
        return productRepository.findAll().map(ProductConvertor::toProductDTO);
    }

    public Mono<ProductDTO> getProductById(String id) {
        return productRepository.findById(id).map(ProductConvertor::toProductDTO);
    }

    public Flux<ProductDTO> getProductInRange(double start, double end) {
        return productRepository.findByPriceBetween(Range.closed(start, end));
    }

    public Mono<ProductDTO> createProduct(Mono<ProductDTO> productDTOMono) {
        return productDTOMono.map(ProductConvertor::toProduct)
                .flatMap(productRepository::save)
                .map(ProductConvertor::toProductDTO);
    }

}