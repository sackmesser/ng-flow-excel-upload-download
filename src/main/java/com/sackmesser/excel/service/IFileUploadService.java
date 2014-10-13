package com.sackmesser.excel.service;

import com.sackmesser.excel.domain.ErrorMessage;
import com.sackmesser.excel.domain.ResultObject;

import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Diogo
 * Date: 03/10/14
 * Time: 16:39
 */
public interface IFileUploadService<E> {
    ResultObject execute(InputStream inputStream) throws Exception;
    List<E> process(List<E> rows);
    ResultObject createResultObject(List<E> rows, List<ErrorMessage> errors);
}
