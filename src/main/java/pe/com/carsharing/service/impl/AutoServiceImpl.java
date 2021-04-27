package pe.com.carsharing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.carsharing.model.Auto;
import pe.com.carsharing.repository.AutoRepository;
import pe.com.carsharing.service.AutoService;
@Service
public class AutoServiceImpl  implements AutoService {
	@Autowired
	private AutoRepository autorepository;
	@Override
	public List<Auto> findAll() throws Exception {
		return autorepository.findAll();
	}

	@Override
	public Optional<Auto> findById(Integer id) throws Exception {
		return autorepository.findById(id);
	}

	@Override
	public Auto save(Auto t) throws Exception {
		return autorepository.save(t);
	}

	@Override
	public Auto update(Auto t) throws Exception {
		return autorepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		autorepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		autorepository.deleteAll();
	}
}
