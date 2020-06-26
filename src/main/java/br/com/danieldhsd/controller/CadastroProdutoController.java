package br.com.danieldhsd.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.danieldhsd.model.Categoria;
import br.com.danieldhsd.model.Produto;

@Named
@ViewScoped
public class CadastroProdutoController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	private List<Categoria> categoriasRaizes;
	
	private Produto produto;
	
	@PostConstruct
	public void init() {
		produto = new Produto();
		inicializar();
	}

	public void inicializar() {
		categoriasRaizes = manager.createQuery("from Categoria", Categoria.class)
							.getResultList();
	}
	
	public Produto getProduto() {
		return produto;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

}
