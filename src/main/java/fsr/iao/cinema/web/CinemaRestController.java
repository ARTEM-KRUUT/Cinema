package fsr.iao.cinema.web;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;





import javax.transaction.Transactional;

import lombok.Data;






















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;








import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fsr.iao.cinema.DAO.ContactRepository;
import fsr.iao.cinema.DAO.FilmRepository;
import fsr.iao.cinema.DAO.TicketRepository;
import fsr.iao.cinema.entities.Contact;
import fsr.iao.cinema.entities.Film;
import fsr.iao.cinema.entities.Ticket;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CinemaRestController {
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private ContactRepository contactRepository;
	@GetMapping("/listFilms")
	public List<Film>listFilms(){
		
		return filmRepository.findAll();
	}

	@GetMapping(path="/imageCinema/{id}", produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] uploadImageCenima(@PathVariable(name="id") String id) throws IOException {
		// Указываем новый путь к изображению
		String imagePath = "cinema/images/" + id;

		File file = new File(imagePath);
		Path path = Paths.get(file.toURI());

		return Files.readAllBytes(path);
	}

	@Transactional
	@PostMapping(path="/payerTickets")
	public List<Ticket> payerTickets(@RequestBody TicketBody ticketBody)
	{  List<Ticket> listTickets= new ArrayList<Ticket>();	
		ticketBody.getListTickets().forEach(idTicket->{
			Ticket ticket=ticketRepository.findById(idTicket).get();
			ticket.setNumClient(ticketBody.getNameClient());
			ticket.setReserve(true);
			ticket.setCodePayement(ticketBody.getCodePayement());
			ticketRepository.save(ticket);
			listTickets.add(ticket);
			
		});
		return listTickets;
	}
	
	@PostMapping(path="/addContact")
	public Contact addContact(@RequestBody Contact contact){
		Contact c=contactRepository.save(contact);
		return  c;
	}

	@GetMapping(path="/imageFilm/{id}", produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] uploadImage(@PathVariable(name="id") Long id) throws IOException {
		Film film = filmRepository.findById(id).orElse(null);

		if (film == null) {
			// Обработка случая, когда фильм с указанным id не найден
			return new byte[0]; // или можно выбросить исключение, вернуть HTTP 404 и т.д.
		}

		String nameImage = film.getPhoto();
		// Указываем новый путь к изображению
		String imagePathFilm = "cinema/images/" + nameImage;
		File file = new File(imagePathFilm);
		Path path = Paths.get(file.toURI());

		return Files.readAllBytes(path);
	}


	public static JsonNode stringToJSONObject(String jsonString) throws Exception {
	    ObjectMapper jacksonObjMapper = new ObjectMapper();
	    return jacksonObjMapper.readTree(jsonString);
	}

	@PostMapping("/upload")
	@ResponseBody
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("info") String info) throws Exception {
		JsonNode jsonNode = stringToJSONObject(info);
		System.out.println(jsonNode.get("titre").asText());

		System.out.println(info);
		String str = file.getOriginalFilename().trim();
		str = str.substring(str.indexOf("."), str.length());

		// Указываем новый путь для сохранения файла
		String destinationFilename = "cinema/images/" + jsonNode.get("title").asText() + str;

		try {
			Files.copy(file.getInputStream(), Paths.get(destinationFilename), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return destinationFilename + " sf t uploada ";
	}

	@PostMapping(path="/addFilm")
	public Film addFilm(@RequestBody Film film){

		Film f= filmRepository.save(film);
		
		return f;
	}
	

    @PutMapping(value = "/updateFilm")
    public Film updateVehicle(@RequestBody Film Film){
		filmRepository.save(Film);
        return Film;
    }


   @RequestMapping(value="/deleteFilm/{id}",method=RequestMethod.DELETE)
   public void deleteFilm(@PathVariable Long id){
	   filmRepository.deleteById(id);

   }


}

@Data
class TicketBody{
	private Integer  codePayement;
	private String nameClient;
	private List<Long> listTickets=new ArrayList<Long>();
}

