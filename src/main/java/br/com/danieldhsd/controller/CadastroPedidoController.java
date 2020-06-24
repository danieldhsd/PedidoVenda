package br.com.danieldhsd.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.com.danieldhsd.model.EnderecoEntrega;
import br.com.danieldhsd.model.Pedido;

@Named
@ViewScoped
public class CadastroPedidoController {
	
	private Pedido pedido;
	
	private List<Integer> itens;
	
	@PostConstruct
	public void init() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public void salvar() {}
	
	public CadastroPedidoController() {
		itens = new ArrayList<Integer>();
		itens.add(1);
	}
	public List<Integer> getItens() {
		return itens;
	}

	public Pedido getPedido() {
		return pedido;
	}
	
}