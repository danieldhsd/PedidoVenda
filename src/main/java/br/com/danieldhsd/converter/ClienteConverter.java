package br.com.danieldhsd.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.danieldhsd.model.Cliente;
import br.com.danieldhsd.repository.ClientesRepository;
import br.com.danieldhsd.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

	private ClientesRepository clientes;
	
	public ClienteConverter() {
		this.clientes = (ClientesRepository) CDIServiceLocator.getBean(ClientesRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;
		
		if(value != null) {
			retorno = this.clientes.buscarPorId(new Long(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Cliente) value).getId().toString();
		}
		return "";
	}

}
