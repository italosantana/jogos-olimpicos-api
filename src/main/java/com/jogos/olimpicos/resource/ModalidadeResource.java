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

import com.jogos.olimpicos.entity.ModalidadeEntity;
import com.jogos.olimpicos.service.ModalidadeService;

@RestController
@RequestMapping("/modalidade")
public class ModalidadeResource {

	@Autowired
	private ModalidadeService modalidadeService;

	/**
	 * Retorna todas {@link ModalidadeEntity} cadastradas
	 * @return List {@link ModalidadeEntity}
	 */
	@GetMapping("/listAll")
	public List<ModalidadeEntity> listAll() {
		return modalidadeService.listAll();
	}

	/**
	 * Obetem {@link ModalidadeEntity} através de seu código
	 * @param codigo
	 * @return {@link ModalidadeEntity}
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<ModalidadeEntity> get(@PathVariable Integer codigo) {
		ModalidadeEntity modalidade = modalidadeService.get(codigo);
		
		return modalidade != null ? ResponseEntity.ok(modalidade) : ResponseEntity.noContent().build();
	}

	
	/**
	 * Deleta a {@link ModalidadeEntity} pelo codigo
	 * @param codigo
	 */
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Integer codigo) {
		modalidadeService.delete(codigo);
	}

	/**
	 * Salva um nova {@link ModalidadeEntity}, caso o id já exista ele atualiza
	 * @param modalidade
	 * @param response
	 * @return
	 */
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ModalidadeEntity> save(@RequestBody ModalidadeEntity modalidade,
			HttpServletResponse response) {
		ModalidadeEntity modalidadeSave = modalidadeService.save(modalidade);
			
		return ResponseEntity.status(HttpStatus.CREATED).body(modalidadeSave);
	}

	/**
	 * Atualiza a {@link ModalidadeEntity} através de seu código.
	 * @param codigo
	 * @param modalidade
	 * @return
	 */
	@PutMapping("/{codigo}")
	public ResponseEntity<ModalidadeEntity> update(@PathVariable Integer codigo,
			@Valid @RequestBody ModalidadeEntity modalidade) {
		ModalidadeEntity modalidadeSalvo = modalidadeService.update(codigo, modalidade);
		return ResponseEntity.ok(modalidadeSalvo);
	}
}
