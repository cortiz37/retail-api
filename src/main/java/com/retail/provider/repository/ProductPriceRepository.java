package com.retail.provider.repository;

import com.retail.provider.domain.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, String> {

    @Query(
        value = "SELECT * FROM PRICES p WHERE p.BRAND_ID = ?1 AND p.PRODUCT_ID = ?2 AND p.START_DATE <= ?3 AND p.END_DATE >= ?3 ORDER BY p.PRIORITY DESC LIMIT 1",
        nativeQuery = true
    )
    ProductPrice findActiveProductPriceByDate(Integer brandId, Integer productId, Date date);
}