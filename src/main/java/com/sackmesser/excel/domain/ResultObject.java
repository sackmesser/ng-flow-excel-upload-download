package com.sackmesser.excel.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: Diogo
 * Date: 06/10/14
 * Time: 15:48
 * To change this template use File | Settings | File Templates.
 */

@Data
@AllArgsConstructor
public class ResultObject {
    public static final String RETURN_TYPE_SUCCESS = "SUCCESS";
    public static final String RETURN_TYPE_ERROR = "ERROR";
    private String status;
    private Object object;
}
