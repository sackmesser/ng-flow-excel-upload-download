package com.sackmesser.tests.service;

import com.sackmesser.excel.domain.ErrorMessage;
import com.sackmesser.excel.domain.ResultObject;
import com.sackmesser.excel.service.ExcelUploadAbstractService;
import com.sackmesser.tests.dao.PlayerDao;
import com.sackmesser.tests.domain.Player;
import com.sackmesser.tests.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Diogo
 * Date: 07/10/14
 * Time: 11:42
 */
@Service
public class PlayerService extends ExcelUploadAbstractService<Player> {
    @Autowired
    private PlayerDao playerDao;

    public PlayerService() {
        super(Player.class);
    }

    @Override
    public List<Player> process(List<Player> rows) {
        /*for(Player row : rows){
            playerRepository.save(row);
            System.out.println("Read: " + row.toString());
        }*/
        playerDao.mergeAll(rows);

        return rows;
    }

    @Override
    public ResultObject createResultObject(List<Player> rows, List<ErrorMessage> errors) {
        if(!errors.isEmpty()){
            return new ResultObject(ResultObject.RETURN_TYPE_ERROR, errors);
        }
        return new ResultObject(ResultObject.RETURN_TYPE_SUCCESS, null);
    }

    public List<Player> listAll(){
        return playerDao.listAll();
    }

}
