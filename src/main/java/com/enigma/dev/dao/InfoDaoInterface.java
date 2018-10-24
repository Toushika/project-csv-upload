package com.enigma.dev.dao;

import com.enigma.dev.model.InfoDto;
import com.enigma.dev.model.InfoEntity;

import java.util.List;

public interface InfoDaoInterface {
   /* public boolean saveInfo(InfoEntity infoEntity);*/

    public boolean saveInfo(List<String[]> records);

    public List<InfoEntity> getAllInfo();

    public boolean deleteInfo(int rowId);

    public boolean editInfo(InfoEntity infoEntity);
}
