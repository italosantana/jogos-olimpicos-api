package com.jogos.olimpicos.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jogos.olimpicos.entity.LocalEntity;
import com.jogos.olimpicos.repository.LocalRepository;


@Service
public class LocalService {
	
	@Autowired
	LocalRepository localRepository;

	private String pattern(String nomeLocal) {
		return nomeLocal.trim().toUpperCase();
	}
	
	public LocalEntity save(LocalEntity local) {
		String nomeLocal = pattern(local.getNome());
		
		LocalEntity localSearch = localRepository.findByNome(nomeLocal);
		
		if(localSearch == null) {
			local.setNome(nomeLocal);
			return localRepository.save(local);
		}else {
			throw new ValidationException("Já existe um registro com este nome cadastrado!");
		}
	}
	
	
	public LocalEntity atualizar(Integer codigo, LocalEntity local) {
		LocalEntity localSalvo = localRepository.findOne(codigo);
		
		if(localSalvo ==  null) {
			throw new EmptyResultDataAccessException(codigo);
		}
		
		localSalvo.setNome(pattern(local.getNome()));
		
		localRepository.save(localSalvo);
		
		return localSalvo;
	}

	public List<LocalEntity> listAll() {
		return localRepository.findAll();
	}

	public LocalEntity get(Integer codigo) {
		return localRepository.findOne(codigo);
	}

	
	public void delete(Integer codigo) {
		LocalEntity local = localRepository.findOne(codigo);

		if (local == null) {
			throw new EmptyResultDataAccessException(1);
		}

		localRepository.delete(local);
	}
}
