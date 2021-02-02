package com.mitocode.service;

import com.mitocode.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUsuarioService extends ICRUD<Usuario, Integer>{
		
	Page<Usuario> listarPageable(Pageable pageable);

	Usuario aginarRoles(Usuario p);
}
