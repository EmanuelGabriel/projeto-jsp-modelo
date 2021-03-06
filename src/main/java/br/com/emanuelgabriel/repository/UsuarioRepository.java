package br.com.emanuelgabriel.repository;

import java.util.List;

import br.com.emanuelgabriel.dtos.TelefoneModelResponse;
import br.com.emanuelgabriel.model.Usuario;
import br.com.emanuelgabriel.service.GenericService;

public interface UsuarioRepository extends GenericService<Usuario> {

	Long quantidadeTelefoneUsuario(Usuario usuario);

	Long quantidadeMaximaUsuarios();

	List<TelefoneModelResponse> buscarTelefones(Long codigoUsuario);

}
