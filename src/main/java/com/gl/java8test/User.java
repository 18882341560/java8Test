package com.gl.java8test;

import lombok.Data;

/**
 * create gl  2018/6/18
 **/
@Data
public class User {
    private String name;
    private Integer age;
    private String sex;

   public User(String name){
        this.name=name;
    }
   public User(){}
}
