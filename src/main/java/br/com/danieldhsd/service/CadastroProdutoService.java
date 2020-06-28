package br.com.danieldhsd.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.danieldhsd.model.Produto;
import br.com.danieldhsd.repository.ProdutosRepository;

public class CadastroProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	ProdutosRepository produtosRepository;
	
	public Produto salvar(Produto produto) {
		Produto produtoExistente = produtosRepository.buscarPorSKU(produto.getSku());
		
		if (produtoExistente != null) {
			throw new NegocioException("Já existe um produto com o SKU informado!");
		}
		return produtosRepository.guardar(produto);
	}

}
