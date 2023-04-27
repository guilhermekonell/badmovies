package com.gkonell.badmovies.service;

import com.gkonell.badmovies.model.IndicatedMovie;
import com.gkonell.badmovies.repository.IndicatedMovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IndicatedMovieService {

    private final IndicatedMovieRepository movieRepository;

    public void insertMovies(List<IndicatedMovie> indicatedMovies) {
        movieRepository.saveAll(indicatedMovies);
    }

    public List<IndicatedMovie> getIndicatedMovieWinners() {
        return movieRepository.findByWinnerTrueOrderByIndicatedYearAscProducersAsc();
    }

}
