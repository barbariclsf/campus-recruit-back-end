package com.campusrecruit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMap<T> {

    private Integer code;
    private String message;
    private String result;
    private T data;
    private List<T> arrayList;
    private String sessionId;

    public ResultMap(Integer code, String result, T data) {
        this.code = code;
        this.result = result;
        this.data = data;
    }


}
