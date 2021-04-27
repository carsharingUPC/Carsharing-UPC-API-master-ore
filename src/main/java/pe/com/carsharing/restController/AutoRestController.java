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
import pe.com.carsharing.model.Auto;
import pe.com.carsharing.service.AutoService;

@Api(value = "Endpoints de Auto")
@RestController
@RequestMapping("api/autos")
public class AutoRestController {
	
	@Autowired
	private AutoService autoService;
	
	@ApiOperation(value = "EndPoint que permite listar los autos")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Auto>> Listar() {
		ResponseEntity<List<Auto>> response;
		try {
			List<Auto> autos = autoService.findAll();
			response = new ResponseEntity<List<Auto>>(autos, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite obtener un auto por su id")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Auto> GetCita(@PathVariable("id") int id) {
		try {
			Optional<Auto> auto = autoService.findById(id);
			if (auto.isPresent()) {
				return new ResponseEntity<Auto>(auto.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Auto>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Auto>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite grabar un auto")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Auto> nuevo(@RequestBody Auto auto) {
		try {
			Auto nuevoAuto = autoService.save(auto);
			return new ResponseEntity<Auto>(nuevoAuto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Auto>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar un auto")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Auto> actualizar(@PathVariable("id") Integer id, @RequestBody Auto auto) {
		try {
			if (id.equals(auto.getId())) {
				Optional<Auto> aut = autoService.findById(id);
				if (aut.isPresent()) {
					Auto autoUpdate = autoService.update(auto);
					return new ResponseEntity<Auto>(autoUpdate, HttpStatus.OK);
				} else {
					return new ResponseEntity<Auto>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Auto>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Auto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Auto> auto = autoService.findById(id);
			if (auto.isPresent()) {
				autoService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
