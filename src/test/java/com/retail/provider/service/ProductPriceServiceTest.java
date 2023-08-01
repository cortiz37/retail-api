package com.retail.provider.service;

import com.retail.provider.domain.ProductPrice;
import com.retail.provider.repository.ProductPriceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductPriceServiceTest {

    private ProductPriceService productPriceService;

    @Mock
    private ProductPriceRepository productPriceRepository;

    @BeforeEach
    public void setUp() {
        productPriceService = new ProductPriceService(productPriceRepository);
    }

    @Test
    void shouldGetReservation() {
        final Integer brandId = 1;
        final Integer productId = 3455;
        final Date date = new Date();

        ProductPrice productPrice = ProductPrice.builder().id("existing_entity").build();

        Mockito.when(productPriceRepository.findActiveProductPriceByDate(
            ArgumentMatchers.eq(brandId), ArgumentMatchers.eq(productId), ArgumentMatchers.eq(date))).thenReturn(productPrice);

        Optional<ProductPrice> result = productPriceService.getProductPrice(brandId, productId, date);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(productPrice.getId(), result.get().getId());
    }
}