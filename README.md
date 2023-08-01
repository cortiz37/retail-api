# retail-api

The Retail API service is responsible for handling requests for a hypothetical retail management group.

---------

## api

There are 2 ways to consume this api:

- rest (traditional, popular)
- gRPC (modern, high performance)

### rest

- port: 8080
- (swagger) run the application and open: [http://localhost:8080/swagger-ui.html]

### gRPC

- port: 9090
- service contract located here: `src/main/proto/product_price_service.proto`

The gRPC services can be called using:
- [gRPCurl](https://github.com/fullstorydev/grpcurl)
- [gRPCui](https://github.com/fullstorydev/grpcui)

---------
    
## requirements
- Java 1.8 or higher
- Docker

## build and run

Build tool is gradle: [https://gradle.org/]

- running directly: ```./gradlew bootRun```
- running in docker: 
```
    docker build -t retail .
    docker run -it --rm -p 8080:8080 -p 9090:9090 retail
```
- or IDE capabilities...

### configuration

Configuration properties are in the application.properties file. They can be overwritten by updating this file or via env var (`APP_OPTS`)