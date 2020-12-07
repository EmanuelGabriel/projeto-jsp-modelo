package br.com.emanuelgabriel.jdbc;

import org.junit.Test;

import br.com.emanuelgabriel.persistencia.jdbc.SingleConnectionJDBC;

public class TesteBancoJDBC {

	@Test
	public void inicializarBanco() {
		SingleConnectionJDBC.getConnection();
	}

}
