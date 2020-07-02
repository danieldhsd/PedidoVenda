package br.com.danieldhsd.controller;

import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.danieldhsd.model.Cliente;
import br.com.danieldhsd.model.EnderecoEntrega;
import br.com.danieldhsd.model.FormaDePagamento;
import br.com.danieldhsd.model.Pedido;
import br.com.danieldhsd.model.Usuario;
import br.com.danieldhsd.repository.ClientesRepository;
import br.com.danieldhsd.repository.UsuariosRepository;
import br.com.danieldhsd.service.CadastroPedidoService;
import br.com.danieldhsd.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPedidoController {
	
	@Inject
	private UsuariosRepository usuarios;
	
	@Inject
	private ClientesRepository clientes;
	
	private CadastroPedidoService cadastroPedidoService;
	
	private Pedido pedido;
	private List<Usuario> vendedores;
	
	public CadastroPedidoController() {
		limpar();
	}
	
	public void inicializar() {
		if(FacesUtil.isNotPostBack()) {
			this.vendedores = this.usuarios.buscarVendedores();
			
			this.recalcularPedido();
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
	
}