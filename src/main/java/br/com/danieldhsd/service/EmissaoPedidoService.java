package br.com.danieldhsd.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.danieldhsd.model.Pedido;
import br.com.danieldhsd.model.StatusPedido;
import br.com.danieldhsd.repository.PedidosRepository;

public class EmissaoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	@Inject
	private PedidosRepository pedidos;
	
	@Inject
	private EstoqueService estoqueService;
	
	public Pedido emitir(Pedido pedido) {
		pedido = this.cadastroPedidoService.salvar(pedido);
		
		if(pedido.isNaoEmissivel()) {
			throw new NegocioException("Pedido não pode ser emitido com o status " 
					+ pedido.getStatusPedido().getDescricao() + ".");
		}
		
		this.estoqueService.baixarItensEstoque(pedido);
		
		pedido.setStatusPedido(StatusPedido.EMITIDO);
		
		pedido = this.pedidos.guardar(pedido);
		
		return pedido;
	}

}
