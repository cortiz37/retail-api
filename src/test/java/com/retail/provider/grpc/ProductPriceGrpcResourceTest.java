package com.retail.provider.grpc;

import com.retail.provider.domain.ProductPrice;
import com.retail.provider.service.ProductPriceService;
import io.grpc.stub.StreamObserver;
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
class ProductPriceGrpcResourceTest {

    private final ProductPrice productPriceExample = ProductPrice.builder()
        .id("0001")
        .brandId(1)
        .productId(1)
        .priceList(1)
        .priority(0)
        .startDate(new Date())
        .endDate(new Date())
        .price(20.0)
        .build();

    private ProductPriceGrpcResource productPriceGrpcResource;

    @Mock
    private ProductPriceService productPriceService;

    @Mock
    private StreamObserver<ProductPriceResponse> responseObserverGet;

    @BeforeEach
    public void setUp() {
        productPriceGrpcResource = new ProductPriceGrpcResource(productPriceService);
    }

    @Test
    void shouldGetProductPrice() {
        Mockito.when(productPriceService.getProductPrice(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.any(Date.class)))
            .thenReturn(Optional.of(productPriceExample));

        productPriceGrpcResource.getProductPrice(ProductPriceRequest.newBuilder().setProductId(344).setBrandId(1).build(), responseObserverGet);

        Mockito.verify(responseObserverGet).onNext(ArgumentMatchers.any(ProductPriceResponse.class));
        Mockito.verify(responseObserverGet).onCompleted();
    }
}