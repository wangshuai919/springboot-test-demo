package com.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class JsonModel {
    private int page;
    private int total;
    private int records;
    private List rows;
    private String companyId;

}
