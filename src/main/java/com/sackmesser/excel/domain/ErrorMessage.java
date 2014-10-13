package com.sackmesser.excel.domain;

import lombok.*;

/**
 * Created with IntelliJ IDEA.
 * User: Diogo
 * Date: 07/10/14
 * Time: 16:06
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private String message;
    private int row;
}
