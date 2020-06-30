package br.com.danieldhsd.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.danieldhsd.model.Produto;
import br.com.danieldhsd.repository.ProdutosRepository;
import br.com.danieldhsd.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter{
	
	private ProdutosRepository produtos;
	
	public ProdutoConverter() {
		produtos = CDIServiceLocator.getBean(ProdutosRepository.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Produto Produto = null;
		if(value != null) {
			Long id = new Long(value);
			Produto = produtos.buscarPorId(id);
		}
		return Produto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Produto produto = (Produto) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}
		return "";
	}
}
