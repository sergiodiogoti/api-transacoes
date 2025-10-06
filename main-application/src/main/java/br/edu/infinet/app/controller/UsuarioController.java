package br.edu.infinet.app.controller;


import br.edu.infinet.app.dto.request.UsuarioRequestDTO;
import br.edu.infinet.app.dto.response.UsuarioResponseDTO;
import br.edu.infinet.app.mapper.GenericMapper;
import br.edu.infinet.app.service.UsuarioService;
import br.edu.infinet.app.domain.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final GenericMapper genericMapper;
	
	public UsuarioController(UsuarioService usuarioService, GenericMapper genericMapper) {
		this.usuarioService = usuarioService;
		this.genericMapper = genericMapper;
	}
	
	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> incluir(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO novoUsuario = usuarioService.incluir(genericMapper.map(usuarioRequestDTO, Usuario.class));
		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
	}
		
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> alterar(@PathVariable Integer id, @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
		usuarioService.alterar(id, genericMapper.map(usuarioRequestDTO, Usuario.class));
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		usuarioService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	

	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> obterLista(){
		List<UsuarioResponseDTO> lista = usuarioService.obterLista();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioResponseDTO> obterPorId(@PathVariable Integer id) {
		Usuario usuario = usuarioService.obterPorId(id);
		return ResponseEntity.ok(genericMapper.map(usuario, UsuarioResponseDTO.class));
	}

	@GetMapping(value = "/cpf/{cpf}")
	public ResponseEntity<UsuarioResponseDTO> obterPorCpf(@PathVariable String cpf) {
		Usuario usuario = usuarioService.obterPorCpf(cpf);
		return ResponseEntity.ok(genericMapper.map(usuario, UsuarioResponseDTO.class));
	}


	@GetMapping("/renda")
	public ResponseEntity<List<UsuarioResponseDTO>> obterPorRenda(
			@RequestParam double min,
			@RequestParam double max) {
		return ResponseEntity.ok(usuarioService.obterPorRendaEntre(min, max));
	}
}