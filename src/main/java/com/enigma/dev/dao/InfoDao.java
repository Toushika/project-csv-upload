package com.enigma.dev.dao;

import com.enigma.dev.model.InfoDto;
import com.enigma.dev.model.InfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Transactional

public class InfoDao implements InfoDaoInterface {
    private static Logger logger = LoggerFactory.getLogger(InfoDaoInterface.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
   /* public boolean saveInfo(InfoEntity infoEntity) {
        boolean isSaved = false;
        try {
            this.entityManager.persist(infoEntity);
            logger.info("InfoDao::saveInfo::data has been instereted");
            isSaved = true;

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("InfoDao::saveInfo::problem in insertion");
        }
        return isSaved;

    }*/

    public boolean saveInfo(List<String[]> records) {
        boolean isSaved = false;
        InfoEntity infoEntity = new InfoEntity();
        List<String> list = new ArrayList<String>();


        try {


            for (String[] record : records) {
              list.add(record[0]);
                list.add(record[1]);
                list.add(record[2]);

                InfoEntity infoEntity1 = new InfoEntity(record[0],record[1],record[2]);

              /*  infoEntity1 = entityManager.find(InfoEntity.class,1L);
*/
                this.entityManager.persist(infoEntity1);
                logger.info("InfoDao::saveInfo:: insertion done");
                logger.info("InfoDao::saveInfo::"+infoEntity1.toString());


            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("InfoDao::saveInfo::problem in insertion");
        }


        return isSaved;

    }

    @Override
    public List<InfoEntity> getAllInfo() {
        List<InfoEntity> infoEntityList = null;
        String qrystr = "SELECT * FROM `detail_info";
        try {
            Query query = this.entityManager.createNativeQuery(qrystr, InfoEntity.class);
            infoEntityList = (List<InfoEntity>) query.getResultList();
            logger.info("InfoDao::getAllInfo");

        } catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println(infoEntityList);
        return infoEntityList;

    }

    @Override
    public boolean deleteInfo(int rowId) {

        String qrystr = "DELETE FROM `detail_info` WHERE `row_id`=?1";
        boolean isDelete = false;
        try {

            Query query = this.entityManager.createNativeQuery(qrystr);
            query.setParameter(1, rowId);
            query.executeUpdate();
            isDelete = true;
            logger.info("Data Has been deleted");

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error in deleting");

        }
        return isDelete;


    }

    public boolean editInfo(InfoEntity infoEntity) {
        List<InfoEntity> infoEntityList = null;
        String qrystr = "SELECT * from `detail_info` WHERE `row_id`=?1";//+movieEntity.getRowId();
//        logger.info("MovieDao::saveMovie:: qrystr \n"+qrystr);
        logger.info("InfoDao::editInfo:: \n" + infoEntity.toString());
        boolean isEdited = false;
        try {
            Query query = this.entityManager.createNativeQuery(qrystr, InfoEntity.class);
            query.setParameter(1, infoEntity.getRowId() + "");

            infoEntityList = (List<InfoEntity>) query.getResultList();

            InfoEntity infoEntityOriginal = infoEntityList.get(0);
            infoEntityOriginal.setName(infoEntity.getName());
            infoEntityOriginal.setEmail(infoEntity.getEmail());
            infoEntityOriginal.setPhone(infoEntity.getPhone());

            isEdited = true;
            logger.info("InfoDao::editInfo::data has been updated " + infoEntity.toString());


        } catch (Exception e) {
            e.printStackTrace();
            logger.info("InfoDao::editInfo::problem in update");
        }
        return isEdited;
    }

}
