package pe.com.carsharing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.com.carsharing.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	@Query("Select us from Usuario us where us.correo = :correo and us.password = :password")
	Optional<Usuario> login(String correo, String password) throws Exception;
	
	@Query("Select us from Usuario us where us.esAdm = false")
	List<Usuario> getCustomerUsers() throws Exception;

	@Query("Select us from Usuario us where us.esAdm = false and enable = true")
	List<Usuario> getCustomerUsersEnabled() throws Exception;

	@Query("Select us from Usuario us where us.esAdm = false and enable = false")
	List<Usuario> getCustomerUsersDisabled() throws Exception;

	@Query("Select us from Usuario us where us.celular = :celular")
	Optional<Usuario> findBycelular(String celular) throws Exception;
}
