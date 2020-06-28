package br.com.danieldhsd.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.danieldhsd.model.Produto;

public class ProdutosRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Produto guardar(Produto produto) {
		try {
			manager.getTransaction().begin();
			produto = manager.merge(produto);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}
		
		return produto;
	}
	
	public Produto buscarPorSKU(String sku) {
		try {
			return manager.createQuery("from Produto where upper(sku) = :SKU", Produto.class)
					.setParameter("SKU", sku.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
