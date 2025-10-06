package br.edu.infinet.app.service;

import br.edu.infinet.app.dto.response.ContaResponseDTO;
import br.edu.infinet.app.exception.ResourceInvalidException;
import br.edu.infinet.app.exception.ResourceNotFoundException;
import br.edu.infinet.app.mapper.GenericMapper;
import br.edu.infinet.app.repository.ContaRepository;
import br.edu.infinet.app.domain.enums.TipoConta;
import br.edu.infinet.app.domain.model.Conta;
import br.edu.infinet.app.domain.model.Usuario;
import br.edu.infinet.app.util.ConstanteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaService {


	private final UsuarioService usuarioService;
	private final ContaRepository contaRepository;
	private final GenericMapper genericMapper;
	private final CambioService cambioService;

	public ContaService(UsuarioService usuarioService, ContaRepository contaRepository, GenericMapper genericMapper, CambioService cambioService) {
		this.usuarioService = usuarioService;
		this.contaRepository = contaRepository;
		this.genericMapper = genericMapper;
		this.cambioService = cambioService;
	}

	@Transactional
	public ContaResponseDTO incluir(Conta conta) {
		if(conta.getId() != null && conta.getId() != 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_ID_REGISTRO_NOVO);
		}
		if(conta.getId() != null && conta.getSaldo() < 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_CONTA_NOVA_SALDO_NEGATIVO);
		}
		Usuario usuario = usuarioService.obterPorId(conta.getUsuario().getId());
		conta.setUsuario(usuario);
		var contaSalva = contaRepository.save(conta);
		return genericMapper.map(contaSalva,ContaResponseDTO.class);
	}


	@Transactional
	public void alterar(Integer id, Conta conta) {
		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_ALTERACAO_ID_INVALIDO);
		}
		obterPorId(id);
		Usuario usuario = usuarioService.obterPorId(conta.getUsuario().getId());
		conta.setUsuario(usuario);
		conta.setId(id);
		contaRepository.save(conta);
	}


	@Transactional
	public void excluir(Integer id) {
		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_EXCLUSAO_ID_INVALIDO);
		}
		obterPorId(id);
		contaRepository.deleteById(id);
	}

	@Transactional
	public Conta marcarComoPrincipal(Integer id) {
		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_ID_MARCAR_CONTA_PRINCIPAL);
		}
		Conta conta = obterPorId(id);
		if(!conta.isPrincipal()) {
			conta.setPrincipal(true);
			contaRepository.save(conta);
		}
		return conta;
	}

	@Transactional(readOnly = true)
	public List<ContaResponseDTO> obterLista() {
		List<Conta> lista = contaRepository.findAll();
		if(lista.isEmpty()){
			throw new ResourceNotFoundException(ConstanteUtil.MSG_NENHUMA_CONTA_ENCONTRADA);
		}
		return genericMapper.mapList(lista,ContaResponseDTO.class);
	}

	@Transactional(readOnly = true)
	public Conta obterPorId(Integer id) {
		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_BUSCA_ID_INVALIDO);
		}
		return contaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstanteUtil.MSG_NENHUMA_CONTA_ENCONTRADA_PARA_ID_INFORMADO));
	}

	@Transactional(readOnly = true)
	public List<ContaResponseDTO> obterPorCpfETipo(String cpf, TipoConta tipo) {
		List<Conta> lista = contaRepository.findByUsuarioCpfAndTipo(cpf, tipo);
		if(lista.isEmpty()){
			throw new ResourceNotFoundException(ConstanteUtil.MSG_NENHUMA_CONTA_ENCONTRADA_PARA_CPF_E_TIPO_INFORMADO);
		}
		return genericMapper.mapList(lista,ContaResponseDTO.class);
	}

	@Transactional(readOnly = true)
	public List<ContaResponseDTO> obterPorSaldoMaiorQue(String cpf, Double valor) {
		List<Conta> lista = contaRepository.findByUsuarioCpfAndSaldoGreaterThan(cpf, valor);
		if(lista.isEmpty()){
			throw new ResourceNotFoundException(ConstanteUtil.MSG_NAO_HA_CONTAS_COM_SALDO_MAIOR_QUE_O_VALOR_INFORMADO);
		}
		return genericMapper.mapList(lista,ContaResponseDTO.class);
	}
} 