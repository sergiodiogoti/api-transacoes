package br.edu.infinet.app.controller;

import br.edu.infinet.app.dto.request.ContaRequestDTO;
import br.edu.infinet.app.dto.response.ContaResponseDTO;
import br.edu.infinet.app.mapper.GenericMapper;
import br.edu.infinet.app.service.ContaService;
import br.edu.infinet.app.domain.enums.TipoConta;
import br.edu.infinet.app.domain.model.Conta;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/contas")
public class ContaController {

	private final ContaService contaService;
	private final GenericMapper genericMapper;
	
	public ContaController(ContaService contaService, GenericMapper genericMapper) {
		this.contaService = contaService;
		this.genericMapper = genericMapper;
	}
	
	@PostMapping
	public ResponseEntity<ContaResponseDTO> incluir(@Valid  @RequestBody ContaRequestDTO contaRequestDTO) {
		ContaResponseDTO contaCriada = contaService.incluir(genericMapper.map(contaRequestDTO, Conta.class));
		return ResponseEntity.status(HttpStatus.CREATED).body(contaCriada);
	}
		
	@PutMapping(value = "/{id}") 
	public ResponseEntity<Void> alterar(@PathVariable Integer id, @Valid @RequestBody Conta conta) {
		contaService.alterar(id, conta);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		contaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}/principal")
	public ResponseEntity<Void> marcarComoPrincipal(@PathVariable Integer id) {
		contaService.marcarComoPrincipal(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ContaResponseDTO>> obterLista(){
		List<ContaResponseDTO> lista = contaService.obterLista();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContaResponseDTO> obterPorId(@PathVariable Integer id){
		Conta conta = contaService.obterPorId(id);
		return ResponseEntity.ok(genericMapper.map(conta,ContaResponseDTO.class));
	}

	@GetMapping("/cpf/{cpf}/tipo/{tipo}")
	public ResponseEntity<List<ContaResponseDTO>> obterPorCpfETipo(
			@PathVariable String cpf,
			@PathVariable String tipo) {

		TipoConta tipoEnum = TipoConta.valueOf(tipo.toUpperCase());
		List<ContaResponseDTO> lista = contaService.obterPorCpfETipo(cpf, tipoEnum);
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/cpf/{cpf}/saldo-maior/{valor}")
	public ResponseEntity<List<ContaResponseDTO>> obterPorSaldoMaiorQue(
			@PathVariable  String cpf,
			@PathVariable  Double valor) {

		List<ContaResponseDTO> lista = contaService.obterPorSaldoMaiorQue(cpf, valor);
		return ResponseEntity.ok(lista);
	}
}