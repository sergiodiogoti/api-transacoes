package br.edu.infinet.sergiotransacoesapi.loader;

import br.edu.infinet.sergiotransacoesapi.model.domain.Conta;
import br.edu.infinet.sergiotransacoesapi.model.domain.Usuario;
import br.edu.infinet.sergiotransacoesapi.model.domain.enums.Instituicao;
import br.edu.infinet.sergiotransacoesapi.model.domain.enums.TipoConta;
import br.edu.infinet.sergiotransacoesapi.model.service.ContaService;
import br.edu.infinet.sergiotransacoesapi.model.service.UsuarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Order(2)
@Component
public class ContaLoader implements ApplicationRunner {

	private final UsuarioService usuarioService;
	private final ContaService contaService;

	public ContaLoader(UsuarioService usuarioService, ContaService contaService) {
		this.usuarioService = usuarioService;
		this.contaService = contaService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("### - Carregando [ContaLoader]...");

		try (BufferedReader leitura = new BufferedReader(new FileReader("dataFile/conta.txt"))) {
			String linha;
			int linhaNum = 1;

			while ((linha = leitura.readLine()) != null) {
				String[] campos = linha.split(";");
				Conta conta = new Conta();
				try {
					conta.setNome(campos[0]);
					conta.setTipo(TipoConta.valueOf(campos[1]));
					conta.setInstituicao(Instituicao.valueOf(campos[2]));
					conta.setSaldo(Double.parseDouble(campos[3]));
					conta.setPrincipal(Boolean.parseBoolean(campos[4]));

					Usuario usuario = usuarioService.obterPorCpf(campos[5]);
					conta.setUsuario(usuario);

					contaService.incluir(conta);
					System.out.println("  [OK] Conta " + conta.getNome() + " incluída com sucesso.");

				} catch (Exception e) {
					System.err.println("  [ERRO] Linha " + linhaNum + " - Problema na inclusão da Conta "
							+ conta.getNome() + ": " + e.getMessage());
				}
				linhaNum++;
			}

		} catch (Exception e) {
			System.err.println("  [ERRO] Problema ao abrir o arquivo de contas: " + e.getMessage());
		}

		System.out.println("### - [ContaLoader] carregado com sucesso...");
		contaService.obterLista().forEach(System.out::println);
		System.out.println("-----------------------------");
	}
}
