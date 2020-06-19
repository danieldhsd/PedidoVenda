package br.com.danieldhsd.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnderecoEntrega {
	
	@Column(name = "ENTREGA_LOGRADOURO", nullable = false, length = 150)
	private String logradouro;

	@Column(name = "ENTREGA_NUMERO", nullable = false, length = 10)
	private String numero;
	
	@Column(name = "ENTREGA_COMPLEMENTO", length = 50)
	private String complemento;
	
	@Column(name = "ENTREGA_CIDADE", nullable = false, length = 50)
	private String cidade;
	
	@Column(name = "ENTREGA_UF", nullable = false, length = 50)
	private String uf;
	
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
