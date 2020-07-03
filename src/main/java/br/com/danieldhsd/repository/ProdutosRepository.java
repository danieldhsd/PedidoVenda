package br.com.danieldhsd.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.danieldhsd.model.Produto;
import br.com.danieldhsd.repository.filter.ProdutoFilter;
import br.com.danieldhsd.service.NegocioException;
import br.com.danieldhsd.util.jpa.Transactional;

public class ProdutosRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Produto guardar(Produto produto) {
		return manager.merge(produto);
	}
	
	@Transactional
	public void remover(Produto produto) {
		try {
			produto = buscarPorId(produto.getId());
			manager.remove(produto);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Produto não pode ser excluido.");
		}
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
	
	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		
		if(StringUtils.isNotBlank(filtro.getSku())) {
			criteria.add(Restrictions.eq("sku", filtro.getSku()));
		}
		
		if(StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Produto buscarPorId(Long id) {
		return manager.find(Produto.class, id);
	}

	public List<Produto> buscarPorNome(String nome) {
		return manager.createQuery("from Produto where upper(nome) like :NOME", Produto.class)
				.setParameter("NOME", nome.toUpperCase() + "%")
				.getResultList();
	}
}
