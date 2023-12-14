package fsr.iao.cinema.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import fsr.iao.cinema.entities.Cinema;
import fsr.iao.cinema.entities.Ticket;
@CrossOrigin(origins = "*", allowedHeaders = "*")

public interface TicketRepository  extends JpaRepository<Ticket,Long> {

}
