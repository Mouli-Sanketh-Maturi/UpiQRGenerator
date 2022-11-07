package com.upi.upiqrgenerator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.upi.upiqrgenerator.model.UPIParameters;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

@Service
public class UPIQrSvcImpl implements UPIQrSvc{

    QRCodeWriter qrCodeWriter;

    public UPIQrSvcImpl() {
        this.qrCodeWriter = new QRCodeWriter();
    }

    @Override
    public Mono<BufferedImage> getUPIQrCode(UPIParameters upi) throws Exception{

        if(upi.getPn() == null || upi.getPn() == null) {
            throw new IllegalArgumentException("Missing one or more mandatory parameters");
        }

        UriComponentsBuilder upiBuilder = UriComponentsBuilder.newInstance();

        upiBuilder.scheme("upi");
        upiBuilder.host("pay");
        upiBuilder.queryParam("pa",upi.getPa());
        upiBuilder.queryParam("pn",upi.getPn());

        if(Strings.isNotBlank(upi.getMc())) upiBuilder.queryParam("mc",upi.getMc());
        if(Strings.isNotBlank(upi.getTid())) upiBuilder.queryParam("tid",upi.getTid());
        if(Strings.isNotBlank(upi.getTr())) upiBuilder.queryParam("tr",upi.getTr());
        if(Strings.isNotBlank(upi.getTn())) upiBuilder.queryParam("tn",upi.getTn());
        if(Strings.isNotBlank(upi.getAm())) upiBuilder.queryParam("am",upi.getAm());
        if(Strings.isNotBlank(upi.getMam())) upiBuilder.queryParam("mam",upi.getMam());
        if(Strings.isNotBlank(upi.getCu())) upiBuilder.queryParam("cu",upi.getCu());
        if(Strings.isNotBlank(upi.getUrl())) upiBuilder.queryParam("url",upi.getUrl());
        if(Strings.isNotBlank(upi.getMode())) upiBuilder.queryParam("mode",upi.getMode());
        if(Strings.isNotBlank(upi.getSign())) upiBuilder.queryParam("sign",upi.getSign());
        if(Strings.isNotBlank(upi.getOrgid())) upiBuilder.queryParam("orgid",upi.getOrgid());
        if(Strings.isNotBlank(upi.getMid())) upiBuilder.queryParam("mid",upi.getMid());
        if(Strings.isNotBlank(upi.getMsid())) upiBuilder.queryParam("msid",upi.getMsid());
        if(Strings.isNotBlank(upi.getMtid())) upiBuilder.queryParam("mtid",upi.getMtid());
        if(Strings.isNotBlank(upi.getQuery())) upiBuilder.queryParam("Query",upi.getQuery());

        BitMatrix bitMatrix =
                qrCodeWriter.encode(upiBuilder.build().toUriString(), BarcodeFormat.QR_CODE, 200, 200);

        return Mono.just(MatrixToImageWriter.toBufferedImage(bitMatrix));
    }

}
