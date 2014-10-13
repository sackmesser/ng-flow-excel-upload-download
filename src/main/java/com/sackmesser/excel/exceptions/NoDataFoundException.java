package com.sackmesser.excel.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: Diogo
 * Date: 09/10/14
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(){
        super("No data found");
    }
}
