package br.com.danieldhsd.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.danieldhsd.model.Pedido;
import br.com.danieldhsd.repository.filter.PedidoFilter;

public class PedidosRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	public List<Pedido> filtrados(PedidoFilter pedidoFilter) {
		Session session = this.manager.unwrap(Session.class);
		
		Criteria criteria = session.createCriteria(Pedido.class)
				.createAlias("cliente", "c") // faz uma join com cliente e nomeia como c
				.createAlias("vendedor", "v");
		
		if (pedidoFilter.getNumeroDe() != null) {
			// id deve ser maior ou igual (ge = greater or equals)
			criteria.add(Restrictions.ge("id", pedidoFilter.getNumeroDe()));
		}
		
		if(pedidoFilter.getNumeroAte() != null) {
			// le = lower or equals
			criteria.add(Restrictions.le("id", pedidoFilter.getNumeroAte()));
		}
		
		if(pedidoFilter.getDataCriacaoDe() != null) {
			criteria.add(Restrictions.ge("dataCriacao", pedidoFilter.getDataCriacaoDe()));
		}
		
		if(pedidoFilter.getDataCriacaoAte() != null) {
			criteria.add(Restrictions.le("dataCriacao", pedidoFilter.getDataCriacaoAte()));
		}
		
		if(StringUtils.isNotBlank(pedidoFilter.getNomeCliente())) {
			criteria.add(Restrictions.ilike("c.nome", pedidoFilter.getNomeCliente(), 
						MatchMode.ANYWHERE));
		}
		
		if(StringUtils.isNotBlank(pedidoFilter.getNomeVendedor())) {
			criteria.add(Restrictions.ilike("v.nome", pedidoFilter.getNomeVendedor(), 
					MatchMode.ANYWHERE));
		}
		
		if(pedidoFilter.getStatuses() != null && pedidoFilter.getStatuses().length > 0) {
			criteria.add(Restrictions.in("status", pedidoFilter.getStatuses()));
		}
		
		return criteria.addOrder(Order.asc("id")).list();
	}

	public Pedido guardar(Pedido pedido) {
		return this.manager.merge(pedido);
	}

	public Pedido buscarPorId(Long id) {
		return this.manager.find(Pedido.class, id);
	}
}
