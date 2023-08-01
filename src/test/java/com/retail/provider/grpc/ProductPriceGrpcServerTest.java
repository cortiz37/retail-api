package com.retail.provider.grpc;

import com.retail.provider.converter.DateConverter;
import com.retail.provider.domain.ProductPrice;
import com.retail.provider.service.ProductPriceService;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductPriceGrpcServerTest {

    @Rule
    public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

    @Mock
    private ProductPriceService productPriceService;

    @Test
    public void shouldReturnProductPrice() throws Exception {
        ProductPrice productPrice = ProductPrice.builder()
            .id("001")
            .brandId(1)
            .productId(233)
            .priceList(1)
            .priority(1)
            .price(23.0)
            .startDate(new Date())
            .endDate(new Date())
            .build();

        String serverName = InProcessServerBuilder.generateName();

        grpcCleanup.register(
            InProcessServerBuilder.forName(serverName)
                .directExecutor()
                .addService(new ProductPriceGrpcResource(productPriceService))
                .build().start()
        );

        ProductPriceServiceGrpc.ProductPriceServiceBlockingStub blockingStub = ProductPriceServiceGrpc.newBlockingStub(
            grpcCleanup.register(InProcessChannelBuilder.forName(serverName).directExecutor().build())
        );

        Mockito.when(productPriceService.getProductPrice(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.any(Date.class))).thenReturn(Optional.of(productPrice));

        ProductPriceResponse reply = blockingStub.getProductPrice(ProductPriceRequest.newBuilder()
            .setBrandId(1)
            .setProductId(35455)
            .setDate(DateConverter.toTimestamp(new Date()))
            .build());

        Assertions.assertNotNull(reply);
        Assertions.assertEquals(productPrice.getPrice(), reply.getPrice());
    }
}