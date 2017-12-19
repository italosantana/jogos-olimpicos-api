package com.jogos.olimpicos.resource;



import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jogos.olimpicos.entity.CompeticaoEntity;
import com.jogos.olimpicos.service.CompeticaoService;

	
@RestController
@RequestMapping("/competicao")
public class CompeticaoResource {

	@Autowired
	CompeticaoService competicaoService;

	/**
	 * Retorna todas {@link CompeticaoEntity} cadastradas
	 * @return
	 */
	@GetMapping("listAll")
	public List<CompeticaoEntity> listAll() {
		return competicaoService.listarTodos();
	}

	/**
	 * Retorna {@link CompeticaoEntity} pelo seu código
	 * @param codigo
	 * @return
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<CompeticaoEntity> get(@PathVariable Integer codigo) {
		CompeticaoEntity competicao = competicaoService.get(codigo);
		return competicao != null ? ResponseEntity.ok(competicao) : ResponseEntity.noContent().build();
	}

	/**
	 * Lista {@link CompeticaoEntity} pelo codigo de uma modalidade
	 * @param codigo
	 * @return
	 */
	@GetMapping("/modalidade/{codigo}")
	public List<CompeticaoEntity> getByModalidade(@PathVariable Integer codigo) {
		return competicaoService.listarPorModalidade(codigo);
	}

	/**
	 * Salvar a Competicao com validacoes
	 * @param competicao
	 * @param response
	 * @return
	 */
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CompeticaoEntity> save(@Valid @RequestBody CompeticaoEntity competicao, HttpServletResponse response) {
		CompeticaoEntity competicaoSalva = competicaoService.salvar(competicao);

		return ResponseEntity.status(HttpStatus.CREATED).body(competicaoSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Integer codigo) {
		competicaoService.delete(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<CompeticaoEntity> update(@PathVariable Integer codigo,
			@Valid @RequestBody CompeticaoEntity competicao) {
		CompeticaoEntity competicaoAtualizada = competicaoService.atualizar(codigo, competicao);
		return ResponseEntity.ok(competicaoAtualizada);
	}
}
