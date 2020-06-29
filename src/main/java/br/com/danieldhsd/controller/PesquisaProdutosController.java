package br.com.danieldhsd.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.danieldhsd.model.Produto;
import br.com.danieldhsd.repository.ProdutosRepository;
import br.com.danieldhsd.repository.filter.ProdutoFilter;

@Named
@ViewScoped
public class PesquisaProdutosController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutosRepository produtosRepository;
	
	private ProdutoFilter produtoFilter;
	
	private List<Produto> produtosFiltrados;
	
	public PesquisaProdutosController() {
		produtoFilter = new ProdutoFilter();
	}
	
	public void pesquisar() {
		produtosFiltrados = produtosRepository.filtrados(produtoFilter);
	}
	
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getProdutoFilter() {
		return produtoFilter;
	}

}
