package br.com.danieldhsd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class EnderecoEntrega implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 150)
	@Column(name = "ENTREGA_LOGRADOURO", nullable = false, length = 150)
	private String logradouro;
	
	@NotBlank
	@Size(max = 10)
	@Column(name = "ENTREGA_NUMERO", nullable = false, length = 10)
	private String numero;
	
	@Size(max = 50)
	@Column(name = "ENTREGA_COMPLEMENTO", length = 50)
	private String complemento;
	
	@NotBlank
	@Size(max = 50)
	@Column(name = "ENTREGA_CIDADE", nullable = false, length = 50)
	private String cidade;
	
	@NotBlank
	@Size(max = 50)
	@Column(name = "ENTREGA_UF", nullable = false, length = 50)
	private String uf;
	
	@NotBlank
	@Size(max = 9)
	@Column(name = "ENTREGA_CEP", nullable = false, length = 9)
	private String cep;
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
}
