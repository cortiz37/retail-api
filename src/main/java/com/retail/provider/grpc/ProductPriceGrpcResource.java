package com.retail.provider.grpc;

import com.retail.provider.converter.DateConverter;
import com.retail.provider.converter.ProductPriceConverter;
import com.retail.provider.service.ProductPriceService;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import javax.persistence.EntityNotFoundException;

@GrpcService
@Slf4j
@RequiredArgsConstructor
public class ProductPriceGrpcResource extends ProductPriceServiceGrpc.ProductPriceServiceImplBase {

    private final ProductPriceService productPriceService;

    @Override
    public void getProductPrice(ProductPriceRequest request, StreamObserver<ProductPriceResponse> responseObserver) {
        try {
            ProductPriceResponse productPriceResponse = productPriceService.getProductPrice(request.getBrandId(), request.getProductId(), DateConverter.toDate(request.getDate()))
                .map(ProductPriceConverter::toProductPriceResponse)
                .orElseThrow(() -> new EntityNotFoundException("no entity found"));
            responseObserver.onNext(productPriceResponse);
            responseObserver.onCompleted();
        } catch (EntityNotFoundException e) {
            responseObserver.onError(new StatusException(Status.NOT_FOUND.withDescription(e.getMessage())));
        }
    }
}