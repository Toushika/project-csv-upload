package com.enigma.dev.service;

import com.enigma.dev.dao.InfoDao;
import com.enigma.dev.model.InfoDto;
import com.enigma.dev.model.InfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService implements InfoServiceInterface {
    private static Logger logger = LoggerFactory.getLogger(InfoService.class);
    @Autowired
    private InfoDao infoDao;
    @Override
  /*  public void saveInfo(InfoDto infoDto) {
        InfoEntity infoEntity =new InfoEntity();
        infoEntity.setName(infoDto.getName());
        infoEntity.setEmail(infoDto.getEmail());
        infoEntity.setPhone(infoDto.getPhone());
        infoDao.saveInfo(infoEntity);

    }*/

    public void saveInfo(List<String[]> records) {
        infoDao.saveInfo(records);

    }

    public List<InfoEntity> getAllInfo(){
        return infoDao.getAllInfo();

    }

    public boolean deleteInfo(int rowId){
        return infoDao.deleteInfo(rowId);
    }

    public boolean editInfo(int rowId,InfoDto infoDto){
        InfoEntity infoEntity = new InfoEntity();
        infoEntity.setRowId(rowId);
        infoEntity.setName(infoDto.getName());
        infoEntity.setEmail(infoDto.getEmail());
        infoEntity.setPhone(infoDto.getPhone());
        logger.info("InfoService::editInfo::"+infoEntity.toString());
        return infoDao.editInfo(infoEntity);

    }
}
