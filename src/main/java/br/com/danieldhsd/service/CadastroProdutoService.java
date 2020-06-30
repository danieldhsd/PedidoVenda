package br.com.danieldhsd.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.danieldhsd.model.Produto;
import br.com.danieldhsd.repository.ProdutosRepository;
import br.com.danieldhsd.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	ProdutosRepository produtosRepository;
	
	@Transactional
	public Produto salvar(Produto produto) {
		Produto produtoExistente = produtosRepository.buscarPorSKU(produto.getSku());
		
		if (produtoExistente != null && !produtoExistente.equals(produto)) {
			throw new NegocioException("Já existe um produto com o SKU informado!");
		}
		return produtosRepository.guardar(produto);
	}

}
