package com.innoclique.api.gateway.filter.functions;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.UUID;

@Component
public class RequestBodyRewrite implements RewriteFunction<String, String> {

    Logger log = LoggerFactory.getLogger(RequestBodyRewrite.class);

    @Override
    public Publisher<String> apply(ServerWebExchange exchange, String requestBody){

        log.info("Incoming request url: {}, body: {}", exchange.getRequest().getURI().getPath(), requestBody);

        log.info("Incoming request method : {}", exchange.getRequest().getMethod());


         exchange.getRequest().mutate().header("X-Trace-ID", UUID.randomUUID().toString());

        log.info("Incoming request Remote address: {}, body: {}", exchange.getRequest().getRemoteAddress() != null ? exchange.getRequest().getRemoteAddress().getAddress() : exchange.getRequest().getRemoteAddress(), requestBody);

        MDC.put("traceId", String.valueOf( exchange.getRequest().getHeaders().get("X-Trace-ID")));

        if(!Strings.isBlank(requestBody) ) {
            String decodedRequest = decodeRequest(requestBody);
            log.info("Decoded request body: {}", decodedRequest);
            return Mono.just(decodedRequest);
        } else {
            return Mono.just(StringUtils.EMPTY);
        }

    }

    private String decodeRequest(String requestBody) {
        return new String(Base64.getDecoder().decode(requestBody));
    }

}
