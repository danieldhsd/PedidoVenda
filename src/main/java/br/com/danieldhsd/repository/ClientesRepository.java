package br.com.danieldhsd.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.danieldhsd.model.Cliente;

public class ClientesRepository implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	public Cliente buscarPorId(Long id) {
		return this.manager.find(Cliente.class, id);
	}
	
	public List<Cliente> buscarPorNome(String nome) {
		return this.manager.createQuery("from Cliente "
				+ "where upper(nome) like :NOME", Cliente.class)
				.setParameter("NOME", nome.toUpperCase() + "%")
				.getResultList();
	}
}
