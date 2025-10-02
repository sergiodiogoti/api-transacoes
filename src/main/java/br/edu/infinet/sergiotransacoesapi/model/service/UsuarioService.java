package br.edu.infinet.sergiotransacoesapi.model.service;

import br.edu.infinet.sergiotransacoesapi.model.domain.Usuario;
import br.edu.infinet.sergiotransacoesapi.model.domain.exceptions.ResourceInvalidException;
import br.edu.infinet.sergiotransacoesapi.model.domain.exceptions.ResourceNotFoundException;
import br.edu.infinet.sergiotransacoesapi.model.repository.UsuarioRepository;
import br.edu.infinet.sergiotransacoesapi.model.util.ConstanteUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService implements CrudService<Usuario, Integer> {


	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}


	@Override
	@Transactional
	public Usuario incluir(Usuario usuario) {
		if(usuario.getId() != null && usuario.getId() != 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_ID_REGISTRO_NOVO);
		}
		usuarioRepository.findByCpf(usuario.getCpf())
				.ifPresent(u -> {
					throw new DataIntegrityViolationException(ConstanteUtil.ERRO_USUARIO_JA_EXISTE);
		});
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public Usuario alterar(Integer id, Usuario usuario) {

		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_ALTERACAO_ID_INVALIDO);
		}
		obterPorId(id);
		usuario.setId(id);
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void excluir(Integer id)  {
		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_EXCLUSAO_ID_INVALIDO);
		}
		Usuario usuario = obterPorId(id);
		usuarioRepository.delete(usuario);
	}

	@Transactional
	public Usuario inativar(Integer id) {

		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_INATIVACAO_ID_INVALIDO);
		}
		Usuario usuario = obterPorId(id);
		if(!usuario.isAtivo()) {
			return usuario;
		}
		usuario.setAtivo(false);
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> obterLista() {
		List<Usuario> lista = usuarioRepository.findAll();
		if(lista.isEmpty()){
			throw new ResourceNotFoundException(ConstanteUtil.MSG_NENHUM_USUARIO_ENCONTRADO);
		}
		return lista;
	}

	@Override
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
	public List<Usuario> obterPorPerfil(String perfil) {
		List<Usuario> lista = usuarioRepository.findByPerfilIgnoreCase(perfil);
		if (lista.isEmpty()) {
			throw new ResourceNotFoundException(ConstanteUtil.MSG_NENHUM_USUARIO_ENCONTRADO_PARA_O_PERFIL);
		}
		return lista;
	}

	@Transactional(readOnly = true)
	public List<Usuario> obterPorRendaEntre(double min, double max) {
		if (min < 0 || max < 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_RENDA_INVALIDA);
		}
		List<Usuario> lista = usuarioRepository.findByRendaMensalBetween(min, max);
		if (lista.isEmpty()) {
			throw new ResourceNotFoundException(ConstanteUtil.MSG_NENHUM_USUARIO_ENCONTRADO_PARA_A_RENDA);
		}
		return lista;
	}

} 