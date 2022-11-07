package com.upi.upiqrgenerator.service;

import com.upi.upiqrgenerator.model.UPIParameters;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

public interface UPIQrSvc {

    public Mono<BufferedImage> getUPIQrCode(UPIParameters upi) throws Exception;

}
