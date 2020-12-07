package br.com.emanuelgabriel.dtos;

public class TelefoneDtoResponse {

	private String nome;
	private String cpf;
	private String numero;
	private String tipo;

	public TelefoneDtoResponse() {
	}

	public TelefoneDtoResponse(String nome, String cpf, String numero, String tipo) {
		this.nome = nome;
		this.cpf = cpf;
		this.numero = numero;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "TelefoneDTOResponse [nome=" + nome + ", cpf=" + cpf + ", numero=" + numero + ", tipo=" + tipo + "]";
	}

}
