package pe.com.carsharing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.carsharing.model.Usuario;
import pe.com.carsharing.repository.UsuarioRepository;
import pe.com.carsharing.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Override
	public List<Usuario> findAll() throws Exception {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> findById(Integer id) throws Exception {
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario save(Usuario t) throws Exception {
		return usuarioRepository.save(t);
	}

	@Override
	public Usuario update(Usuario t) throws Exception {
		return usuarioRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		usuarioRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		usuarioRepository.deleteAll();
	}

	@Override
	public Optional<Usuario> login(String correo, String password) throws Exception {
		return usuarioRepository.login(correo, password);
	}

	@Override
	public List<Usuario> getCustomerUsers() throws Exception {
		return usuarioRepository.getCustomerUsers();
	}

	@Override
	public List<Usuario> getCustomerUsersEnabled() throws Exception {
		return usuarioRepository.getCustomerUsersEnabled();
	}

	@Override
	public List<Usuario> getCustomerUsersDisabled() throws Exception {
		return usuarioRepository.getCustomerUsersDisabled();
	}

	@Override
	public Optional<Usuario> findBycelular(String celular) throws Exception {
		return usuarioRepository.findBycelular(celular);
	}
}
