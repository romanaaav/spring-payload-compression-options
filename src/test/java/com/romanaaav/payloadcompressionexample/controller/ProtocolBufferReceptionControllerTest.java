package com.romanaaav.payloadcompressionexample.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.romanaaav.payloadcompressionexample.proto.Novedades;
import com.romanaaav.payloadcompressionexample.util.PayloadSizeHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
class ProtocolBufferReceptionControllerTest {

    private final String JSON_PAYLOAD_RESPONSE = "jsonPayloadExample.json";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PayloadSizeHelper payloadSizeHelper;

    @Autowired
    MockMvc mockMvc;

    private String getTextResourceContent(String resourceFileName) throws IOException {
        Path resourceDirectory = Paths.get("src","test","resources",resourceFileName);
        return String.join("\n", Files.readAllLines(resourceDirectory, StandardCharsets.UTF_8));
    }

    @Test
    public void testProtocolBufferCompression() throws IOException {

        JsonNode jsonPayloadResponse = objectMapper.readTree(getTextResourceContent(JSON_PAYLOAD_RESPONSE));

        Novedades.Builder novedadesBuilder = Novedades.newBuilder();

        String hash = jsonPayloadResponse.get("hash").asText();
        JsonNode fotosNode = jsonPayloadResponse.get("fotos");


        if (fotosNode != null && fotosNode.isArray()) {
            // Iterate over the elements of the array
            for (JsonNode element : fotosNode) {
                Novedades.FotoNovedad.Builder fotoNovedadBuilder = Novedades.FotoNovedad.newBuilder()
                        .setBase64(element.get("base64").asText())
                        .setEtiqueta(element.get("etiqueta").asText());

                novedadesBuilder.addFotos(fotoNovedadBuilder);
            }
        }

        Novedades novedades = novedadesBuilder.setHash(hash).build();


        byte[] byteArray = novedades.toByteArray();

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\tmp\\testCompression.gzip");

        try (
                GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream)) {
            novedades.writeTo(gzipOutputStream);
        }

        fileOutputStream.close();

        log.info("El peso total del arreglo de bytes de novedades en Protocol Buffer con foto en Base64 es:" + payloadSizeHelper.getPayloadSizeFromByteArray(byteArray));

        fail("Not implemented yet");
    }
}