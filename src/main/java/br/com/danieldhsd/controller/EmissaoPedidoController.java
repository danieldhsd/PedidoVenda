package br.com.danieldhsd.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.danieldhsd.model.Pedido;
import br.com.danieldhsd.service.EmissaoPedidoService;
import br.com.danieldhsd.util.jsf.FacesUtil;

@Named
@RequestScoped
public class EmissaoPedidoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmissaoPedidoService emissaoPedidoService;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	public void emitirPedido() {
		this.pedido.removerItemVazio();
		
		try {
			this.pedido = this.emissaoPedidoService.emitir(this.pedido);
			this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
			
			FacesUtil.addInfoMessage("Pedido emitido com sucesso!");
		} finally {
			this.pedido.adicionarItemVazio();
		}
	}
}
