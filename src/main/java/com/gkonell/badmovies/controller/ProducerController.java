package com.gkonell.badmovies.controller;

import com.gkonell.badmovies.dto.WinnersIntervalDTO;
import com.gkonell.badmovies.service.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
@AllArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping("/min-max-interval-winners")
    public WinnersIntervalDTO getMinMaxIntervalWinners() {
        return producerService.getMinMaxIntervalsWinners();
    }

}
