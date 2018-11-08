package com.ldy.jackson.formater;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {

    public static void main(String[] args) throws IOException {

        Integer i = 1;
        System.out.println(i);
        boolean b = false;
        System.out.println(b);

        ObjectMapper objectMapper = new XmlMapper();
        Employees employees = objectMapper.readValue(
                StringUtils.toEncodedString(Files.readAllBytes(Paths.get("/Users/yanzhifa/code/practice/target/classes/employee.xml")), StandardCharsets.UTF_8),
                Employees.class);
        System.out.println(employees);


        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        RootXml rootXml = objectMapper.readValue(
                StringUtils.toEncodedString(Files.readAllBytes(Paths.get("/Users/yanzhifa/code/practice/target/classes/XML.xml")), StandardCharsets.UTF_8),
                RootXml.class);
        int k = rootXml.getK11().getC() == null ? 1 : 0;
        System.out.println(i);
        System.out.println(rootXml);




    }
}
