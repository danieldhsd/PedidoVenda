package br.com.danieldhsd.controller;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

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
		
		VelocityTemplate template;
		String pedido = "/emails/pedido.template";
		pedido.getClass().getResourceAsStream(pedido);
		
		template = new VelocityTemplate(pedido);
		
		message.to(this.pedido.getCliente().getEmail())
			.subject("Pedido " + this.pedido.getId())
			.put("locale", new Locale("pt", "BR"))
			.put("numberTool", new NumberTool())
			.put("pedido", this.pedido)
			.bodyHtml(template)
			.send();
		
		FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
	}
}
