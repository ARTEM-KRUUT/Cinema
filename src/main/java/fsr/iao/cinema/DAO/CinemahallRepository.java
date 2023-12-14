package fsr.iao.cinema.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fsr.iao.cinema.entities.Сinemahall;
@RepositoryRestResource
@CrossOrigin(origins = "*", allowedHeaders = "*")

public interface CinemahallRepository extends JpaRepository<Сinemahall,Long> {

}
