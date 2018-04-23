package com.ldy.jackson;
/*
 * Davis Molinari
 * www.davismol.net
 * 29/05/2015
 *
 * */

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


class CustomSerializer extends StdSerializer<SWEngineer> {


    public CustomSerializer(Class<SWEngineer> t) {
        super(t);
    }

    public CustomSerializer() {
        this(SWEngineer.class);
    }


    @Override
    public void serialize(SWEngineer swe,
                          JsonGenerator jgen,
                          SerializerProvider sp) throws IOException, JsonGenerationException {

        StringBuilder lang = new StringBuilder();
        jgen.writeStartObject();
        jgen.writeNumberField("id", swe.getId());
        jgen.writeStringField("name", swe.getName());

        for (String s: swe.getLanguages()) {
            lang.append(s).append(";");
        }
        jgen.writeStringField("languages", lang.toString());

        jgen.writeEndObject();
    }
}


class CustomDeserializer extends StdDeserializer<SWEngineer>{

    public CustomDeserializer(Class<SWEngineer> t) {
        super(t);
    }

    public CustomDeserializer() {
        this(SWEngineer.class);
    }

    @Override
    public SWEngineer deserialize(JsonParser jp, DeserializationContext dc)
            throws IOException, JsonProcessingException {

        long id = 0;
        String name = null;
        String []languages = null;
        JsonToken currentToken = null;
        while ((currentToken = jp.nextValue()) != null) {
            switch (currentToken) {
                case VALUE_NUMBER_INT:
                    if (jp.getCurrentName().equals("id")) {
                        id = jp.getLongValue();
                    }
                    break;
                case VALUE_STRING:
                    switch (jp.getCurrentName()) {
                        case "name":
                            name = jp.getText();
                            break;
                        case "languages":
                            languages = jp.getText().split(";");
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return new SWEngineer(id, name, languages);
    }
}

// HERE WE PUT THE ANNOTATIONS
@JsonSerialize(using=CustomSerializer.class)
@JsonDeserialize(using=CustomDeserializer.class)
class SWEngineer {

    private long id;
    private String name;
    private String[] languages;

    public SWEngineer(long id, String name, String[] languages) {
        this.id = id;
        this.name = name;
        this.languages = languages;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getLanguages() {
        return this.languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ID: ").append(this.id).append("\nNome: ")
                .append(this.name).append("\nLinguaggi:");

        for (String s: this.languages) {
            sb.append(" ").append(s);
        }
        return sb.toString();
    }
}


public class CustomSerDeserExample {

    public static void main (String[] args) {

        SWEngineer swe1 = new SWEngineer(1, "Mark", new String[]{"Java", "Python", "C++", "Scala"});

        ObjectMapper mapper = new ObjectMapper();

//      WE DON'T USE THIS STUFF ANYMORE
//
//      SimpleModule mod = new SimpleModule("MyModule");
//      mod.addSerializer(new CustomSerializer(SWEngineer.class));
//      mod.addDeserializer(SWEngineer.class, new CustomDeserializer(SWEngineer.class));
//      mapper.registerModule(mod);

        System.out.println("--- OGGETTO ORIGINALE ---\n" + swe1);

        String s = null;

        try {
            s = mapper.writeValueAsString(swe1);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- JAVA to JSON (Custom) ---\n" + s);

        SWEngineer sweOut = null;
        try {
            sweOut = mapper.readValue(s, SWEngineer.class);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- JSON to JAVA ---\n" + sweOut);
    }
}

