package br.com.emanuelgabriel.jdbc.usuario;

import java.util.List;

import org.junit.Test;

import br.com.emanuelgabriel.dtos.TelefoneDtoResponse;
import br.com.emanuelgabriel.repository.UsuarioRepository;
import br.com.emanuelgabriel.service.UsuarioServiceImpl;
import br.com.emanuelgabriel.service.exception.RegraNegocioException;

public class TesteTelefone {

	private static final String TELEFONE_USUARIO_NAO_ENCONTRADO = "Telefone para esse usuário não encontrado";

	private UsuarioRepository usuarioRepository;

	public TesteTelefone() {
		this.usuarioRepository = new UsuarioServiceImpl();
	}

	@Test
	public void buscarTelefonesUsuarios() {

		List<TelefoneDtoResponse> telefones = this.usuarioRepository.buscarTelefones(3L);
		if (telefones.isEmpty()) {
			throw new RegraNegocioException(TELEFONE_USUARIO_NAO_ENCONTRADO);
		}

		for (TelefoneDtoResponse telefone : telefones) {
			System.out.println(telefone);
		}

	}

}
