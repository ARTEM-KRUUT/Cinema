package fsr.iao.cinema.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fsr.iao.cinema.entities.Category;
import fsr.iao.cinema.entities.Cinema;
@RepositoryRestResource
@CrossOrigin(origins = "*", allowedHeaders = "*")

public interface CategoryRepository  extends JpaRepository<Category,Long> {

}
