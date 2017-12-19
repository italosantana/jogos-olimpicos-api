package com.jogos.olimpicos.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jogos.olimpicos.entity.CompeticaoEntity;
import com.jogos.olimpicos.repository.CompeticaoRepository;
import com.jogos.olimpicos.type.EtapaType;


@Service
public class CompeticaoService {

	@Autowired
	private CompeticaoRepository competicaoRepository;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Verifica se a data de inicio, Modalidade e Local conflita com o periodo de
	 * alguma CompeticaoEntity ja cadastrada
	 * 
	 * Caso seja uma atualizacao a busca por competicoes e diferenciada
	 * 
	 * @param CompeticaoEntity
	 * @return boolean
	 */
	private boolean existeCompeticaoEntityNoPeriodo(CompeticaoEntity CompeticaoEntity) {
		List<CompeticaoEntity> competicoes = new ArrayList<>();

		if (CompeticaoEntity.getId() == null) {
			competicoes = competicaoRepository.findCompeticaoByLocalDataInicioAndModalidade(
					CompeticaoEntity.getLocal().getId(), CompeticaoEntity.getDataInicio(),
					CompeticaoEntity.getModalidade().getId());
		} else {
			competicoes = competicaoRepository.findCompeticaoByLocalDataInicioAndModalidadeUpdate(CompeticaoEntity.getId(),
					CompeticaoEntity.getLocal().getId(), CompeticaoEntity.getDataInicio(),
					CompeticaoEntity.getModalidade().getId());
		}

		return !competicoes.isEmpty();
	}

	/**
	 * Salva a CompeticaoEntity, realizando validacoes
	 * 
	 * @param CompeticaoEntity
	 * @return CompeticaoEntity
	 */
	public CompeticaoEntity salvar(CompeticaoEntity CompeticaoEntity) {
		validate(CompeticaoEntity);

		return competicaoRepository.save(CompeticaoEntity);
	}

	private void validate(CompeticaoEntity CompeticaoEntity) {

		validaDatas(CompeticaoEntity);

		if (existeCompeticaoEntityNoPeriodo(CompeticaoEntity)) {
			throw new ValidationException("Já existe outra competição cadastrada no mesmo período");
		}

		Duration duracao = Duration.between(CompeticaoEntity.getDataInicio(), CompeticaoEntity.getDataTermino());
		long seconds = duracao.getSeconds();

	
		if (seconds < 1800) {
			throw new ValidationException("O tempo da competição deve ter ao menos 30 minutos");
		}

		if (ultrapassaLimiteCompeticao(CompeticaoEntity)) {
			throw new ValidationException("Somente 4 competições são permitidas no mesmo dia e local");
		}

		// Somente é permitido o mesmo competidor caso seja em fase FINAL ou SEMIFINAL
		if (!CompeticaoEntity.getEtapa().equals(EtapaType.FINAL) && !CompeticaoEntity.getEtapa().equals(EtapaType.SEMIFINAL)) {
			if (CompeticaoEntity.getCompetidorA().getId().equals(CompeticaoEntity.getCompetidorB().getId())) {
				throw new ValidationException("Competidores iguais são permitidos apenas na final ou semifinal.");
			}
		}

	}

	private void validaDatas(CompeticaoEntity CompeticaoEntity) {
		LocalDateTime inicio = CompeticaoEntity.getDataInicio();
		LocalDateTime termino = CompeticaoEntity.getDataTermino();

		if (inicio.getYear() != 2020 || termino.getYear() != 2020) {
			throw new ValidationException("Registro das competições somente para o ano de 2020'");
		}

		if (termino.isBefore(inicio)) {
			throw new ValidationException("Data de termino da competição deve ser maior que a data inicial");
		}
	}

	private boolean ultrapassaLimiteCompeticao(CompeticaoEntity CompeticaoEntity) {
		LocalDateTime dataInicialDiaLocal = CompeticaoEntity.getDataInicio().with(LocalTime.MIN);
		LocalDateTime dataTerminalDiaLocal = CompeticaoEntity.getDataTermino().with(LocalTime.MAX);

		List<CompeticaoEntity> competicoesDoDia = competicaoRepository.listByLocalDia(CompeticaoEntity.getLocal().getId(),
				dataInicialDiaLocal, dataTerminalDiaLocal);

		if (!competicoesDoDia.isEmpty() && competicoesDoDia.size() >= 4) {
			return true;
		}

		return false;
	}

	public List<CompeticaoEntity> listarPorModalidade(Integer codigo) {
		List<CompeticaoEntity> listByModalidade = competicaoRepository.listByModalidade(codigo);
		
		return listByModalidade;
	}

	public CompeticaoEntity get(Integer codigo) {
		return competicaoRepository.findOne(codigo);
	}

	public void delete(Integer codigo) {
		CompeticaoEntity CompeticaoEntity = competicaoRepository.findOne(codigo);

		if (CompeticaoEntity == null) {
			throw new EmptyResultDataAccessException(1);
		}

		competicaoRepository.delete(CompeticaoEntity);
	}

	public List<CompeticaoEntity> listarTodos() {
		return competicaoRepository.findAll(new Sort(Sort.Direction.ASC, "dataInicio"));
	}

	public CompeticaoEntity atualizar(Integer codigo, CompeticaoEntity CompeticaoEntity) {
		CompeticaoEntity CompeticaoEntitySalva = competicaoRepository.findOne(codigo);

		if (CompeticaoEntitySalva == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(CompeticaoEntity, CompeticaoEntitySalva, "id");

		validate(CompeticaoEntitySalva);

		return competicaoRepository.save(CompeticaoEntitySalva);
	}
}
