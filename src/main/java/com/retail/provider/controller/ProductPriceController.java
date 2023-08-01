package com.retail.provider.controller;

import com.retail.provider.converter.DateConverter;
import com.retail.provider.converter.ProductPriceConverter;
import com.retail.provider.dto.ProductPriceDTO;
import com.retail.provider.service.ProductPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(ProductPriceController.PATH)
@Tag(name = "Product prices")
@Slf4j
@RequiredArgsConstructor
public class ProductPriceController {

    public static final String PATH = "/v1/brands/{brandId}/products/{productId}/prices";

    private final ProductPriceService productPriceService;

    @GetMapping
    @Operation(
        summary = "Checks current product price",
        description = "Checks current product prices according according to the business rules present in the records"
    )
    public ResponseEntity<ProductPriceDTO> checkAvailability(@PathVariable("brandId") Integer brandId, @PathVariable("productId") Integer productId, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return productPriceService.getProductPrice(brandId, productId, DateConverter.toDate(date))
            .map(ProductPriceConverter::toProductPriceDTO)
            .map(productPrice -> ResponseEntity.status(HttpStatus.OK).body(productPrice))
            .orElse(ResponseEntity.notFound().build());
    }
}