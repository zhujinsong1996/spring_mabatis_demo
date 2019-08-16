package com.qf.dto;

import java.util.Arrays;

public class BarDemoDTO {
    String name;
    String type= "bar";
    int[] data = {};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BarDemoDTO{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
