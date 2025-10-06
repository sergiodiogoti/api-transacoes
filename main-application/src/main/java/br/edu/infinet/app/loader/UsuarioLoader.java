package br.edu.infinet.app.loader;

import br.edu.infinet.app.service.UsuarioService;
import br.edu.infinet.app.domain.model.Usuario;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Order(1)
@Component
public class UsuarioLoader implements ApplicationRunner {

	private final UsuarioService usuarioService;

	public UsuarioLoader(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("### - Carregando [UsuarioLoader]");

		try (BufferedReader leitura = new BufferedReader(new FileReader("usuario.txt"))) {
			String linha;
			int linhaNum = 1;

			while ((linha = leitura.readLine()) != null) {
				String[] campos = linha.split(";");
				Usuario usuario = new Usuario();
				try {
					usuario.setNome(campos[0]);
					usuario.setEmail(campos[1]);
					usuario.setCpf(campos[2]);
					usuario.setTelefone(campos[3]);
					usuario.setRendaMensal(Double.parseDouble(campos[4]));
					usuario.setAtivo(Boolean.parseBoolean(campos[5]));
					usuario.setSenha(campos[6]);

					usuarioService.incluir(usuario);
					System.out.println("  [OK] Usuario " + usuario.getNome() + " incluído com sucesso.");

				} catch (Exception e) {
					System.err.println("  [ERRO] Linha " + linhaNum + " - Problema na inclusão do usuario "
							+ usuario.getNome() + ": " + e.getMessage());
				}
				linhaNum++;
			}

		} catch (Exception e) {
			System.err.println("  [ERRO] Problema ao abrir o arquivo de usuários: " + e.getMessage());
		}

		System.out.println("### - [UsuarioLoader] carregado com sucesso...");
		usuarioService.obterLista().forEach(System.out::println);
		System.out.println("-----------------------------");
	}
}
