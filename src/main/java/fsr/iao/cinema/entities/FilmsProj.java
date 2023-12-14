package fsr.iao.cinema.entities;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="fl",types={fsr.iao.cinema.entities.Film.class})

public interface FilmsProj {
public Long getId();
public String getTitle();
public double detDuration();
public  String getDirector();
public String getDescription();
public String getPhoto();
public Date getReliaseDate();
public Category getCategory();
public String getVideoUrl();
}
