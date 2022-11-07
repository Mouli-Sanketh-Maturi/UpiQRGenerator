package com.upi.upiqrgenerator.rest;

import com.upi.upiqrgenerator.model.UPIParameters;
import com.upi.upiqrgenerator.service.UPIQrSvc;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}
