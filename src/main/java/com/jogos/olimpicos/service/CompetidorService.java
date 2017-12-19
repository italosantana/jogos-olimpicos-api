package com.jogos.olimpicos.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jogos.olimpicos.entity.CompetidorEntity;
import com.jogos.olimpicos.repository.CompetidorRepository;

@Service
public class CompetidorService {

	@Autowired
	CompetidorRepository competidorRepository;

	/**
	 * Salva um local aplicando um pattern especifico
	 * 
	 * @param local
	 * @return Local
	 */
	public CompetidorEntity salvar(CompetidorEntity competidor) {

		String nomeCompetidor = competidor.getNome();

		CompetidorEntity competidorSearch = competidorRepository.findByNome(nomeCompetidor);

		if (competidorSearch == null) {
			competidor.setNome(nomeCompetidor);
			return competidorRepository.save(competidor);
		} else {
			throw new ValidationException("Já existe um registro com este nome cadastrado!");
		}
	}

	public CompetidorEntity updateByCodigo(Integer codigo, CompetidorEntity competidor) {
		CompetidorEntity competidorSalvo = competidorRepository.findOne(codigo);

		if (competidorSalvo == null) {
			throw new EmptyResultDataAccessException(codigo);
		}

		competidorSalvo.setNome(competidor.getNome());

		competidorRepository.save(competidorSalvo);

		return competidorSalvo;
	}

	public List<CompetidorEntity> listAll() {
		return competidorRepository.findAll();
	}

	public CompetidorEntity get(Integer codigo) {
		return competidorRepository.findOne(codigo);
	}

	public void delete(Integer codigo) {
		CompetidorEntity competidor = competidorRepository.findOne(codigo);

		if (competidor == null) {
			throw new EmptyResultDataAccessException(1);
		}

		competidorRepository.delete(competidor);
	}
}
