package com.sackmesser.excel.service;

import com.sackmesser.excel.domain.ErrorMessage;
import com.sackmesser.excel.domain.ResultObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.excel.RowCallbackHandler;
import org.springframework.batch.item.excel.Sheet;
import org.springframework.batch.item.excel.mapping.DefaultRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Diogo
 * Date: 03/10/14
 * Time: 16:52
 */
@Service
@Slf4j
public abstract class ExcelUploadAbstractService<E> implements IFileUploadService<E> {
    private Class<E> classType;

    public ExcelUploadAbstractService(Class<E> classType){
        this.classType = classType;
    }

    @Override
    public ResultObject execute(InputStream inputStream) throws Exception {

        PoiItemReader<E> itemReader = configureItemReader(inputStream);
        E row = classType.newInstance(); // instantiate to avoid problems when there's an error on the first line
        List<E> list = new ArrayList<>();
        List<ErrorMessage> errors = new ArrayList<>();
        do {
            try{
                row = itemReader.read();
                if (row != null){
                    list.add(row);
                }
            }catch(RuntimeException e){
                errors.add(new ErrorMessage(e.getCause().toString(), itemReader.getCurrentRowIndex()+ 1));
            }
        } while (row != null);

        if(errors.isEmpty()){
            process(list);
        }
        ResultObject resultObject = createResultObject(list, errors);
        return resultObject;
    }

    private PoiItemReader<E> configureItemReader(InputStream inputStream) throws Exception {
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
        PoiItemReader<E> itemReader = new PoiItemReader<>();
        itemReader.setLinesToSkip(1); // First line is skipped
        itemReader.setResource(inputStreamResource);
        itemReader.setRowMapper(new DefaultRowMapper(classType));

        itemReader.setSkippedRowsCallback(new RowCallbackHandler() {
            public void handleRow(final Sheet sheet, final String[] row) {
                log.info("Skipping: " + StringUtils.arrayToCommaDelimitedString(row));
            }
        });
        itemReader.afterPropertiesSet();
        itemReader.open(new ExecutionContext());
        return itemReader;
    }
}
