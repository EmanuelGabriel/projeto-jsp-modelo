package br.com.emanuelgabriel.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.emanuelgabriel.dtos.TelefoneModelResponse;
import br.com.emanuelgabriel.model.Usuario;
import br.com.emanuelgabriel.persistencia.jdbc.SingleConnectionJDBC;
import br.com.emanuelgabriel.repository.UsuarioRepository;

public class UsuarioServiceImpl implements UsuarioRepository {

	private static final long serialVersionUID = 1L;

	private Connection conexao;

	public UsuarioServiceImpl() {
		conexao = SingleConnectionJDBC.getConnection();
	}

	@Override
	public void criar(Usuario usuario) {

		String sql = "INSERT INTO usuario (nome, login, senha, cpf) VALUES (?,?,?,?)";

		try {

			PreparedStatement preparador = this.conexao.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setString(4, usuario.getCpf());

			preparador.execute();

			// fechando a conexão
			preparador.close();

		} catch (SQLException ex) {
			Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
		}

	}

	@Override
	public List<Usuario> findAll() {

		String sql = "SELECT * FROM usuario";
		List<Usuario> usuarios = new ArrayList<>();

		try {

			PreparedStatement preparador = this.conexao.prepareStatement(sql);
			ResultSet resultSet = preparador.executeQuery();

			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setCodigo(resultSet.getLong("codigo"));
				usuario.setNome(resultSet.getString("nome"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setCpf(resultSet.getString("cpf"));

				usuarios.add(usuario);
			}

			// fechando a conexão
			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuarios;
	}

	@Override
	public Usuario update(Usuario usuario) {

		String sql = "UPDATE usuario SET nome=?, login=?, senha=? WHERE codigo = " + usuario.getCodigo();

		try {

			PreparedStatement preparador = this.conexao.prepareStatement(sql);
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());

			preparador.execute();
			// fechando a conexão
			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	@Override
	public Usuario findByCodigo(Long codigo) {

		Usuario usuario = new Usuario();
		String sql = "SELECT * FROM usuario WHERE codigo = " + codigo;

		try {

			PreparedStatement preparador = this.conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();

			while (resultado.next()) {
				usuario.setCodigo(resultado.getLong("codigo"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				usuario.setCpf(resultado.getString("cpf"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	@Override
	public void remover(Usuario usuario) {

		String sql = "DELETE FROM usuario WHERE codigo = " + usuario.getCodigo();

		try {

			PreparedStatement preparador = this.conexao.prepareStatement(sql);
			preparador.execute();

			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<TelefoneModelResponse> buscarTelefones(Long codigoUsuario) {

		List<TelefoneModelResponse> telefones = new ArrayList<>();

		String sql = "SELECT nome, cpf, numero, tipo FROM telefone tel INNER JOIN usuario u ON tel.usuario_codigo = u.codigo WHERE u.codigo = "
				+ codigoUsuario;

		try {

			PreparedStatement preparador = this.conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				TelefoneModelResponse telefone = new TelefoneModelResponse();
				telefone.setNome(resultado.getString("nome"));
				telefone.setCpf(resultado.getString("cpf"));
				telefone.setNumero(resultado.getString("numero"));
				telefone.setTipo(resultado.getString("tipo"));

				telefones.add(telefone);

			}

			// fechando a conexão
			preparador.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return telefones;

	}

}
