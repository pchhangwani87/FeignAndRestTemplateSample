package com.example.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="sample",url = "http://localhost:8080/demo")
public interface TestFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/sample/restService", consumes = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String> sampleRest();

}
