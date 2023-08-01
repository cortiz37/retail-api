package com.retail.provider.service;

import com.retail.provider.domain.ProductPrice;
import com.retail.provider.repository.ProductPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    public Optional<ProductPrice> getProductPrice(Integer brandId, Integer productId, Date date) {
        return Optional.ofNullable(productPriceRepository.findActiveProductPriceByDate(brandId, productId, date));
    }
}