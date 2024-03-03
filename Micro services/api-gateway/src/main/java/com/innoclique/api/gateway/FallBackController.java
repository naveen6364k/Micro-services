package com.innoclique.api.gateway;

import com.innoclique.api.gateway.filter.RequestBodyRewriteFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    Logger log = LoggerFactory.getLogger(FallBackController.class);

    @GetMapping("/fallback/{serviceName}")
    public ResponseEntity<String> getFallBackResponse(@PathVariable String serviceName){
        log.info("Fallback method is invoked for service : {}", serviceName);
        return new ResponseEntity<>(serviceName + " is not available at this moment", HttpStatus.OK);
    }
}
