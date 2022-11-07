package com.upi.upiqrgenerator.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class UPIParameters {

    @NonNull
    String pa;
    @NonNull
    String pn;
    String mc;
    String tid;
    String tr;
    String tn;
    String am;
    String mam;
    String cu;
    String url;
    String mode;
    String sign;
    String orgid;
    String mid;
    String msid;
    String mtid;
    String Query;

}