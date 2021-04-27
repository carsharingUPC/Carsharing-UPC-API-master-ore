package pe.com.carsharing.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pe.com.carsharing.model.Reserva;
import pe.com.carsharing.model.Usuario;
import pe.com.carsharing.service.UsuarioService;

@Api(value = "Endpoints de Usuario")
@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@ApiOperation(value = "EndPoint que permite listar los usuarios")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> Listar() {
		ResponseEntity<List<Usuario>> response;
		try {
			List<Usuario> usuarios = usuarioService.findAll();
			response = new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite obtener un usuario por su id")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> GetUsuario(@PathVariable("id") int id) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);
			if (usuario.isPresent()) {
				return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite obtener usuario por celular")
	@GetMapping(path= "/celular/{celular}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> Getcelular(@PathVariable("celular") String celular){
		try {
			Optional<Usuario> cellphone = usuarioService.findBycelular(celular);
			if (cellphone.isPresent()) {
				return new ResponseEntity<Usuario>(cellphone.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite grabar un usuario")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> nuevo(@RequestBody Usuario usuario) {
		try {
			Usuario nuevoUsuario = new Usuario();
			nuevoUsuario.setEsAdm(false);
			nuevoUsuario.setEnable(true);
			nuevoUsuario= usuarioService.save(usuario);
			return new ResponseEntity<Usuario>(nuevoUsuario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar un usuario")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> actualizar(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
		try {
			if (id.equals(usuario.getId())) {
				Optional<Usuario> use = usuarioService.findById(id);
				if (use.isPresent()) {
					Usuario usuarioUpdate = usuarioService.update(usuario);
					return new ResponseEntity<Usuario>(usuarioUpdate, HttpStatus.OK);
				} else {
					return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);
			if (usuario.isPresent()) {
				usuarioService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/{id}/reservas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<Reserva> > GetUsuarioReserva(@PathVariable("id") int id) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);
			if( usuario.isPresent() ) {
				return new ResponseEntity< List<Reserva> >(usuario.get().getReservas(), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Reserva>>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Reserva>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite listar a los usuarios que no son adm")
	@GetMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Usuario>> usuariosComunes () {
		ResponseEntity<List<Usuario>> response;
		try {
			List<Usuario> usuarios = usuarioService.getCustomerUsers();
			response = new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite listar a los usuarios que no son adm y estan activos")
	@GetMapping(path = "/customer/enabled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Usuario>> usuariosComunesActivos () {
		ResponseEntity<List<Usuario>> response;
		try {
			List<Usuario> usuarios = usuarioService.getCustomerUsersEnabled();
			response = new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@ApiOperation(value = "EndPoint que permite listar a los usuarios que no son adm y que estan inactivos")
	@GetMapping(path = "/customer/disabled", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Usuario>> usuariosComunesInactivos () {
		ResponseEntity<List<Usuario>> response;
		try {
			List<Usuario> usuarios = usuarioService.getCustomerUsersDisabled();
			response = new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}


	@ApiOperation(value = "EndPoint que permite desactivar un usuario por su id")
	@GetMapping(path = "/{id}/desactivate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> desactivar(@PathVariable("id") int id) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);
			if (usuario.isPresent()) {
				usuario.get().setEnable(false);
				usuarioService.update(usuario.get());
				return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite activar un usuario por su id")
	@GetMapping(path = "/{id}/activate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> activar(@PathVariable("id") int id) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);
			if (usuario.isPresent()) {
				usuario.get().setEnable(true);
				usuarioService.update(usuario.get());
				return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}


	@ApiOperation(value = "EndPoint que permite obtener usuario por correo y password ")
	@PostMapping(path= "/log", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> log(@RequestBody Usuario user){
		try {
			String correo = user.getCorreo();
			String password = user.getPassword();
			Optional<Usuario> login = usuarioService.login(correo, password);
			if (login.isPresent()) {
				return new ResponseEntity<Usuario>(login.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}
}
