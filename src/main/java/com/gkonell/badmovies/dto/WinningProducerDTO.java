package com.gkonell.badmovies.dto;

import lombok.Data;

@Data
public class WinningProducerDTO {

    String producer;
    Integer previousWin;
    Integer followingWin;
    Integer interval;

}
