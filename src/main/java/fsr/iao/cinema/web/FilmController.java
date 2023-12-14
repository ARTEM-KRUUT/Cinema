package fsr.iao.cinema.web;

import fsr.iao.cinema.DAO.FilmRepository;
import fsr.iao.cinema.entities.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/allfilms")
    public String getAllMovies() {
        return "films";
    }

    @GetMapping("/{id}")
    public Film getMovieById(@PathVariable Long id) {
        return filmRepository.findById(id).orElse(null);
    }
}



