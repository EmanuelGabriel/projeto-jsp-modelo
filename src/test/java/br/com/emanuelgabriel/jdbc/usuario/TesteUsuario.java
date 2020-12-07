package br.com.emanuelgabriel.jdbc.usuario;

import java.util.List;

import org.junit.Test;

import br.com.emanuelgabriel.model.Usuario;
import br.com.emanuelgabriel.repository.UsuarioRepository;
import br.com.emanuelgabriel.service.UsuarioServiceImpl;
import br.com.emanuelgabriel.service.exception.RegraNegocioException;

public class TesteUsuario {

	private static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";
	private static final String NENHUM_REGISTRO_ENCONTRADO = "Nenhum registro encontrado";

	private UsuarioRepository usuarioRepository;

	public TesteUsuario() {
		this.usuarioRepository = new UsuarioServiceImpl();
	}

	@Test
	public void criar() {

		Usuario usuario = new Usuario();
		usuario.setNome("Jonas Lima Duarte");
		usuario.setLogin("jonaslima");
		usuario.setSenha("892839");
		usuario.setCpf("01080946588");

		this.usuarioRepository.criar(usuario);
		System.out.println("Usuário " + usuario.getNome() + " cadastrado com sucesso!");

	}

	@Test
	public void buscarTodos() {

		List<Usuario> usuarios = this.usuarioRepository.findAll();
		if (usuarios.isEmpty()) {
			throw new RegraNegocioException(NENHUM_REGISTRO_ENCONTRADO);
		}

		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}

	}

	@Test
	public void buscarPorCodigo() {

		Usuario usuario = this.usuarioRepository.findByCodigo(4L);
		if (usuario != null) {
			System.out.println(usuario);
		}

		throw new RegraNegocioException(USUARIO_NAO_ENCONTRADO);

	}

	@Test
	public void atualizar() {

		Usuario usuario = this.usuarioRepository.findByCodigo(6L);

		// usuario.setNome("Carlito Tadeu Monteiro");
		usuario.setLogin("jonas2020");
		usuario.setSenha("10203040");
		// usuario.setCpf("04067845633");

		this.usuarioRepository.update(usuario);
		System.out.println("Usuário '" + usuario.getNome() + "' atualizado com sucesso!");

	}

	@Test
	public void remover() {

		Usuario usuario = this.usuarioRepository.findByCodigo(4L);
		if (usuario != null) {
			this.usuarioRepository.remover(usuario);
			System.out.println("Usuário de código '" + usuario.getCodigo() + "' removido com sucesso!");
		}

		System.out.println(USUARIO_NAO_ENCONTRADO);
		throw new RegraNegocioException(USUARIO_NAO_ENCONTRADO);

	}

}
