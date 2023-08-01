package com.retail.provider.controller;

import com.retail.provider.domain.ProductPrice;
import com.retail.provider.service.ProductPriceService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@WebMvcTest(value = ProductPriceController.class)
class ProductPriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductPriceService productPriceService;

    @Test
    public void shouldGetProductPrice() throws Exception {
        ProductPrice productPrice = ProductPrice.builder().id("001").build();

        Mockito.when(productPriceService.getProductPrice(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.any(Date.class))).thenReturn(Optional.of(productPrice));

        mockMvc.perform(MockMvcRequestBuilders.get(ProductPriceController.PATH
                .replace("{brandId}", "1")
                .replace("{productId}", "3455")).param("date", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}