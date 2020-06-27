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
import br.com.danieldhsd.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriasRepository categoriasRepository;
	
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subCategoria;
	
	private Produto produto;
	
	@NotNull
	private Categoria categoriaPai;
	
	public CadastroProdutoController() {
		produto = new Produto();
	}

	public void inicializar() {
		if(FacesUtil.isNotPostBack())
			categoriasRaizes = categoriasRepository.buscarRaizes();
	}
	
	public void carregarSubcategorias() {
		subCategoria = categoriasRepository.buscarSubcategorias(categoriaPai);
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

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Categoria> getSubCategoria() {
		return subCategoria;
	}

}
