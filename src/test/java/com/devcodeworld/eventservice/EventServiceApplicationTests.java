package com.devcodeworld.eventservice;

import com.devcodeworld.eventservice.controller.ProductController;
import com.devcodeworld.eventservice.dto.ProductDTO;
import com.devcodeworld.eventservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.when;

@WebFluxTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @MockitoBean
    ProductService productService;

    @Test
    void saveProduct_ok() {
        ProductDTO dto = new ProductDTO("102", "mobile", 1, 10000);

        // Controller takes Mono<ProductDTO>, so stub with ArgumentMatchers.<Mono<ProductDTO>>any()
        when(productService.createProduct(ArgumentMatchers.<Mono<ProductDTO>>any()))
                .thenReturn(Mono.just(dto));

        webTestClient.post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(dto)                  // send the body directly
                .exchange()
                .expectStatus().isOk()           // your controller returns 200 by default
                .expectBody(ProductDTO.class);
    }
}