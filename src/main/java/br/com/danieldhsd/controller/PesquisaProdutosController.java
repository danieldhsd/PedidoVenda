package br.com.danieldhsd.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class PesquisaProdutosController {
	
	private List<Integer> produtosFiltrados;
	
	public PesquisaProdutosController() {
		produtosFiltrados = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			produtosFiltrados.add(i);
		}
	}
	
	public List<Integer> getProdutosFiltrados() {
		return produtosFiltrados;
	}

}
