package com.ldy.jackson.formater.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XMLParser {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new XmlMapper()
                .registerModule(new JavaTimeModule());

        XmlMapper xmlMapper = (XmlMapper) objectMapper;

        FetchModel employees = xmlMapper.readValue(
                StringUtils.toEncodedString(Files.readAllBytes(Paths.get("/Users/yanzhifa/code/practice/target/classes/Fetch.xml")), StandardCharsets.UTF_8),
                FetchModel.class);
        //System.out.println(employees);


        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        ObjectMapper objectMapper1 = new ObjectMapper();
        Car car = objectMapper1.readValue(json, Car.class);

        System.out.println(car);
    }

    @Data
    public static class Car {

        private String color;
        private String type;

        // standard getters setters
    }
}
