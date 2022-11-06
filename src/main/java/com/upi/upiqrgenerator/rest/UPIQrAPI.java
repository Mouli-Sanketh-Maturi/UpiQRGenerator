package com.upi.upiqrgenerator.rest;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

public interface UPIQrAPI {

    public Mono<ResponseEntity<BufferedImage>> getQrCode(String name, String vpa);

}
