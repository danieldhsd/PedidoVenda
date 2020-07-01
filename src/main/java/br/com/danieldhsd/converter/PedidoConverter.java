package br.com.danieldhsd.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.danieldhsd.model.Pedido;
import br.com.danieldhsd.repository.PedidosRepository;
import br.com.danieldhsd.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter{
	
	private PedidosRepository pedidos;
	
	public PedidoConverter() {
		pedidos = CDIServiceLocator.getBean(PedidosRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Pedido pedido = null;
		if(value != null) {
			Long id = new Long(value);
			pedido = pedidos.buscarPorId(id);
		}
		return pedido;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		return "";
	}
}
