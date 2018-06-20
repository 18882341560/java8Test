package com.gl.java8test;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * create gl  2018/6/18
 **/
@Data
@AllArgsConstructor
public class User {
    private String name;
    private Integer age;
    private String sex;
    private Status status;

   public User(String name){
        this.name=name;
    }
   public User(){}

    public User(String name, Integer age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public enum Status{
       FREE,
       BUSY,
       VOCATION;
   }

}
