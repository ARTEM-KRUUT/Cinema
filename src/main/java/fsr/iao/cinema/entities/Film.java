package fsr.iao.cinema.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Film implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id ;
private String title;
private double duration;
private String director;
private String description;
private String photo;
private Date reliaseDate;
@OneToMany(mappedBy="film")
@JsonProperty(access=Access.WRITE_ONLY)
private Collection<Projection> projections;
@ManyToOne
private Category category;

private String videoUrl;


	@Column(length = 1000) // Adjust the length based on your needs
	private String imageUrl;
}
