package com.jogos.olimpicos.service;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jogos.olimpicos.entity.ModalidadeEntity;
import com.jogos.olimpicos.repository.ModalidadeRepository;

@Service
public class ModalidadeService {

	@Autowired
	ModalidadeRepository modalidadeRepository;

	public ModalidadeEntity save(ModalidadeEntity modalidade) {

		String nome = modalidade.getNome().toUpperCase();

		ModalidadeEntity localSearch = modalidadeRepository.findByNome(nome);

		if (localSearch == null) {
			modalidade.setNome(nome);
			return modalidadeRepository.save(modalidade);
		} else {
			throw new ValidationException("Já existe um registro com este nome cadastrado!");
		}
	}

	public ModalidadeEntity update(Integer codigo, ModalidadeEntity modalidade) {
		ModalidadeEntity modalidadeSalvo = modalidadeRepository.findOne(codigo);

		if (modalidadeSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}

		modalidadeSalvo.setNome(modalidade.getNome().toUpperCase());

		modalidadeRepository.save(modalidadeSalvo);

		return modalidadeSalvo;
	}

	public void delete(Integer codigo) {
		ModalidadeEntity modalidade = modalidadeRepository.findOne(codigo);

		if (modalidade == null) {
			throw new EmptyResultDataAccessException(1);
		}

		modalidadeRepository.delete(modalidade);
	}

	public List<ModalidadeEntity> listAll() {
		return modalidadeRepository.findAll();
	}

	public ModalidadeEntity get(Integer codigo) {
		return modalidadeRepository.findOne(codigo);
	}
}
