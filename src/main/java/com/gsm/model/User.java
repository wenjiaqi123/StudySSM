package com.gsm.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private int id;
    private String name;
    private int age;
    private String info;
    private String createDate;
}
