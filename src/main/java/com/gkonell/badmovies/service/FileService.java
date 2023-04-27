package com.gkonell.badmovies.service;

import com.gkonell.badmovies.model.IndicatedMovie;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class FileService<T> {

    public List<T> parseCsvFileToObject(String pathFile, Class<T> type) throws FileNotFoundException {
        File file = new File(pathFile);
        BufferedReader buffer = new BufferedReader(new FileReader(file.getPath()));

        CsvToBean<T> csvReader = new CsvToBeanBuilder<T>(buffer)
                .withType(type)
                .withSeparator(';')
                .build();

        return csvReader.parse();
    }

}
