package com.dilshat.restfulwebservices.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties("field1") //На уровне класса
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    //@JsonIgnore
    private String field2;
    private String field3;

    public SomeBean(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getfield1() {
        return field1;
    }

    public void setfield1(String field1) {
        this.field1 = field1;
    }

    public String getfield2() {
        return field2;
    }

    public void setfield2(String field2) {
        this.field2 = field2;
    }

    public String getfield3() {
        return field3;
    }

    public void setfield3(String field3) {
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "SomeBean{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3='" + field3 + '\'' +
                '}';
    }
}
