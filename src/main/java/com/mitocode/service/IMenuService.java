package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Menu;
import com.mitocode.model.Rol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMenuService extends ICRUD<Menu, Integer>{
	
	List<Menu> listarMenuPorUsuario(String nombre);

	Page<Menu> listarPageable(Pageable pageable);

}
