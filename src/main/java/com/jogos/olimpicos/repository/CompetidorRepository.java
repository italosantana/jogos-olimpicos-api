package com.jogos.olimpicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jogos.olimpicos.entity.CompetidorEntity;

public interface CompetidorRepository extends JpaRepository<CompetidorEntity, Integer>{

	public CompetidorEntity findByNome(String nomeCompetidor);

}

