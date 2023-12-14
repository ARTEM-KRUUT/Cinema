package fsr.iao.cinema.entities;
import org.springframework.data.rest.core.config.Projection;

@Projection(name="ticketProj",types={Ticket.class})
public interface TicketsProj {
	public Long getId();
	public String getNumClient();
	public double getPrice();
	public Integer getCodePayement();
	public boolean getReserve();
	public Place getCinemahall();
	public fsr.iao.cinema.entities.Projection getProjection();
	
}
