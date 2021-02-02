package com.mitocode.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mitocode.model.Menu;
import com.mitocode.service.IMenuService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/menus")
public class MenuController {
	
	@Autowired
	private IMenuService service;
	
	@GetMapping
	public ResponseEntity<List<Menu>> listar() throws Exception{
		List<Menu> menus = new ArrayList<>();
		menus = service.listar();
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<List<Menu>> listar(@RequestBody String nombre) throws Exception{
		List<Menu> menus = new ArrayList<>();
		menus = service.listarMenuPorUsuario(nombre);
		return new ResponseEntity<List<Menu>>(menus, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Menu> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Menu obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Menu>(obj, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Menu> registrar(@Valid @RequestBody Menu p) throws Exception {
		Menu obj = service.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdMenu()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Menu> modificar(@Valid @RequestBody Menu p) throws Exception {
		Menu obj = service.modificar(p);
		return new ResponseEntity<Menu>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Menu obj = service.listarPorId(id);

		if (obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


	@GetMapping("/pageable")
	public ResponseEntity<Page<Menu>> listarPageable(Pageable pageable) throws Exception{
		Page<Menu> menu = service.listarPageable(pageable);
		return new ResponseEntity<Page<Menu>>(menu, HttpStatus.OK);
	}
}
