package br.com.danieldhsd.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.danieldhsd.model.Usuario;

public class UsuariosRepository implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Usuario buscarPorId(Long id) {
		return this.manager.find(Usuario.class, id);
	}
	
	public List<Usuario> buscarVendedores() {
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	public Usuario buscarPorEmail(String email) {
		Usuario usuario = null;
		
		try {
			usuario = this.manager.createQuery("from Usuario where lower(email) = :EMAIL", 
					Usuario.class)
					.setParameter("EMAIL", email.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			
		}
		return usuario;
	}
}
