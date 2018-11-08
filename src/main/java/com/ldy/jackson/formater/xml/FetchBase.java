package com.ldy.jackson.formater.xml;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
@JacksonXmlRootElement(localName = "RetrievePartnerResponse")
public class FetchBase {

    @JacksonXmlElementWrapper(localName = "ListofPartners")
    @JacksonXmlProperty(localName = "Partner")
    public List<PartnerInfo> list = new LinkedList<>();

    @JacksonXmlProperty(localName = "Status")
    public String status;

    @Data
    @JacksonXmlRootElement(localName = "Partner")
    public static class PartnerInfo {

        @JacksonXmlProperty(localName = "PartnerId")
        public int partnerId;

        @JacksonXmlProperty(localName = "PartnerName")
        public String partnerName;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Competency")
        public List<Competency> competencys;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Badges")
        public List<Bage> bages;

        @JacksonXmlProperty(localName = "Accreditations")
        public Accreditations accreditations;

        @JacksonXmlProperty(localName = "PrimaryContact")
        public String primaryContact;

        @JacksonXmlProperty(localName = "Status")
        public String status;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "Program")
        public List<Program> programs;

        @JacksonXmlProperty(localName = "Address")
        public Address address;

        @JacksonXmlProperty(localName = "Contact")
        public Contact contacts;

        @JacksonXmlProperty(localName = "isSDPEligible")
        public String isSDPEligible;

        @JacksonXmlProperty(localName = "SPtier")
        public String spTier;

    }

    @Data
    public static class Competency {

        @JsonProperty("EffectiveDate")
        public OffsetDateTime effectiveDate;

        @JsonProperty("ExpirationDate")
        public OffsetDateTime expirationDate;

        @JsonProperty("Name")
        public String name;

        @JsonProperty("Number")
        public String number;

        @JsonProperty("EligibilityDate")
        public OffsetDateTime eligibilityDate;

        @JsonProperty("DateOfCompletion")
        public OffsetDateTime dateOfCompletion;

        @JsonProperty("Provisional")
        public String provisional;

        @JsonProperty("MeetCustomerReferenceRequirements")
        public String meetCustomerReferenceRequirements;

        @JsonProperty("MeetTrainingRequirements")
        public String meetTrainingRequirements;

        @JsonProperty("EnrollmentFormSubmitted")
        public String enrollmentFormSubmitted;

        @JsonProperty("Competency")
        public String competency;

        @JsonProperty("PrimaryContact")
        public String primaryContact;

        @JsonProperty("Status")
        public String status;

        @JsonProperty("ProvisionalReason")
        public String provisionalReason;

        @JsonProperty("ProvisionalStatusEndDate")
        public OffsetDateTime provisionalStatusEndDate;

        @JsonProperty("ApplicationTracks")
        public String applicanTracks;

    }

    @Data
    public static class Bage {

        @JsonProperty("name")
        public String name;

        @JsonProperty("value")
        public String value;
    }

    @Data
    public static class Accreditations {

        @JsonProperty("NumberOfVCP")
        public Float numberOfVCP;

        @JsonProperty("NumberOfVSP")
        public Float numberOfVSP;

        @JsonProperty("NumberOfVTSP")
        public Float numberOfVTSP;

        @JsonProperty("NumberOfVOPCP")
        public Float numberOfVOPCP;

        @JsonProperty("NumberOfVSPCP")
        public Float numberOfVSPCP;

        @JsonProperty("NumberOfEthicsAndCompliance")
        public Float numberOfEthicsAndCompliance;

        @JsonProperty("NumberOfVCPGlobal")
        public Float numberOfVCPGlobal;

        @JsonProperty("NumberOfVSPGlobal")
        public Float numberOfVSPGlobal;

        @JsonProperty("NumberOfVTSPGlobal")
        public Float numberOfVTSPGlobal;

        @JsonProperty("NumberOfVOPCPGlobal")
        public Float numberOfVOPCPGlobal;

        @JsonProperty("NumberOfVSPCPGlobal")
        public Float numberOfVSPCPGlobal;

    }

    @Data
    public static class Program {

        @JsonProperty("EffectiveDate")
        public OffsetDateTime effectiveDate;

        @JsonProperty("ExpirationDate")
        public OffsetDateTime expirationDate;

        @JsonProperty("ClassOfTrade")
        public String classOfTrade;

        @JsonProperty("Tier")
        public String tier;
    }

    @Data
    public static class Address {
        @JsonProperty("AddressLine1")
        public String addressLine1;

        @JsonProperty("AddressLine2")
        public String addressLine2;

        private Map<String, String> addresses = new HashMap<>();

        @JsonAnySetter
        public void setAddress(String name, String value) {
            this.addresses.put(name, value);
        }

        @JsonProperty("City")
        public String city;

        @JsonProperty("State")
        public String state;

        @JsonProperty("Province")
        public String province;

        @JsonProperty("PostalCode")
        public String postalCode;

        @JsonProperty("County")
        public String county;

        @JsonProperty("CountryCode")
        public String countryCode;

        @JsonProperty("Country")
        public String country;

        @JsonProperty("Geo")
        public String geo;

        @JsonProperty("Region")
        public String region;

        @JsonProperty("SubRegion")
        public String subRegion;

        @JsonProperty("RegionalArea")
        public String regionalArea;
    }

    @Data
    public static class Contact {
        @JsonProperty("ContactFirstName")
        public String contactFirstName;

        @JsonProperty("ContactLastName")
        public String contactLastName;

        @JsonProperty("ContactEmail")
        public String ContactEmail;

        @JsonProperty("ContactPhone")
        public String ContactPhone;

    }

}

