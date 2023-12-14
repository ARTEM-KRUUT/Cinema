package fsr.iao.cinema.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;


















import javax.transaction.Transactional;

import fsr.iao.cinema.DAO.*;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;


import fsr.iao.cinema.entities.Category;
import fsr.iao.cinema.entities.Cinema;
import fsr.iao.cinema.entities.Film;
import fsr.iao.cinema.entities.Place;
import fsr.iao.cinema.entities.Projection;
import fsr.iao.cinema.entities.Сinemahall;
import fsr.iao.cinema.entities.Seance;
import fsr.iao.cinema.entities.Ticket;
import fsr.iao.cinema.entities.City;

@Service
@Transactional
public class CinemaInitServiceImpl  implements ICinemaInitService{
@Autowired	
private CityRepository cityRepository;
	@Override
	public void initCities() {
		// TODO Auto-generated method stub
		Stream.of("Moscow","St.Petersburg","Sochi","Kazan").forEach(nameCity->{
			City city =new City();
			city.setName(nameCity);
			cityRepository.save(city);
		});;


	}
@Autowired
private CinemaRepository cinemaRepository;
	@Override
	public void initCinemas() {
		cityRepository.findAll().stream().forEach(city->{
			Stream.of("SuperCinema "+city.getName(),"Cinemastar "+city.getName(),"Cinemacool "+city.getName()).forEach(
				nameCinema->{
					Cinema cinema=new Cinema();
					cinema.setName(nameCinema);
					cinema.setNumberCinemahalls(3+(int)(Math.random()*7));
					cinema.setCity(city);
					cinemaRepository.save(cinema);
				});
		});
	}
@Autowired
private CinemahallRepository cinemahallRepository;
	@Override
	public void initCinemahall() {
		cinemaRepository.findAll().stream().forEach(cinema->{
			IntStream.range(0,1).forEach(i->{
				Сinemahall cinemahall = new Сinemahall();
				cinemahall.setName(" cinemahall "+i+1);
				cinemahall.setCinema(cinema);
				cinemahall.setNumberCinemahall(15+(int)(Math.random()*20));
				cinemahallRepository.save(cinemahall);
					}
					);
		});

	}
@Autowired
private PlaceRepository placeRepository;
	@Override
	public void initPlaces() {
		cinemahallRepository.findAll().stream().forEach(cinemahall->{
			IntStream.range(0,1).forEach(i->{
				Place place = new Place();
				place.setNumber(i+1);
				place.setCinemahall(cinemahall);
				placeRepository.save(place);
					}
					);
		});
	}
@Autowired
private SeanceRepository seanceRepository;
	@Override
	public void initSeances() {
		// TODO Auto-generated method stub
		DateFormat dateFormat=new SimpleDateFormat("HH:mm");

		Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s->{
			Seance seance=new Seance();
			try {
				seance.setStartTime(dateFormat.parse(s));
				seanceRepository.save(seance);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
@Autowired
private CategoryRepository categoryRepository;
	@Override
	public void initCategories() {
		// TODO Auto-generated method stub
		Stream.of("Action","Drama","Fiction","Histoire").forEach(cat->{
			Category categorie=new Category();
			categorie.setName(cat);
			categoryRepository.save(categorie);
		});
	}
@Autowired
private FilmRepository filmRepository;
	@Override
	public void initFilms() {
		// TODO Auto-generated method stub
		double[] duration=new double[]{1,1.5,2,2.5,3};
		List<Category> categories=categoryRepository.findAll();
		Stream.of("Game Of Thrones","Spider Man","Batman","Titanic","Cat Women","Super Man").forEach(nameFilm->{
			Film  film =new Film();
			film.setTitle(nameFilm);
			film.setDuration(duration[(int)Math.random()*duration.length]);
			film.setPhoto(nameFilm.replaceAll(" ", "")+".jpg");
			film.setCategory(categories.get(new Random().nextInt(categories.size())));
			filmRepository.save(film);
		});

	}
@Autowired
private ProjectionRepository projectionRepository;
	@Override
	public void initProjections() {
		// TODO Auto-generated method stub
		double[] prices=new double[]{50,60,70,80};
		List<Film> films=filmRepository.findAll();

		cityRepository.findAll().forEach(city->{
			city.getCinemas().forEach(cinema->{
				cinema.getCinemahall().forEach(cinemahall->{
					//filmRepository.findAll().forEach(film->{
						int index=new Random().nextInt(films.size());
						Film film=films.get(index);
						seanceRepository.findAll().forEach(seance->{
							Projection projection=new Projection();
							projection.setDateProjection(new Date());
							projection.setFilm(film);
							projection.setPrice(prices[new Random().nextInt(prices.length)]);
							projection.setCinemahall(cinemahall);
							projection.setSeance(seance);
							projectionRepository.save(projection);
						});
					//});
				});
			});
		});

	}
@Autowired
private TicketRepository ticketRepository;
	@Override
	public void initTickets() {
		// TODO Auto-generated method stub
		projectionRepository.findAll().forEach(projection->{
			projection.getCinemahall().getPlaces().forEach(cinemahall->{
				Ticket ticket=new Ticket();
				ticket.setPlaces(cinemahall);
				ticket.setProjection(projection);
				ticket.setPrice(projection.getPrice());
				ticket.setReserve(false);
				ticketRepository.save(ticket);

			});
		});

	}

}
