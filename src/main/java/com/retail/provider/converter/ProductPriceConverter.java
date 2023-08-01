package com.retail.provider.converter;

import com.retail.provider.domain.ProductPrice;
import com.retail.provider.dto.ProductPriceDTO;
import com.retail.provider.grpc.ProductPriceResponse;

public abstract class ProductPriceConverter {

    public static ProductPriceDTO toProductPriceDTO(ProductPrice productPrice) {
        return ProductPriceDTO.builder()
            .productId(productPrice.getProductId())
            .brandId(productPrice.getBrandId())
            .priceList(productPrice.getPriceList())
            .startDate(productPrice.getStartDate())
            .endDate(productPrice.getEndDate())
            .price(productPrice.getPrice())
            .build();
    }

    public static ProductPriceResponse toProductPriceResponse(ProductPrice productPrice) {
        return ProductPriceResponse.newBuilder()
            .setProductId(productPrice.getProductId())
            .setBrandId(productPrice.getBrandId())
            .setPriceList(productPrice.getPriceList())
            .setStartDate(DateConverter.toTimestamp(productPrice.getStartDate()))
            .setEndDate(DateConverter.toTimestamp(productPrice.getEndDate()))
            .setPrice(productPrice.getPrice())
            .build();
    }
}
