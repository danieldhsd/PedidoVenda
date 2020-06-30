package br.com.danieldhsd.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.danieldhsd.model.Produto;
import br.com.danieldhsd.repository.ProdutosRepository;
import br.com.danieldhsd.repository.filter.ProdutoFilter;
import br.com.danieldhsd.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaProdutosController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutosRepository produtosRepository;
	
	private ProdutoFilter produtoFilter;
	
	private List<Produto> produtosFiltrados;

	private Produto produtoSelecionado;
	
	public PesquisaProdutosController() {
		produtoFilter = new ProdutoFilter();
	}
	
	public void pesquisar() {
		produtosFiltrados = produtosRepository.filtrados(produtoFilter);
	}
	
	public void excluir() {
		produtosRepository.remover(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado);
		
		FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku() 
		+ " excluído com sucesso.");
	}
	
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getProdutoFilter() {
		return produtoFilter;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

}
