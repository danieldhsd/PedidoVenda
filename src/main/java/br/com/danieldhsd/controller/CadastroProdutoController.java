package br.com.danieldhsd.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.com.danieldhsd.model.Categoria;
import br.com.danieldhsd.model.Produto;
import br.com.danieldhsd.repository.CategoriasRepository;
import br.com.danieldhsd.service.CadastroProdutoService;
import br.com.danieldhsd.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private CategoriasRepository categoriasRepository;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;
	
	private List<Categoria> categoriasRaizes;
	private List<Categoria> subCategoria;
	
	private Produto produto;
	
	@NotNull
	private Categoria categoriaPai;
	
	public CadastroProdutoController() {
		limpar();
	}

	public void inicializar() {
		if(FacesUtil.isNotPostBack()) {
			categoriasRaizes = categoriasRepository.buscarRaizes();
			
			if(this.categoriaPai != null) {
				carregarSubcategorias();
			}
		}
	}
	
	public void carregarSubcategorias() {
		subCategoria = categoriasRepository.buscarSubcategorias(categoriaPai);
	}
	
	public void salvar() {
		cadastroProdutoService.salvar(produto);
		limpar();
		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}
	
	private void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subCategoria = new ArrayList<Categoria>();
	}
	
	public boolean isEditando() {
		return this.produto.getId() != null;
	}
	
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
		
		if(this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
	}

	public List<Categoria> getSubCategoria() {
		return subCategoria;
	}

}
