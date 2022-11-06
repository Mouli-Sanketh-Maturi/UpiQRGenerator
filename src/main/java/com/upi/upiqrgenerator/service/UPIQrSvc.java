package com.upi.upiqrgenerator.service;

import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

public interface UPIQrSvc {

    public Mono<BufferedImage> getUPIQrCode(String name, String vpa) throws Exception;

}
