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

import com.jogos.olimpicos.entity.CompetidorEntity;
import com.jogos.olimpicos.service.CompetidorService;


@RestController
@RequestMapping("/competidor")
public class CompetidorResource {
	
	@Autowired
	private CompetidorService competidorService;
	
	/**
	 * Lista todos {@link CompetidorEntity} cadastrados
	 * @return
	 */
	@GetMapping("/listAll")
	public List<CompetidorEntity> listAll(){
		return competidorService.listAll(); 
	}
	
	/**
	 * Retorna o {@link CompetidorEntity} pelo codigo 
	 * @param codigo
	 * @return
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<CompetidorEntity> get(@PathVariable Integer codigo){
		CompetidorEntity competidor = competidorService.get(codigo); 
		return competidor != null ? ResponseEntity.ok(competidor) : ResponseEntity.noContent().build();
	}
	
	/**
	 * Deleta o {@link CompetidorEntity} pelo codigo
	 * @param codigo
	 */
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Integer codigo){
		competidorService.delete(codigo);
	}
	
	/**
	 * 
	 * @param competidor
	 * @param response
	 * @return
	 */
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CompetidorEntity> save(@Valid @RequestBody CompetidorEntity competidor, HttpServletResponse response) {
		CompetidorEntity competidorSalvo = competidorService.salvar(competidor);
	
		return ResponseEntity.status(HttpStatus.CREATED).body(competidorSalvo);
	}
	
	/**
	 * Atualiza o {@link CompetidorEntity} pelo codigo
	 * @param codigo
	 * @param competidor
	 * @return
	 */
	@PutMapping("/{codigo}")
	public ResponseEntity<CompetidorEntity> atualizar(@PathVariable Integer codigo, @Valid @RequestBody CompetidorEntity competidor){
		CompetidorEntity competidorSalvo = competidorService.updateByCodigo(codigo, competidor);
		return ResponseEntity.ok(competidorSalvo);
	}
}
