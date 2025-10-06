package br.edu.infinet.app.service;

import java.util.List;

import br.edu.infinet.app.dto.response.UsuarioResponseDTO;
import br.edu.infinet.app.exception.ResourceInvalidException;
import br.edu.infinet.app.exception.ResourceNotFoundException;
import br.edu.infinet.app.mapper.GenericMapper;
import br.edu.infinet.app.repository.UsuarioRepository;
import br.edu.infinet.app.domain.model.Usuario;
import br.edu.infinet.app.util.ConstanteUtil;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {


	private final UsuarioRepository usuarioRepository;

	private final GenericMapper genericMapper;

	public UsuarioService(UsuarioRepository usuarioRepository, GenericMapper genericMapper) {
		this.usuarioRepository = usuarioRepository;
		this.genericMapper = genericMapper;
	}


	@Transactional
	public UsuarioResponseDTO incluir(Usuario usuario) {
		if(usuario.getId() != null && usuario.getId() != 0) {
			throw new ResourceInvalidException(ConstanteUtil .ERRO_ID_REGISTRO_NOVO);
		}
		usuarioRepository.findByCpf(usuario.getCpf())
				.ifPresent(u -> {
					throw new DataIntegrityViolationException(ConstanteUtil.ERRO_USUARIO_JA_EXISTE);
		});
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		return genericMapper.map(usuarioSalvo, UsuarioResponseDTO.class);
	}


	@Transactional
	public void alterar(Integer id, Usuario usuario) {

		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_ALTERACAO_ID_INVALIDO);
		}
		obterPorId(id);
		usuario.setId(id);
		usuarioRepository.save(usuario);
	}


	@Transactional
	public void excluir(Integer id)  {
		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_EXCLUSAO_ID_INVALIDO);
		}
		Usuario usuario = obterPorId(id);
		usuarioRepository.delete(usuario);
	}



	@Transactional(readOnly = true)
	public List<UsuarioResponseDTO> obterLista() {

		List<UsuarioResponseDTO> usuarios = usuarioRepository.findAll()
				.stream().map(usuario -> genericMapper.map(usuario, UsuarioResponseDTO.class))
				.toList();

		if(usuarios.isEmpty()){
			throw new ResourceNotFoundException(ConstanteUtil.MSG_NENHUM_USUARIO_ENCONTRADO);
		}

		return usuarios;
	}

	@Transactional(readOnly = true)
	public Usuario obterPorId(Integer id) {
		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_BUSCA_ID_INVALIDO);
		}
        return usuarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstanteUtil.MSG_NENHUM_USUARIO_ENCONTRADO_PARA_ID_INFORMADO));
	}

	@Transactional(readOnly = true)
	public Usuario obterPorCpf(String cpf) {
		if (cpf == null) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_BUSCA_CPF_INVALIDO);
		}
		return usuarioRepository.findByCpf(cpf)
				.orElseThrow(() -> new ResourceNotFoundException(ConstanteUtil.MSG_NENHUM_USUARIO_ENCONTRADO_PARA_CPF_INFORMADO));
	}


	@Transactional(readOnly = true)
	public List<UsuarioResponseDTO> obterPorRendaEntre(double min, double max) {
		if (min < 0 || max < 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_RENDA_INVALIDA);
		}
		List<UsuarioResponseDTO> lista = usuarioRepository.findByRendaMensalBetween(min, max)
				.stream().map(usuario -> genericMapper.map(usuario, UsuarioResponseDTO.class))
				.toList();

		if (lista.isEmpty()) {
			throw new ResourceNotFoundException(ConstanteUtil.MSG_NENHUM_USUARIO_ENCONTRADO_PARA_A_RENDA);
		}
		return lista;
	}

} 