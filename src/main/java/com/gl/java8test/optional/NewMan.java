package com.gl.java8test.optional;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

/**
 * create gl  2018/6/27
 **/
@Data
@AllArgsConstructor
public class NewMan {
    private Optional<Godness> godness = Optional.empty(); // 一定要得到一个空的容器,而不是null
    public NewMan() {
    }
}
