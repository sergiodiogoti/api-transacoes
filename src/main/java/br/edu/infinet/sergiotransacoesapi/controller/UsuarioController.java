package br.edu.infinet.sergiotransacoesapi.controller;

import br.edu.infinet.sergiotransacoesapi.model.domain.Usuario;
import br.edu.infinet.sergiotransacoesapi.model.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> incluir(@Valid @RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.incluir(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
	}
		
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> alterar(@PathVariable Integer id, @Valid @RequestBody Usuario usuario) {
		usuarioService.alterar(id, usuario);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		usuarioService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}/inativar")
	public ResponseEntity<Usuario> inativar(@PathVariable Integer id) {
		usuarioService.inativar(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> obterLista(){
		List<Usuario> lista = usuarioService.obterLista();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> obterPorId(@PathVariable Integer id) {
		Usuario usuario = usuarioService.obterPorId(id);
		return ResponseEntity.ok(usuario);
	}

	@GetMapping(value = "/cpf/{cpf}")
	public ResponseEntity<Usuario> obterPorCpf(@PathVariable String cpf) {
		Usuario usuario = usuarioService.obterPorCpf(cpf);
		return ResponseEntity.ok(usuario);
	}

	@GetMapping("/perfil/{perfil}")
	public ResponseEntity<List<Usuario>> obterPorPerfil(@PathVariable String perfil) {
		return ResponseEntity.ok(usuarioService.obterPorPerfil(perfil));
	}

	@GetMapping("/renda")
	public ResponseEntity<List<Usuario>> obterPorRenda(
			@RequestParam double min,
			@RequestParam double max) {
		return ResponseEntity.ok(usuarioService.obterPorRendaEntre(min, max));
	}
}