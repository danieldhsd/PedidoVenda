package br.com.danieldhsd.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.danieldhsd.model.Usuario;
import br.com.danieldhsd.repository.UsuariosRepository;
import br.com.danieldhsd.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {
	
	private UsuariosRepository usuarios;
	
	public UsuarioConverter() {
		this.usuarios = (UsuariosRepository) CDIServiceLocator.getBean(UsuariosRepository.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
		
		if(value != null) {
			retorno = this.usuarios.buscarPorId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Usuario) value).getId().toString();
		}
		return "";
	}
	
}
