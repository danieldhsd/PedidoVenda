package br.com.danieldhsd.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.danieldhsd.model.Categoria;
import br.com.danieldhsd.repository.CategoriasRepository;
import br.com.danieldhsd.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter{
	
	private CategoriasRepository categorias;
	
	public CategoriaConverter() {
		categorias = CDIServiceLocator.getBean(CategoriasRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Categoria categoria = null;
		if(value != null) {
			Long id = new Long(value);
			categoria = categorias.buscarPorId(id);
		}
		return categoria;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			return ((Categoria) value).getId().toString();
		}
		return "";
	}
	
	
}
