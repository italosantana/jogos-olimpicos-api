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

import com.jogos.olimpicos.entity.LocalEntity;
import com.jogos.olimpicos.service.LocalService;

@RestController
@RequestMapping("/local")
public class LocalResource {

	@Autowired
	private LocalService localService;

	/**
	 * Lista todos {@link LocalEntity} cadastrados
	 * @return
	 */
	@GetMapping("/listAll")
	public List<LocalEntity> listar() {
		return localService.listAll();
	}

	/**
	 * Retorna um {@link LocalEntity} pelo codigo
	 * @param codigo
	 * @return
	 */
	@GetMapping("/{codigo}")
	public ResponseEntity<LocalEntity> get(@PathVariable Integer codigo) {
		LocalEntity local = localService.get(codigo);
		return local != null ? ResponseEntity.ok(local) : ResponseEntity.noContent().build();
	}

	/**
	 * Deleta um {@link LocalEntity} pelo codigo
	 * @param codigo
	 */
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Integer codigo) {
		localService.delete(codigo);
	}

	/**
	 * Salva um registro novo
	 * @param local
	 * @param response
	 * @return
	 */
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<LocalEntity> save(@Valid @RequestBody LocalEntity local, HttpServletResponse response) {
		LocalEntity localSave = localService.save(local);

		return ResponseEntity.status(HttpStatus.CREATED).body(localSave);
	}

	/**
	 * Atualiza Local através de seu código
	 * @param codigo
	 * @param local
	 * @return
	 */
	@PutMapping("/{codigo}")
	public ResponseEntity<LocalEntity> update(@PathVariable Integer codigo, @Valid @RequestBody LocalEntity local) {
		LocalEntity localSave = localService.atualizar(codigo, local);
		return ResponseEntity.ok(localSave);
	}
}
