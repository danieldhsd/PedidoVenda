package br.com.danieldhsd.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.danieldhsd.model.ItemPedido;
import br.com.danieldhsd.model.Pedido;
import br.com.danieldhsd.repository.PedidosRepository;
import br.com.danieldhsd.util.jpa.Transactional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidosRepository pedidosRepository;
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = this.pedidosRepository.buscarPorId(pedido.getId());
		
		for(ItemPedido item : pedido.getItensPedido()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}

	public void retornarItensEstoque(Pedido pedido) {
		pedido = this.pedidosRepository.buscarPorId(pedido.getId());
		
		for(ItemPedido item : pedido.getItensPedido()) {
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
	}
}
