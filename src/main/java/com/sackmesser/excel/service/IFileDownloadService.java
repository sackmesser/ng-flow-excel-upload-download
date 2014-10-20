package com.sackmesser.excel.service;

import javax.xml.crypto.OctetStreamData;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Diogo
 * Date: 09/10/14
 * Time: 10:39
 */
public interface IFileDownloadService {
    byte[] writeToFile(List<Object> objects) throws Exception;
}
