package com.devcodeworld.eventservice.service;


import com.devcodeworld.entity.Stock;
import com.devcodeworld.grpc.StockRequest;
import com.devcodeworld.grpc.StockResponse;
import com.devcodeworld.grpc.StockTradingServiceGrpc;
import com.devcodeworld.repository.StockRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class StockTradingServiceImpl extends StockTradingServiceGrpc.StockTradingServiceImplBase {


    private final StockRepository stockRepository;

    public StockTradingServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void getStockPrice(StockRequest request,
                              StreamObserver<StockResponse> responseObserver) {

        //stockName -> DB -> map response -> return

        String stockSymbol = request.getStockSymbol();
        Stock stockEntity = stockRepository.findByStockSymbol(stockSymbol);

        StockResponse stockResponse = StockResponse.newBuilder()
                .setStockSymbol(stockEntity.getStockSymbol())
                .setPrice(stockEntity.getPrice())
                .setTimestamp(stockEntity.getLastUpdated().toString())
                .build();

        responseObserver.onNext(stockResponse);
        responseObserver.onCompleted();

    }
}
