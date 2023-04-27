package com.gkonell.badmovies.component;

import com.gkonell.badmovies.model.IndicatedMovie;
import com.gkonell.badmovies.service.FileService;
import com.gkonell.badmovies.service.IndicatedMovieService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;

@Component
public class ReadMovieListComponent {

    private static final Logger logger = LoggerFactory.getLogger(ReadMovieListComponent.class);

    @Value( "${file.movieList.path}" )
    private String movieListFileName;

    @Autowired
    private FileService<IndicatedMovie> fileService;

    @Autowired
    private IndicatedMovieService indicatedMovieService;

    @PostConstruct
    public void readMovieList(){
        try {
            List<IndicatedMovie> indicatedMovies = fileService.parseCsvFileToObject(movieListFileName, IndicatedMovie.class);
            indicatedMovieService.insertMovies(indicatedMovies);

            logger.info("The list of indicated movies has been inserted into the database!");
        } catch (FileNotFoundException e) {
            logger.warn("Movie list file not found!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
