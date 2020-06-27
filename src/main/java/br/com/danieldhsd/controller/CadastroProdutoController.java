package br.com.danieldhsd.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

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
	
	@NotNull
	private Categoria categoriaPai;
	
	public CadastroProdutoController() {
		produto = new Produto();
	}

	public void inicializar() {
		categoriasRaizes = categoriasRepository.buscarRaizes();
	}
	
	public void salvar() {}
	
	public Produto getProduto() {
		return produto;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

}
