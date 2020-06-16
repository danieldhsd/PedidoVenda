package br.com.danieldhsd.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CadastroPedidoController {
	
	private List<Integer> itens;

	public CadastroPedidoController() {
		itens = new ArrayList<Integer>();
		itens.add(1);
	}

	public List<Integer> getItens() {
		return itens;
	}
	
}