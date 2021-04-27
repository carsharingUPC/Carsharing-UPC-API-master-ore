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
import pe.com.carsharing.service.ReservaService;

@Api(value = "Endpoints de Reserva")
@RestController
@RequestMapping("api/reservas")
public class ReservaRestController {
	
	@Autowired
	private ReservaService reservaService;
	
	@ApiOperation(value = "EndPoint que permite listar las reservas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Reserva>> Listar() {
		ResponseEntity<List<Reserva>> response;
		try {
			List<Reserva> reservas = reservaService.findAll();
			response = new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite obtener una reserva por su id")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reserva> GetCita(@PathVariable("id") int id) {
		try {
			Optional<Reserva> reserva = reservaService.findById(id);
			if (reserva.isPresent()) {
				return new ResponseEntity<Reserva>(reserva.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Reserva>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite grabar una reserva")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reserva> nuevo(@RequestBody Reserva reserva) {
		try {
			Reserva nuevaReserva = reservaService.save(reserva);
			return new ResponseEntity<Reserva>(nuevaReserva, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Reserva>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar una reserva")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reserva> actualizar(@PathVariable("id") Integer id, @RequestBody Reserva reserva) {
		try {
			if (id.equals(reserva.getId())) {
				Optional<Reserva> res = reservaService.findById(id);
				if (res.isPresent()) {
					Reserva reservaUpdate = reservaService.update(reserva);
					return new ResponseEntity<Reserva>(reservaUpdate, HttpStatus.OK);
				} else {
					return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Reserva>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Reserva>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Reserva> reserva = reservaService.findById(id);
			if (reserva.isPresent()) {
				reservaService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite desactivar una reserva por su id")
	@GetMapping(path = "/{id}/desactivate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reserva> desactivar(@PathVariable("id") int id) {
		try {
			Optional<Reserva> reserva = reservaService.findById(id);
			if (reserva.isPresent()) {
				reserva.get().setUso(false);
				reservaService.update(reserva.get());
				return new ResponseEntity<Reserva>(reserva.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Reserva>(HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation(value = "EndPoint que permite activar una reserva por su id")
	@GetMapping(path = "/{id}/activate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Reserva> activar(@PathVariable("id") int id) {
		try {	
			Optional<Reserva> reserva = reservaService.findById(id);
			if (reserva.isPresent()) {
				reserva.get().setUso(true);
				reservaService.update(reserva.get());
				return new ResponseEntity<Reserva>(reserva.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Reserva>(HttpStatus.BAD_REQUEST);
		}
	}
}
