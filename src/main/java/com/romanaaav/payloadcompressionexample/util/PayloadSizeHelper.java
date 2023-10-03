package com.romanaaav.payloadcompressionexample.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PayloadSizeHelper {

    public String getPayloadSizeFromRequest(HttpServletRequest request) {
        double sizeInBytes = (double) request.getContentLength();
        double sizeInMegaBytes = sizeInBytes / (1024 * 1024);
        return String.format( "%.2f", sizeInMegaBytes);
    }
}
