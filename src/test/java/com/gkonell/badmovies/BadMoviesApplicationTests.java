package com.gkonell.badmovies;

import com.gkonell.badmovies.dto.WinnersIntervalDTO;
import com.gkonell.badmovies.model.IndicatedMovie;
import com.gkonell.badmovies.service.FileService;
import com.gkonell.badmovies.service.ProducerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BadMoviesApplicationTests {

	@Value( "${file.movieList.path}" )
	private String movieListFileName;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private FileService<IndicatedMovie> fileService;

	@Autowired
	private ProducerService producerService;

	@Test
	void shouldGetMinMaxIntervalWinners() throws FileNotFoundException {
		ResponseEntity<WinnersIntervalDTO> response = testRestTemplate.getForEntity("/producer/min-max-interval-winners", WinnersIntervalDTO.class);

		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertNotNull(response.getBody());

		List<IndicatedMovie> indicatedMovies = getIndicatedMoviesCsvFile();
		List<IndicatedMovie> indicatedMovieWinners = indicatedMovies.stream().filter(IndicatedMovie::getWinner).collect(Collectors.toList());
		WinnersIntervalDTO intervalsWinners = producerService.getIntervalsWinners(indicatedMovieWinners);

		assertNotNull(intervalsWinners);
		assertEquals(response.getBody().getMin(), intervalsWinners.getMin());
		assertEquals(response.getBody().getMax(), intervalsWinners.getMax());
	}
	
	private List<IndicatedMovie> getIndicatedMoviesCsvFile() throws FileNotFoundException {
		return fileService.parseCsvFileToObject(movieListFileName, IndicatedMovie.class);
	}

}
