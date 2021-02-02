package com.mitocode.service.impl;

import com.mitocode.model.Usuario;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IUsuarioRepo;
import com.mitocode.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UsuarioService extends CRUDImpl<Usuario, Integer> implements IUsuarioService {

	@Autowired
	private IUsuarioRepo repo;

	@Override
	protected IGenericRepo<Usuario, Integer> getRepo() {
		return repo;
	}

	@Override
	public Page<Usuario> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Usuario aginarRoles(Usuario u) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repo.findById(u.getIdUsuario());
		Usuario resultado = new Usuario();
		if (usuario.isPresent() ){
			Usuario user = usuario.get();
			user.setRoles(u.getRoles());
			resultado = repo.save(user);
		}else{
			throw new UsernameNotFoundException(String.format("Usuario no existe"));
		}
		return resultado;
	}
}
