package fsr.iao.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


import fsr.iao.cinema.entities.Category;
import fsr.iao.cinema.entities.Cinema;
import fsr.iao.cinema.entities.Film;
import fsr.iao.cinema.entities.Сinemahall;
import fsr.iao.cinema.entities.City;
import fsr.iao.cinema.service.ICinemaInitService;


@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
	@Autowired
    private ICinemaInitService cinemaInitService;
	@Autowired
	private RepositoryRestConfiguration  restConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		restConfiguration.exposeIdsFor(City.class,Cinema.class, Сinemahall.class,Film.class, Category.class);
	

		cinemaInitService.initCities();
		cinemaInitService.initCinemas();
		cinemaInitService.initCinemahall();
		cinemaInitService.initPlaces();
		cinemaInitService.initSeances();
		cinemaInitService.initCategories();
		cinemaInitService.initFilms();
		cinemaInitService.initProjections();
		cinemaInitService.initTickets();
		
	}
	

}
