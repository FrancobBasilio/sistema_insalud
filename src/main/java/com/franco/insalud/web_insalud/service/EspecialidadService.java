package com.franco.insalud.web_insalud.service;


import com.franco.insalud.web_insalud.entity.Especialidad;
import com.franco.insalud.web_insalud.repository.EspecialidadRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EspecialidadService {

   @Autowired
   private EspecialidadRepository especialidadRepository;
   
	public List<Especialidad> listar(){
		return especialidadRepository.findAll();
	}
}