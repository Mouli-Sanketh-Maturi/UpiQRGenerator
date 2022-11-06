package com.upi.upiqrgenerator.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.image.BufferedImage;

@Service
public class UPIQrSvcImpl implements UPIQrSvc{

    QRCodeWriter qrCodeWriter;

    public UPIQrSvcImpl() {
        this.qrCodeWriter = new QRCodeWriter();
    }

    @Override
    public Mono<BufferedImage> getUPIQrCode(String name, String vpa) throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append("upi://pay?");
        sb.append("pn=");
        sb.append(name);
        sb.append("&pa=");
        sb.append(vpa);

        BitMatrix bitMatrix =
                qrCodeWriter.encode(sb.toString(), BarcodeFormat.QR_CODE, 200, 200);

        return Mono.just(MatrixToImageWriter.toBufferedImage(bitMatrix));
    }

}
