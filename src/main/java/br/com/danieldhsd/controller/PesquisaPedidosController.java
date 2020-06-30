package br.com.danieldhsd.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.danieldhsd.model.Pedido;
import br.com.danieldhsd.model.StatusPedido;
import br.com.danieldhsd.repository.PedidosRepository;
import br.com.danieldhsd.repository.filter.PedidoFilter;

@Named
@ViewScoped
public class PesquisaPedidosController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidosRepository pedidosRepository;
	
	private PedidoFilter filtro;
	
	private List<Pedido> pedidosFiltrados;
	
	public PesquisaPedidosController() {
		this.filtro = new PedidoFilter();
		this.pedidosFiltrados = new ArrayList<Pedido>();
	}

	public void pesquisar() {
		pedidosFiltrados = pedidosRepository.filtrados(filtro);
	}
	
	public StatusPedido[] getStatuses() {
		return StatusPedido.values();
	}
	
	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(PedidoFilter filtro) {
		this.filtro = filtro;
	}

}
