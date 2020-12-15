package br.com.emanuelgabriel.model;

public class Endereco {

	private Long codigo;
	private String numero;
	private String cep;
	private String logradouro;
	private String rua;
	private String cidade;
	private String estado;

	public Endereco() {
	}

	public Endereco(Long codigo, String numero, String cep, String logradouro, String rua, String cidade,
			String estado) {
		this.codigo = codigo;
		this.numero = numero;
		this.cep = cep;
		this.logradouro = logradouro;
		this.rua = rua;
		this.cidade = cidade;
		this.estado = estado;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Endereco [codigo=" + codigo + ", numero=" + numero + ", cep=" + cep + ", logradouro=" + logradouro
				+ ", rua=" + rua + ", cidade=" + cidade + ", estado=" + estado + "]";
	}

}
