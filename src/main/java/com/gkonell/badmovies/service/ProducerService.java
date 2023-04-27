package com.gkonell.badmovies.service;

import com.gkonell.badmovies.dto.WinnersIntervalDTO;
import com.gkonell.badmovies.dto.WinningProducerDTO;
import com.gkonell.badmovies.model.IndicatedMovie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProducerService {

    private final IndicatedMovieService indicatedMovieService;

    public WinnersIntervalDTO getMinMaxIntervalsWinners() {
        List<IndicatedMovie> indicatedMovieWinners = indicatedMovieService.getIndicatedMovieWinners();

        return getIntervalsWinners(indicatedMovieWinners);
    }

    public WinnersIntervalDTO getIntervalsWinners(List<IndicatedMovie> indicatedMovieWinners) {
        List<WinningProducerDTO> producerWinnerIntervals = getProducerWinnerIntervals(indicatedMovieWinners);

        List<WinningProducerDTO> producerWinnerMaxInterval = getProducerWinnerMaxInterval(producerWinnerIntervals);
        List<WinningProducerDTO> producerWinnerMinInterval = getProducerWinnerMinInterval(producerWinnerIntervals);

        return new WinnersIntervalDTO(producerWinnerMinInterval, producerWinnerMaxInterval);
    }

    private List<WinningProducerDTO> getProducerWinnerIntervals(List<IndicatedMovie> indicatedMovieWinners) {
        LinkedHashMap<String, List<IndicatedMovie>> producerWinners = new LinkedHashMap<>();
        indicatedMovieWinners.forEach(indicatedMovie ->
                indicatedMovie.getProducersList().forEach(producer ->
                        producerWinners.computeIfAbsent(producer, m -> new LinkedList<>()).add(indicatedMovie)
                )
        );

        List<WinningProducerDTO> winningProducersDTO = new ArrayList<>();
        producerWinners.forEach((s, indicatedMovies) -> {
            if (indicatedMovies.size() >= 2) {
                indicatedMovies.sort(Comparator.comparing(IndicatedMovie::getIndicatedYear));

                for (int i = 1; i < indicatedMovies.size(); i++) {
                    WinningProducerDTO winningProducerDTO = new WinningProducerDTO();
                    winningProducerDTO.setProducer(s);
                    winningProducerDTO.setPreviousWin(indicatedMovies.get(i-1).getIndicatedYear());
                    winningProducerDTO.setFollowingWin(indicatedMovies.get(i).getIndicatedYear());
                    winningProducerDTO.setInterval(indicatedMovies.get(i).getIndicatedYear() - indicatedMovies.get(i-1).getIndicatedYear());
                    winningProducersDTO.add(winningProducerDTO);
                }
            }
        });

        return winningProducersDTO;
    }

    private List<WinningProducerDTO> getProducerWinnerMaxInterval(List<WinningProducerDTO> producerWinnerIntervals) {
        Optional<Integer> maxInterval = producerWinnerIntervals.stream()
                .map(WinningProducerDTO::getInterval)
                .max(Integer::compareTo);
        return producerWinnerIntervals.stream().filter(winningProducerDTO ->
                Objects.equals(winningProducerDTO.getInterval(), maxInterval.get())
        ).toList();
    }

    private List<WinningProducerDTO> getProducerWinnerMinInterval(List<WinningProducerDTO> producerWinnerIntervals) {
        Optional<Integer> minInterval = producerWinnerIntervals.stream()
                .map(WinningProducerDTO::getInterval)
                .min(Integer::compareTo);
        return producerWinnerIntervals.stream().filter(winningProducerDTO ->
                Objects.equals(winningProducerDTO.getInterval(), minInterval.get())
        ).toList();
    }

}
