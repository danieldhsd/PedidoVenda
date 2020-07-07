package br.com.danieldhsd.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.danieldhsd.model.Pedido;
import br.com.danieldhsd.model.StatusPedido;
import br.com.danieldhsd.repository.PedidosRepository;
import br.com.danieldhsd.util.jpa.Transactional;

public class CancelamentoPedidoService implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private PedidosRepository pedidos;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Transactional
	public Pedido cancelar(Pedido pedido) {
		pedido = this.pedidos.buscarPorId(pedido.getId());
		
		if(pedido.isNaoCancelavel()) {
			throw new NegocioException("Pedido nao pode ser cancelado no status "
							+ pedido.getStatusPedido().getDescricao() + ".");
		}
		
		if(pedido.isEmitido()) {
			this.estoqueService.retornarItensEstoque(pedido);
		}
		
		pedido.setStatusPedido(StatusPedido.CANCELADO);
		
		pedido = this.pedidos.guardar(pedido);
		
		return pedido;
	}

}
