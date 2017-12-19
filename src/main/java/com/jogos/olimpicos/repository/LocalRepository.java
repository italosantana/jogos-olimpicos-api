package com.jogos.olimpicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jogos.olimpicos.entity.LocalEntity;

public interface LocalRepository extends JpaRepository<LocalEntity, Integer>{
	
	public LocalEntity findByNome(String nome);

}
