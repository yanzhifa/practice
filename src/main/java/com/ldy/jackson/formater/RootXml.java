package com.ldy.jackson.formater;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.*;
import lombok.Data;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JacksonXmlRootElement(localName = "root")
@JsonSerialize
public class RootXml {

    private String k1;

    @JacksonXmlProperty(localName = "k-2")
    private Integer k2;

    //@JacksonXmlElementWrapper(localName = "k3")
    private Collection<String> k3;

    @JacksonXmlElementWrapper(localName = "k4")
    @JacksonXmlProperty(localName = "ka")
    private Collection<String> k4;

    @JacksonXmlElementWrapper(localName = "k5", useWrapping = false)
    private Collection<String> k5;

    @JacksonXmlProperty(isAttribute = true)
    private Integer k6;

    @Data
    public static class Value1 {

        @JacksonXmlText
        private String a;

    }

    private Value1 k7;

    @Data
    public static class Value2 {

        @JacksonXmlText(value = false)
        @JacksonXmlProperty(localName = "A")
        private String a;

        private Map<String, String> other = new HashMap<>();

        @JsonAnySetter
        public void setAddress(String name, String value) {
            this.other.put(name, value);
        }
    }

    private Value2 k8;

    @Data
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    public static class Value3 {
        public String a;

        @JsonProperty
        public String b;

        @JsonProperty
        public String c;

        @JacksonXmlElementWrapper(useWrapping = false)
        private List<Value2> value2;
    }

    private Value3 k10;

    private Value3 k11;

    @JacksonXmlCData(value = true) // 序列化时是否总是使用 CDATA 块
    private String k9;


}
