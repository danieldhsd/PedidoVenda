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
import br.com.danieldhsd.repository.CategoriasRepository;

@Named
@ViewScoped
public class CadastroProdutoController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriasRepository categoriasRepository;
	
	private List<Categoria> categoriasRaizes;
	
	private Produto produto;
	
	@PostConstruct
	public void init() {
		produto = new Produto();
		inicializar();
	}

	public void inicializar() {
		categoriasRaizes = categoriasRepository.buscarRaizes();
	}
	
	public Produto getProduto() {
		return produto;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

}
