package com.example.demorosenwald.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Data
public class SchoolData{

    private String UID;

    private String State;

    private String County;

    private String schoolNames;

    private String alternateNames;

    private String appl;

    private String originalDate;

    private String Title;

    private String Subject;

    private String Description;

    private String Creator;

    private String Source;

    private String Publisher;

    private String Rights;

    private String Format;

    private String schoolCardId;

    private String photo_id;

    //
    private ArrayList<String> listUID;


}
