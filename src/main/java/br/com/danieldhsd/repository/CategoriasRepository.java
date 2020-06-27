package br.com.danieldhsd.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.danieldhsd.model.Categoria;

public class CategoriasRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<Categoria> buscarRaizes(){
		
		return manager.createQuery("from Categoria where categoriaPai is null", Categoria.class)
				.getResultList();
	}
	
	public List<Categoria> buscarSubcategorias(Categoria categoria) {
		return manager.createQuery("from Categoria where categoriaPai = :RAIZ", 
				Categoria.class)
				.setParameter("RAIZ", categoria)
				.getResultList();
	}
	
	public Categoria buscarPorId(Long id) {
		return manager.find(Categoria.class, id);
	}
}
