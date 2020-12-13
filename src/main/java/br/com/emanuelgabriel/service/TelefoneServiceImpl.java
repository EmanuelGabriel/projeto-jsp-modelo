package br.com.emanuelgabriel.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.emanuelgabriel.model.Telefone;
import br.com.emanuelgabriel.model.enums.Tipo;
import br.com.emanuelgabriel.persistencia.jdbc.SingleConnectionJDBC;
import br.com.emanuelgabriel.repository.TelefoneRepository;

public class TelefoneServiceImpl implements TelefoneRepository {

	private static final long serialVersionUID = 1L;

	private Connection conexao;

	public TelefoneServiceImpl() {
		conexao = SingleConnectionJDBC.getConnection();
	}

	@Override
	public void criar(Telefone telefone) {

		String sql = "INSERT INTO telefone (numero, tipo, usuario_codigo) VALUES (?,?,?)";
		try {

			PreparedStatement preparador = this.conexao.prepareStatement(sql);
			preparador.setString(1, telefone.getNumero());
			preparador.setString(2, telefone.getTipo().toString());
			preparador.setLong(3, telefone.getCodigoUsuario());

			preparador.execute();

			// fechando a conexão
			preparador.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Telefone> findAll() {

		String sql = "SELECT * FROM telefone";
		List<Telefone> telefones = new ArrayList<>();

		try {

			PreparedStatement preparador = this.conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();

			while (resultado.next()) {
				Telefone tel = new Telefone();
				tel.setCodigo(resultado.getLong("codigo"));
				tel.setNumero(resultado.getString("numero"));
				tel.setCodigoUsuario(resultado.getLong("usuario_codigo"));
				tel.setTipo(Tipo.getTipo(resultado.getString("tipo")));

				telefones.add(tel);
			}

			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return telefones;
	}

	@Override
	public Telefone update(Telefone telefone) {

		String sql = "UPDATE telefone SET numero=?, tipo=? WHERE codigo = " + telefone.getCodigo();

		try {

			PreparedStatement preparador = this.conexao.prepareStatement(sql);
			preparador.setString(1, telefone.getNumero());
			preparador.setString(2, telefone.getTipo().toString());

			preparador.executeUpdate();

			// fechando a conexão
			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return telefone;
	}

	@Override
	public Telefone findByCodigo(Long codigo) {
		Telefone telefone = new Telefone();
		String sql = "SELECT * FROM telefone WHERE codigo = " + codigo;

		try {

			PreparedStatement preparador = this.conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();

			while (resultado.next()) {
				telefone.setCodigo(resultado.getLong("codigo"));
				telefone.setCodigoUsuario(resultado.getLong("codigo"));
				telefone.setNumero(resultado.getString("numero"));
				telefone.setTipo(Tipo.valueOf(resultado.getString("tipo")));
			}

			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return telefone;
	}

	@Override
	public void remover(Telefone telefone) {

		String sql = "DELETE FROM telefone WHERE codigo = " + telefone.getCodigo();

		try {

			PreparedStatement preparador = this.conexao.prepareStatement(sql);
			preparador.executeUpdate();

			preparador.close();

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
