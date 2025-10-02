package br.edu.infinet.sergiotransacoesapi.controller;


import br.edu.infinet.sergiotransacoesapi.model.domain.Conta;
import br.edu.infinet.sergiotransacoesapi.model.domain.enums.TipoConta;
import br.edu.infinet.sergiotransacoesapi.model.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/contas")
public class ContaController {

	private final ContaService contaService;
	
	public ContaController(ContaService contaService) {
		this.contaService = contaService;
	}
	
	@PostMapping
	public ResponseEntity<Conta> incluir(@Valid  @RequestBody Conta conta) {
		Conta contaCriada = contaService.incluir(conta);
		return ResponseEntity.status(HttpStatus.CREATED).body(contaCriada);
	}
		
	@PutMapping(value = "/{id}") 
	public ResponseEntity<Conta> alterar(@PathVariable Integer id, @Valid @RequestBody Conta conta) {
		contaService.alterar(id, conta);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		contaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}/principal")
	public ResponseEntity<Conta> marcarComoPrincipal(@PathVariable Integer id) {
		contaService.marcarComoPrincipal(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Conta>> obterLista(){
		List<Conta> lista = contaService.obterLista();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Conta> obterPorId(@PathVariable Integer id){
		Conta conta = contaService.obterPorId(id);
		return ResponseEntity.ok(conta);
	}

	@GetMapping("/cpf/{cpf}/tipo/{tipo}")
	public ResponseEntity<List<Conta>> obterPorCpfETipo(
			@PathVariable String cpf,
			@PathVariable String tipo) {

		TipoConta tipoEnum = TipoConta.valueOf(tipo.toUpperCase());
		List<Conta> lista = contaService.obterPorCpfETipo(cpf, tipoEnum);
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/cpf/{cpf}/saldo-maior/{valor}")
	public ResponseEntity<List<Conta>> obterPorSaldoMaiorQue(
			@PathVariable  String cpf,
			@PathVariable  Double valor) {

		List<Conta> lista = contaService.obterPorSaldoMaiorQue(cpf, valor);
		return ResponseEntity.ok(lista);
	}
}