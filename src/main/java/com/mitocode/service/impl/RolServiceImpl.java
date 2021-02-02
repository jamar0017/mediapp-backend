package com.mitocode.service.impl;

import com.mitocode.model.Rol;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IRolRepo;
import com.mitocode.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends CRUDImpl<Rol, Integer> implements IRolService {

	@Autowired
	private IRolRepo repo;

	@Override
	protected IGenericRepo<Rol, Integer> getRepo() {
		return repo;
	}
	
	@Override
	public Page<Rol> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	
}
