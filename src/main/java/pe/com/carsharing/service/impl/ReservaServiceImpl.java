package pe.com.carsharing.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.carsharing.model.Reserva;
import pe.com.carsharing.repository.ReservaRepository;
import pe.com.carsharing.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Override
	public List<Reserva> findAll() throws Exception {
		return reservaRepository.findAll();
	}

	@Override
	public Optional<Reserva> findById(Integer id) throws Exception {
		return reservaRepository.findById(id);
	}

	@Override
	public Reserva save(Reserva t) throws Exception {
		return reservaRepository.save(t);
	}

	@Override
	public Reserva update(Reserva t) throws Exception {
		return reservaRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		reservaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		reservaRepository.deleteAll();
	}

}
