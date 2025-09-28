package com.franco.insalud.web_insalud.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franco.insalud.web_insalud.entity.Atencion;
import com.franco.insalud.web_insalud.repository.AtencionRepository;


@Service
public class AtencionService {

   @Autowired
   private AtencionRepository atencionRepository;
   
	public List<Atencion> listar(){
		return atencionRepository.findAll();
	}
   
	   public List<Atencion> buscarPorFecha(LocalDate fecha) {
	        return atencionRepository.findByFecha(fecha);
	    }
	   
	   
	   public Optional<Atencion> buscarPorId(Long id) {
	        return atencionRepository.findById(id);
	    }
	   
		public Atencion guardar(Atencion atencion) {
			  return atencionRepository.save(atencion);
		  }
		
		public void eliminar(Long id) {
			atencionRepository.deleteById(id);
		}
		
		public List<Atencion> findByPacienteId(Long pacienteId) {
		    return atencionRepository.findByPacienteId(pacienteId);
		}
		
}