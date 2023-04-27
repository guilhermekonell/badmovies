package com.gkonell.badmovies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WinnersIntervalDTO {

    List<WinningProducerDTO> min;
    List<WinningProducerDTO> max;

}
