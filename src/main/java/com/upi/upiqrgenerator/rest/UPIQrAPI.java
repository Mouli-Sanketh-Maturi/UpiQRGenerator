package com.upi.upiqrgenerator.rest;

import com.upi.upiqrgenerator.model.UPIParameters;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

public interface UPIQrAPI {

    public Mono<ResponseEntity<BufferedImage>> getQrCode(String name, String vpa);

    public Mono<ResponseEntity<BufferedImage>> getDynamicQrCode(UPIParameters params);

}
