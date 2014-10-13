package com.sackmesser.tests.web;

import com.sackmesser.excel.service.ExcelDownloadService;
import com.sackmesser.excel.service.IFileDownloadService;
import com.sackmesser.excel.service.IFileUploadService;
import com.sackmesser.excel.web.ExcelAbstractController;
import com.sackmesser.tests.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Diogo
 * Date: 08/10/14
 * Time: 16:00
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/player")
public class PlayerController extends ExcelAbstractController {
    @Autowired
    PlayerService playerService;

    @Autowired
    ExcelDownloadService excelDownloadService;

    @Override
    protected IFileUploadService getFileUploadService() {
        return playerService;
    }

    @Override
    protected IFileDownloadService getFileDownloadService() {
        return excelDownloadService;
    }

    @Override
    protected List<Object> filterDataToDownload(Map filter) {
        return findAll();
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public List findAll(){
        return playerService.listAll();
    }
}
