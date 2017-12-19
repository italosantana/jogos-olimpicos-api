package com.jogos.olimpicos.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jogos.olimpicos.type.EtapaType;

@Entity
@Table(name = "competicao")
public class CompeticaoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idmodalidade", referencedColumnName = "id")
	private ModalidadeEntity modalidade;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idlocal", referencedColumnName = "id")
	private LocalEntity local;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "competidor_a_id", referencedColumnName = "id")
	private CompetidorEntity competidorA;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "competidor_b_id", referencedColumnName = "id")
	private CompetidorEntity competidorB;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EtapaType etapa;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "data_inicio")
	private LocalDateTime dataInicio;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "data_termino")
	private LocalDateTime dataTermino;

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(LocalDateTime dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ModalidadeEntity getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeEntity modalidade) {
		this.modalidade = modalidade;
	}

	public LocalEntity getLocal() {
		return local;
	}

	public void setLocal(LocalEntity local) {
		this.local = local;
	}

	public CompetidorEntity getCompetidorA() {
		return competidorA;
	}

	public void setCompetidorA(CompetidorEntity competidorA) {
		this.competidorA = competidorA;
	}

	public CompetidorEntity getCompetidorB() {
		return competidorB;
	}

	public void setCompetidorB(CompetidorEntity competidorB) {
		this.competidorB = competidorB;
	}

	public EtapaType getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaType etapa) {
		this.etapa = etapa;
	}
}

