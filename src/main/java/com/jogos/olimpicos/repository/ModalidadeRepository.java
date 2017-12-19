package com.jogos.olimpicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jogos.olimpicos.entity.ModalidadeEntity;

public interface ModalidadeRepository extends JpaRepository<ModalidadeEntity, Integer>{
	
	public ModalidadeEntity findByNome(String nomeModalidade);

}
