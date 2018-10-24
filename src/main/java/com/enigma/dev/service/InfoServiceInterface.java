package com.enigma.dev.service;

import com.enigma.dev.model.InfoDto;
import com.enigma.dev.model.InfoEntity;

import java.util.List;

public interface InfoServiceInterface {
   /* public void saveInfo(InfoDto infoDto);*/
   public void saveInfo(List<String[]> records);

    public List<InfoEntity> getAllInfo();

    public boolean deleteInfo(int rowId);

    public boolean editInfo(int rowId, InfoDto infoDto);
}
