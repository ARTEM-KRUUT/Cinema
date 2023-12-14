package fsr.iao.cinema.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="p1",types={fsr.iao.cinema.entities.Projection.class})
public interface ProjectionProj {
public Long getId();
public double getPrice();
public Date getDateProjection();
public Сinemahall getCinemahall();
public Film getFilm();
public Seance getSeance();
public Collection<Ticket> getTickets();

}
