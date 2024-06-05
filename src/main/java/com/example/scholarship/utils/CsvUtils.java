package com.example.scholarship.utils;

import com.example.scholarship.model.Student;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class CsvUtils {

    public static List<Student> read(Class<Student> clazz, InputStream inputStream) throws IOException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            HeaderColumnNameMappingStrategy<Student> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(clazz);

            CsvToBean<Student> csvToBean = new CsvToBeanBuilder<Student>(reader)
                    .withMappingStrategy(strategy)
                    .build();
            return csvToBean.parse();
        }
    }
}
