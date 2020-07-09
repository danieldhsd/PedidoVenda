package br.com.danieldhsd.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;

import br.com.danieldhsd.model.Pedido;
import br.com.danieldhsd.util.jsf.FacesUtil;
import br.com.danieldhsd.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioPedidoEmailController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Mailer mailer;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	public void enviarPedido() {
		MailMessage message = mailer.novaMensagem();
		
		FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
	}
}