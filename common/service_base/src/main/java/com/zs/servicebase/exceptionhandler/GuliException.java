package com.zs.servicebase.exceptionhandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 *
 * @author zhousheng
 * @date 2020年 07月08日 11:13:21
 */
@Data
@AllArgsConstructor  //lombok生成有参数构造方法
@NoArgsConstructor   //lombok生成无参数构造
public class GuliException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;//状态码
    private String msg;//异常信息

}