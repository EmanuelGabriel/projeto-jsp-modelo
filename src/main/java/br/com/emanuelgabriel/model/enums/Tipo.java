package br.com.emanuelgabriel.model.enums;

public enum Tipo {

	FIXO, 
	CELULAR, 
	RESIDENCIAL;

	
	public static Tipo getTipo(String tipo) {
		for (Tipo t : Tipo.values()) {
			if (t.toString().equalsIgnoreCase(tipo.toUpperCase())) {
				return t;
			}
		}
		return null;
	}

}
