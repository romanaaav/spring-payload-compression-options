package com.romanaaav.payloadcompressionexample.controller;

import com.romanaaav.payloadcompressionexample.util.PayloadSizeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/json")
public class JsonReceptionController {

    private final PayloadSizeHelper payloadSizeHelper;

    @Autowired
    public JsonReceptionController(PayloadSizeHelper payloadSizeHelper) {
        this.payloadSizeHelper = payloadSizeHelper;
    }

    @PostMapping(path = "")
    public String handleJsonPayload(HttpServletRequest request) {
        return "The size of the payload request is: " + payloadSizeHelper.getPayloadSizeFromRequest(request) + "MB";
    }


}
