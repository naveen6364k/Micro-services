package com.innoclique.api.gateway.filter;

import com.innoclique.api.gateway.filter.functions.RequestBodyRewrite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Component
public class RequestBodyRewriteFilter implements GlobalFilter, Ordered {

    @Autowired
    private ModifyRequestBodyGatewayFilterFactory modifyRequestBodyFilter;

    @Autowired
    private RequestBodyRewrite requestBodyRewrite;

    Logger log = LoggerFactory.getLogger(RequestBodyRewriteFilter.class);

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        List<String> traceIdList = exchange.getRequest().getHeaders().get("X-Trace-ID");

        ServerHttpRequest request = exchange.getRequest();
        if (traceIdList == null || traceIdList.isEmpty()) {

             request = exchange.getRequest().mutate()
                    .header("X-Trace-ID", UUID.randomUUID().toString())
                    .build();
        }
        traceIdList = exchange.getRequest().getHeaders().get("X-Trace-ID");

        String traceId = (traceIdList != null && !traceIdList.isEmpty()) ? traceIdList.get(0) : null;
                // String traceId = exchange.getRequest().getHeaders().get("X-Trace-ID") != null &&  exchange.getRequest().getHeaders().get("X-Trace-ID").get(0);
        MDC.put("traceId",  traceId);

       /* log.info("Incoming request url: {}, body: {}", exchange.getRequest().getURI().getPath(), exchange.getRequest().getBody());

        log.info("Incoming request method : {}", exchange.getRequest().getMethod());



        log.info("Incoming request Remote address: {}, body: {}", exchange.getRequest().getRemoteAddress() != null ? exchange.getRequest().getRemoteAddress().getAddress() : exchange.getRequest().getRemoteAddress(), exchange.getRequest().getBody()); */

      return  modifyRequestBodyFilter
                .apply(new ModifyRequestBodyGatewayFilterFactory.Config()
                        .setRewriteFunction(String.class, String.class, requestBodyRewrite))
                .filter(exchange.mutate().request(request).build(), chain);

       // return chain.filter(exchange.mutate().request(request).build());
    }
       /* return modifyRequestBodyFilter
                .apply(new ModifyRequestBodyGatewayFilterFactory.Config()
                        .setRewriteFunction(String.class, String.class, requestBodyRewrite))
                .filter(exchange, chain);

    }*/
}
