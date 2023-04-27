package com.gkonell.badmovies.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class IndicatedMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @CsvBindByName(column = "year")
    Integer indicatedYear;

    @CsvBindByName(column = "title")
    String title;

    @CsvBindByName(column = "studios")
    String studios;

    @CsvBindByName(column = "producers")
    String producers;

    @CsvBindByName(column = "winner")
    Boolean winner = false;

    public List<String> getProducersList() {
        return Arrays.stream(producers.split("(,)|(, and )|( and )"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

}
