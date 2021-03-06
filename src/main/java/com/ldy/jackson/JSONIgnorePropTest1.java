package com.ldy.jackson;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties({"notInterstingMember", "forgetThisField"})
class MyTestClass {

    private long id;
    private String name;
    private String notInterstingMember;
    private int anotherMember;
    private int forgetThisField;

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

    public String getNotInterstingMember() {
        return this.notInterstingMember;
    }

    public void setNotInterstingMember(String notInterstingMember) {
        this.notInterstingMember = notInterstingMember;
    }

    public int getAnotherMember() {
        return this.anotherMember;
    }

    public void setAnotherMember(int anotherMember) {
        this.anotherMember = anotherMember;
    }

    public int getForgetThisField() {
        return this.forgetThisField;
    }

    public void setForgetThisField(int forgetThisField) {
        this.forgetThisField = forgetThisField;
    }

    @Override
    public String toString() {
        return "MyTestClass [" + this.id + " , " +  this.name + ", " + this.notInterstingMember + ", " + this.anotherMember + ", " + this.forgetThisField + "]";
    }

}

public class JSONIgnorePropTest1 {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        MyTestClass mtc = new MyTestClass();
        mtc.setId(1);
        mtc.setName("Test program");
        mtc.setNotInterstingMember("Don't care about this");
        mtc.setAnotherMember(100);
        mtc.setForgetThisField(-1);


        String s = null;
        try {
            s = mapper.writeValueAsString(mtc);
        }
        catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(s);

        MyTestClass mtc2 = null;
        try {
            mtc2 = mapper.readValue(s, MyTestClass.class);
        }
        catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(mtc2.toString());

    }
}