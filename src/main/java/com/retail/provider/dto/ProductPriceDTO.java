package com.retail.provider.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class ProductPriceDTO {

    private Integer productId;
    private Integer brandId;
    private Integer priceList;
    private Date startDate;
    private Date endDate;
    private Double price;
}
