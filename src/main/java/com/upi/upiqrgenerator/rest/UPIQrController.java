package com.upi.upiqrgenerator.rest;

import com.upi.upiqrgenerator.model.UPIParameters;
import com.upi.upiqrgenerator.service.UPIQrSvc;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

@RestController
public class UPIQrController implements UPIQrAPI{

    UPIQrSvc upiQrSvc;

    @Autowired
    public UPIQrController(UPIQrSvc upiQrSvc){
        this.upiQrSvc = upiQrSvc;
    }

    @SneakyThrows
    @GetMapping("/upi")
    @Override
    public Mono<ResponseEntity<BufferedImage>> getQrCode(@RequestParam("name") String name, @RequestParam("vpa") String vpa) {
             return upiQrSvc.getUPIQrCode(new UPIParameters(vpa,name))
                     .map(bufferedImage ->
                             ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bufferedImage));
    }

    @SneakyThrows
    @PostMapping(value = "/upi",consumes = "application/json")
    @Override
    public Mono<ResponseEntity<BufferedImage>> getDynamicQrCode(@RequestBody UPIParameters params) {
        return upiQrSvc.getUPIQrCode(params)
                .map(bufferedImage ->
                        ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bufferedImage));
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}
