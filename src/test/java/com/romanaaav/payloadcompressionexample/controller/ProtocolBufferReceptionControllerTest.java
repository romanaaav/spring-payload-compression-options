package com.romanaaav.payloadcompressionexample.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProtocolBufferReceptionControllerTest {

    private final String JSON_PAYLOAD_RESPONSE = "jsonPayloadExample.json";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    private String getTextResourceContent(String resourceFileName) throws IOException {
        Path resourceDirectory = Paths.get("src","test","resources",resourceFileName);
        return String.join("\n", Files.readAllLines(resourceDirectory, StandardCharsets.UTF_8));
    }

    @Test
    public void testProtocolBufferReception() throws IOException {

        JsonNode jsonPayloadResponse = objectMapper.readTree(getTextResourceContent(JSON_PAYLOAD_RESPONSE));

        

        fail("Not implemented yet");
    }
}