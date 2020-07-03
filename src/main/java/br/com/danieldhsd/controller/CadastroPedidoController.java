package br.com.danieldhsd.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.danieldhsd.model.Cliente;
import br.com.danieldhsd.model.EnderecoEntrega;
import br.com.danieldhsd.model.FormaDePagamento;
import br.com.danieldhsd.model.ItemPedido;
import br.com.danieldhsd.model.Pedido;
import br.com.danieldhsd.model.Produto;
import br.com.danieldhsd.model.Usuario;
import br.com.danieldhsd.repository.ClientesRepository;
import br.com.danieldhsd.repository.ProdutosRepository;
import br.com.danieldhsd.repository.UsuariosRepository;
import br.com.danieldhsd.service.CadastroPedidoService;
import br.com.danieldhsd.util.jsf.FacesUtil;
import br.com.danieldhsd.validation.SKU;

@Named
@ViewScoped
public class CadastroPedidoController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuariosRepository usuarios;
	
	@Inject
	private ClientesRepository clientes;
	
	@Inject
	private ProdutosRepository produtos;
	
	@Inject
	private CadastroPedidoService cadastroPedidoService;
	
	private Pedido pedido;
	
	private Produto produtoLinhaEditavel;
	
	private List<Usuario> vendedores;
	
	@SKU
	private String sku;
	
	public CadastroPedidoController() {
		limpar();
	}
	
	public void inicializar() {
		if(FacesUtil.isNotPostBack()) {

			this.vendedores = this.usuarios.buscarVendedores();
			this.pedido.adicionarItemVazio();
			
		}
	}
	
	public void carregarProdutoPorSku() {
		if(StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtos.buscarPorSKU(sku);
			this.carregarProdutoLinhaEditavel();
		}
	}
	
	public void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public void salvar() {
		this.pedido = this.cadastroPedidoService.salvar(this.pedido);
		
		FacesUtil.addInfoMessage("Pedido salvo com sucesso!");
	}
	
	public boolean isEditando() {
		return this.pedido.getId() != null;
	}
	
	public List<Cliente> completarClientes(String nome){
		return this.clientes.buscarPorNome(nome);
	}
	
	public List<Produto> completarProduto(String nome){
		return this.produtos.buscarPorNome(nome);
	}
	
	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = this.pedido.getItensPedido().get(0);
		
		if(this.produtoLinhaEditavel != null) {
			
			if(this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil.addErrorMessage("Já existe um item no pedido com o produto informado.");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());
				
				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;
				this.pedido.recalcularValorTotal();
			}
		}
	}
	
	public void atualizarQuantidade(ItemPedido item, int linha) {
		if(item.getQuantidade() < 1) {
			if(linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getPedido().getItensPedido().remove(linha);
			}
		}
		this.pedido.recalcularValorTotal();
	}
	
	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		for(ItemPedido item : this.getPedido().getItensPedido()) {
			if(produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}
		
		return existeItem;
	}

	public void recalcularPedido() {
		if (this.pedido != null) {
			this.pedido.recalcularValorTotal();
		}
	}
	
	public FormaDePagamento[] getFormasDePagamento() {
		return FormaDePagamento.values();
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
}