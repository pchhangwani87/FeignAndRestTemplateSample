package com.example.controller;

import com.example.feignClient.TestFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Autowired
    TestFeign testFeign;


    @GetMapping(value="/feign",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> startFeignTest(){
        LOG.debug("Testing Started :: startFeignTest ");

        ResponseEntity<String> responseString = testFeign.sampleRest();

        LOG.debug("responseString :: {} ",responseString.getStatusCode());
        return ResponseEntity.status(HttpStatus.OK).body(responseString.getBody()+" Feign Test Successfully !!");
    }


    @GetMapping(value="/restTemplate",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> restTemplateTest(){
        LOG.debug("Testing Started :: restTemplateTest ");
        String responseString = "";
        try {
            /*Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("##PROXY_HOST##", 80));
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setProxy(proxy);*/

           // RestTemplate restTemplate = new RestTemplate(requestFactory);

            RestTemplate restTemplate = new RestTemplate();
            String sampleRestUrl
                    = "http://localhost:8080/demo/sample/restService";
            ResponseEntity<String> response
                    = restTemplate.getForEntity(sampleRestUrl, String.class);
            LOG.debug("response :: " + response.getStatusCode());
            LOG.debug("testing Ended :: " + response.getBody());
            responseString = response.getBody();
        }catch (Exception e){
            LOG.error("Exception Occurred :: ",e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("error");
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseString+" RestTemplate Tested Successfully !!");
    }

}
