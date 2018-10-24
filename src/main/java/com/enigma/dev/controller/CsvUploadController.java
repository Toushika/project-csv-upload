package com.enigma.dev.controller;

import com.enigma.dev.model.InfoDto;
import com.enigma.dev.model.InfoEntity;
import com.enigma.dev.service.InfoService;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RestController
public class CsvUploadController {
    private final Logger logger = LoggerFactory.getLogger(CsvUploadController.class);

    @Autowired
    private InfoService infoService;

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F://csv//";
    private static final String SAMPLE_CSV_FILE_PATH = "F://csv//esd.csv";

    @PostMapping("/csvUpload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile uploadfile, InfoDto infoDto) {

        logger.debug("Single file upload!");

        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }


        try {
            saveUploadedFiles(Arrays.asList(uploadfile), infoDto);
          /*  Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());*/

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Successfully uploaded - " +
                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

    }

    //save file
/*    private void saveUploadedFiles(List<MultipartFile> files, InfoDto infoDto) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);


            try (

                    Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                    CSVReader csvReader = new CSVReader(reader);
            ) {
                List<String[]> records = csvReader.readAll();
                for (String[] record : records) {

                    infoDto.setName(record[0]);
                    infoDto.setEmail(record[1]);
                    infoDto.setPhone(record[2]);

                    System.out.println("Name : " + record[0]);
                    System.out.println("Email : " + record[1]);
                    System.out.println("Phone : " + record[2]);

                    infoService.saveInfo(infoDto);
                }
            }
        }

    }*/


    private void saveUploadedFiles(List<MultipartFile> files, InfoDto infoDto) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);


            try (

                    Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
                    CSVReader csvReader = new CSVReader(reader);
            ) {
                List<String[]> records = csvReader.readAll();

                    infoService.saveInfo(records);
                }
            }
        }


    @PostMapping("/geAllInfo")
    public ResponseEntity<?> getAllInfo(HttpServletRequest request) {

        List<InfoEntity> infoEntityList = infoService.getAllInfo();
        logger.info("csvUploadController:: getAllInfo::");

        return new ResponseEntity(infoEntityList, HttpStatus.OK);
    }

    @PostMapping("/deleteInfo/{rowId}")
    public ResponseEntity<?> deleteInfo(@PathVariable("rowId") int rowId,
                                        HttpServletRequest request) {
        logger.info("CsvUploadController:: deleteInfo:: delete ");
        infoService.deleteInfo(rowId);
        return new ResponseEntity("delete tested", HttpStatus.OK);
    }

    @PostMapping("/editInfo/{rowId}")
    public ResponseEntity<?> editMovieInfo(@PathVariable("rowId") int rowId, @RequestBody InfoDto infoDto) {
        logger.info("CsvUploadController :: editInfo :: " + infoDto.toString());
        infoService.editInfo(rowId, infoDto);
        return new ResponseEntity("", HttpStatus.OK);
    }


}
