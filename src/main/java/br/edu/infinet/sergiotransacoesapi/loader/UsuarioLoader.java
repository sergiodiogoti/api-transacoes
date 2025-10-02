package br.edu.infinet.sergiotransacoesapi.loader;

import br.edu.infinet.sergiotransacoesapi.model.domain.Endereco;
import br.edu.infinet.sergiotransacoesapi.model.domain.Usuario;
import br.edu.infinet.sergiotransacoesapi.model.service.UsuarioService;
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

		try (BufferedReader leitura = new BufferedReader(new FileReader("dataFile/usuario.txt"))) {
			String linha;
			int linhaNum = 1;

			while ((linha = leitura.readLine()) != null) {
				String[] campos = linha.split(";");
				Usuario usuario = new Usuario();
				try {
					Endereco endereco = new Endereco();
					endereco.setCep(campos[8]);
					endereco.setLogradouro(campos[9]);
					endereco.setBairro(campos[10]);
					endereco.setEstado(campos[11]);
					endereco.setUf(campos[12]);
					endereco.setLocalidade(campos[13]);

					usuario.setNome(campos[0]);
					usuario.setPontuacaoCredito(Integer.parseInt(campos[1]));
					usuario.setRendaMensal(Double.parseDouble(campos[2]));
					usuario.setAtivo(Boolean.parseBoolean(campos[3]));
					usuario.setCpf(campos[4]);
					usuario.setEmail(campos[5]);
					usuario.setTelefone(campos[6]);
					usuario.setPerfil(campos[7]);
					usuario.setEndereco(endereco);

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
