package br.com.danieldhsd.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.com.danieldhsd.model.Pedido;
import br.com.danieldhsd.model.StatusPedido;
import br.com.danieldhsd.repository.PedidosRepository;
import br.com.danieldhsd.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidosRepository pedidos;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		if(pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatusPedido(StatusPedido.ORCAMENTO);
		}
		pedido.recalcularValorTotal();
		
		if(pedido.isNaoAlteravel()) {
			throw new NegocioException("Pedido não pode ser alterado no status "
						+ pedido.getStatusPedido().getDescricao() + ".");
		}
		
		if(pedido.getItensPedido().isEmpty()) {
			throw new NegocioException("O pedido deve possuir pelo menos um item.");
		}
		
		if(pedido.isValorTotalNegativo()) {
			throw new NegocioException("O valor total do pedido não pode ser negativo.");
		}
		
		pedido = this.pedidos.guardar(pedido);
		
		return pedido;
	}
}
