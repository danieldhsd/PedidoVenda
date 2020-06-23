package br.com.danieldhsd.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.com.danieldhsd.model.Produto;

@Named
@ViewScoped
public class CadastroProdutoController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	
	@PostConstruct
	public void init() {
		produto = new Produto();
	}

	public Produto getProduto() {
		return produto;
	}
}
