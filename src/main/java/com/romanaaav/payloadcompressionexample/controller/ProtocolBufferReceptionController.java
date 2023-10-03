package com.romanaaav.payloadcompressionexample.controller;

import com.romanaaav.payloadcompressionexample.util.PayloadSizeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/protocol-buffer")
public class ProtocolBufferReceptionController {

    private final PayloadSizeHelper payloadSizeHelper;

    @Autowired
    public ProtocolBufferReceptionController(PayloadSizeHelper payloadSizeHelper) {
        this.payloadSizeHelper = payloadSizeHelper;
    }

    @PostMapping(path = "")
    public String handleJsonPayload(HttpServletRequest request, @RequestBody byte[] protobufPayload) {


        return "The size of the payload request is: " + payloadSizeHelper.getPayloadSizeFromRequest(request) + "MB";
    }
}
