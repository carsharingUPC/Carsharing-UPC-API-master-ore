package pe.com.carsharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.com.carsharing.model.Auto;
@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer>{

}
