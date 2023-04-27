package com.gkonell.badmovies.repository;

import com.gkonell.badmovies.model.IndicatedMovie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndicatedMovieRepository extends CrudRepository<IndicatedMovie, Integer> {

    List<IndicatedMovie> findByWinnerTrueOrderByIndicatedYearAscProducersAsc();

}
