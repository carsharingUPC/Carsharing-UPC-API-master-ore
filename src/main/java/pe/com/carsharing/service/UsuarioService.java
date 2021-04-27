package pe.com.carsharing.service;

import java.util.List;
import java.util.Optional;

import pe.com.carsharing.model.Usuario;

public interface UsuarioService extends CrudService<Usuario, Integer>{
	Optional<Usuario> login(String correo, String password) throws Exception;
	List<Usuario> getCustomerUsers() throws Exception;
	List<Usuario> getCustomerUsersEnabled() throws Exception;
	List<Usuario> getCustomerUsersDisabled() throws Exception;
	Optional<Usuario> findBycelular(String celular) throws Exception;
}
