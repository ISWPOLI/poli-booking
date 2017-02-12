package co.edu.poligran.serviciosalestudiante.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import co.edu.poligran.serviciosalestudiante.entities.UserEntity;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

}
