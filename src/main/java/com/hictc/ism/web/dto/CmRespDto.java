package com.hictc.ism.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CmRespDto<T> {
    private Integer code;
    private String message;
    private T Data;

}
