package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sample")
public class SampleRestAPI {

    private static final Logger LOG = LoggerFactory.getLogger(SampleRestAPI.class);

    @GetMapping(value="/restService",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> startFeignTest(){
        LOG.debug("Testing Started :: SampleRestAPI ");
        String responseString = "Hello World !!";
        return ResponseEntity.status(HttpStatus.OK).body(responseString);
    }
}
