package br.edu.infinet.sergiotransacoesapi.model.service;

import br.edu.infinet.sergiotransacoesapi.model.domain.Conta;
import br.edu.infinet.sergiotransacoesapi.model.domain.Usuario;
import br.edu.infinet.sergiotransacoesapi.model.domain.enums.TipoConta;
import br.edu.infinet.sergiotransacoesapi.model.domain.exceptions.ResourceInvalidException;
import br.edu.infinet.sergiotransacoesapi.model.domain.exceptions.ResourceNotFoundException;
import br.edu.infinet.sergiotransacoesapi.model.repository.ContaRepository;
import br.edu.infinet.sergiotransacoesapi.model.util.ConstanteUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaService implements CrudService<Conta, Integer> {


	private final UsuarioService usuarioService;
	private final ContaRepository contaRepository;

	public ContaService(UsuarioService usuarioService, ContaRepository contaRepository) {
		this.usuarioService = usuarioService;
		this.contaRepository = contaRepository;
	}

	@Override
	@Transactional
	public Conta incluir(Conta conta) {
		if(conta.getId() != null && conta.getId() != 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_ID_REGISTRO_NOVO);
		}
		if(conta.getId() != null && conta.getSaldo() < 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_CONTA_NOVA_SALDO_NEGATIVO);
		}
		Usuario usuario = usuarioService.obterPorId(conta.getUsuario().getId());
		conta.setUsuario(usuario);
		return contaRepository.save(conta);
	}

	@Override
	@Transactional
	public Conta alterar(Integer id, Conta conta) {
		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_ALTERACAO_ID_INVALIDO);
		}
		obterPorId(id);
		Usuario usuario = usuarioService.obterPorId(conta.getUsuario().getId());
		conta.setUsuario(usuario);
		conta.setId(id);

		return contaRepository.save(conta);
	}

	@Override
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

	@Override
	@Transactional(readOnly = true)
	public List<Conta> obterLista() {
		List<Conta> lista = contaRepository.findAll();
		if(lista.isEmpty()){
			throw new ResourceNotFoundException(ConstanteUtil.MSG_NENHUMA_CONTA_ENCONTRADA);
		}
		return lista;
	}

	@Override
	@Transactional(readOnly = true)
	public Conta obterPorId(Integer id) {
		if(id == null || id == 0) {
			throw new ResourceInvalidException(ConstanteUtil.ERRO_BUSCA_ID_INVALIDO);
		}
		return contaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstanteUtil.MSG_NENHUMA_CONTA_ENCONTRADA_PARA_ID_INFORMADO));
	}

	@Transactional(readOnly = true)
	public List<Conta> obterPorCpfETipo(String cpf, TipoConta tipo) {
		List<Conta> lista = contaRepository.findByUsuarioCpfAndTipo(cpf, tipo);
		if(lista.isEmpty()){
			throw new ResourceNotFoundException(ConstanteUtil.MSG_NENHUMA_CONTA_ENCONTRADA_PARA_CPF_E_TIPO_INFORMADO);
		}
		return lista;
	}

	@Transactional(readOnly = true)
	public List<Conta> obterPorSaldoMaiorQue(String cpf, Double valor) {
		List<Conta> lista = contaRepository.findByUsuarioCpfAndSaldoGreaterThan(cpf, valor);
		if(lista.isEmpty()){
			throw new ResourceNotFoundException(ConstanteUtil.MSG_NAO_HA_CONTAS_COM_SALDO_MAIOR_QUE_O_VALOR_INFORMADO);
		}
		return lista;
	}
} 