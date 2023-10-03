package com.romanaaav.payloadcompressionexample.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PayloadSizeHelper {

    public String getPayloadSizeFromRequest(HttpServletRequest request) {
        double sizeInBytes = (double) request.getContentLength();
        return String.format( "%.2f", getSizeInMegaBytes(sizeInBytes));
    }

    public String getPayloadSizeFromByteArray(byte[] byreArray) {
        double sizeInBytes = (double) byreArray.length;
        return String.format( "%.2f", getSizeInMegaBytes(sizeInBytes));
    }

    private double getSizeInMegaBytes(double sizeInBytes) {
        return sizeInBytes / (1024 * 1024);
    }
}
