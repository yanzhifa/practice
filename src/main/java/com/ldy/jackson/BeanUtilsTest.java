package com.ldy.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.OffsetDateTime;

public class BeanUtilsTest {

    @Data
    static class A {
        String a;
        String b;

        public A(String a, String b) {
            this.a = a;
            this.b = b;
        }
    }

    @Data
    static class B {
        public B(String a, String d, String c) {
            this.a = a;
            this.d = d;
            this.c = c;
        }

        String a;
        String d;
        String c;
    }

    public static void main(String[] args) {
        A a = new A("1", "2");
        B b = new B("4","5","6");
        BeanUtils.copyProperties(a, b);
        System.out.println(b);

        Competency competency = new Competency(OffsetDateTime.now(),
                OffsetDateTime.now(),"A","001",OffsetDateTime.now(),OffsetDateTime.now(),
                "prov","Yes","No",
                "Yes","VVV","jack","Achieved",
                "need",OffsetDateTime.now(),"tracks");
        CompetencyEntity entity = new CompetencyEntity();
        BeanUtils.copyProperties(competency, entity);
        System.out.println(entity);
    }

    @Data
    public static class Competency {

        public Competency(OffsetDateTime effectiveDate, OffsetDateTime expirationDate, String name, String number, OffsetDateTime eligibilityDate, OffsetDateTime dateOfCompletion, String provisional, String meetCustomerReferenceRequirements, String meetTrainingRequirements, String enrollmentFormSubmitted, Object competency, String primaryContact, String status, String provisionalReason, OffsetDateTime provisionalStatusEndDate, String applicationTracks) {
            this.effectiveDate = effectiveDate;
            this.expirationDate = expirationDate;
            this.name = name;
            this.number = number;
            this.eligibilityDate = eligibilityDate;
            this.dateOfCompletion = dateOfCompletion;
            this.provisional = provisional;
            this.meetCustomerReferenceRequirements = meetCustomerReferenceRequirements;
            this.meetTrainingRequirements = meetTrainingRequirements;
            this.enrollmentFormSubmitted = enrollmentFormSubmitted;
            this.competency = competency;
            this.primaryContact = primaryContact;
            this.status = status;
            this.provisionalReason = provisionalReason;
            this.provisionalStatusEndDate = provisionalStatusEndDate;
            this.applicationTracks = applicationTracks;
        }

        public Competency(){}

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
        public Object competency;

        @JsonProperty("PrimaryContact")
        public String primaryContact;

        @JsonProperty("Status")
        public String status;

        @JsonProperty("ProvisionalReason")
        public String provisionalReason;

        @JsonProperty("ProvisionalStatusEndDate")
        public OffsetDateTime provisionalStatusEndDate;

        @JsonProperty("ApplicationTracks")
        public String applicationTracks;

    }

    @Data
    static class CompetencyEntity {

        public enum Status {
            Achieved, Incomplete
        }

        public CompetencyEntity() {
        }

        @JsonIgnore
        private String id;

        private String name;

        private OffsetDateTime effectiveDate;

        private OffsetDateTime expirationDate;

        private String number;

        private OffsetDateTime eligibilityDate;

        private OffsetDateTime dateOfCompletion;

        private Boolean provisional;

        private String provisionalReason;

        private OffsetDateTime provisionalStatusEndDate;

        private Boolean meetCustomerReferenceRequirements;

        private Boolean meetTrainingRequirements;

        private Boolean enrollmentFormSubmitted;

        private String competency;

        private String primaryContact;

        private Status status;

    }
}
