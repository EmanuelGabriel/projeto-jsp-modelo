package br.com.emanuelgabriel.jdbc.usuario;

import java.util.List;

import org.junit.Test;

import br.com.emanuelgabriel.dtos.TelefoneModelResponse;
import br.com.emanuelgabriel.model.Telefone;
import br.com.emanuelgabriel.model.Usuario;
import br.com.emanuelgabriel.model.enums.Tipo;
import br.com.emanuelgabriel.repository.TelefoneRepository;
import br.com.emanuelgabriel.repository.UsuarioRepository;
import br.com.emanuelgabriel.service.TelefoneServiceImpl;
import br.com.emanuelgabriel.service.UsuarioServiceImpl;
import br.com.emanuelgabriel.service.exception.RegraNegocioException;

public class TesteTelefone {

	private static final String TELEFONE_USUARIO_NAO_ENCONTRADO = "Telefone para esse usuário não encontrado";
	private static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado";
	private static final String USUARIO_REGISTRO_ENCONTRADO = "Usuário não encontrado";
	private static final String TELEFONE_NUMERO_NAO_ENCONTRADO = "Telefone não encontrado";

	private UsuarioRepository usuarioRepository;
	private TelefoneRepository telefoneRepository;

	public TesteTelefone() {
		this.usuarioRepository = new UsuarioServiceImpl();
		this.telefoneRepository = new TelefoneServiceImpl();
	}

	@Test
	public void buscarTelefonesUsuarios() {

		List<TelefoneModelResponse> telefones = this.usuarioRepository.buscarTelefones(3L);
		if (telefones.isEmpty()) {
			throw new RegraNegocioException(TELEFONE_USUARIO_NAO_ENCONTRADO);
		}

		for (TelefoneModelResponse telefone : telefones) {
			System.out.println(telefone);
		}

	}

	@Test
	public void buscarPorNumero() {

		String numero = "8639556845";
		Telefone telefone = this.telefoneRepository.buscarPorNumero(numero);

		if (telefone.getNumero() == null) {
			throw new RegraNegocioException(TELEFONE_NUMERO_NAO_ENCONTRADO);
		}

		System.out.println(telefone);

	}

	@Test
	public void salvar() {

		Usuario usuario = this.usuarioRepository.findByCodigo(2L);
		if (usuario.getCodigo() == null) {
			throw new RegraNegocioException(USUARIO_REGISTRO_ENCONTRADO);
		}

		Telefone telefone = new Telefone();
		telefone.setNumero("85988672390");
		telefone.setTipo(Tipo.CELULAR);
		telefone.setCodigoUsuario(usuario.getCodigo());

		this.telefoneRepository.criar(telefone);
		System.out.println(telefone);

	}

	@Test
	public void findAll() {

		List<Telefone> telefones = this.telefoneRepository.findAll();
		if (telefones.isEmpty()) {
			throw new RegraNegocioException(NENHUM_REGISTRO_ENCONTRADO);
		}

		telefones.forEach(tel -> {
			System.out.println(tel);
		});

	}

	@Test
	public void atualizar() {

		Telefone telefone = this.telefoneRepository.findByCodigo(4L);
		if (telefone.getCodigo() == null) {
			throw new RegraNegocioException(NENHUM_REGISTRO_ENCONTRADO);
		}

		telefone.setNumero("86981663488");
		telefone.setTipo(Tipo.CELULAR);

		this.telefoneRepository.update(telefone);
		System.out.println("Número telefone '" + telefone.getNumero() + "' atualizado");

	}

}
