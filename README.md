# Feature 3: Modularização e Design de APIs com DTOs

## Descrição da Feature
Esta feature demonstra a aplicação de conceitos de **arquitetura multi-módulos** e o uso estratégico de **Data Transfer Objects (DTOs)** em uma aplicação Spring Boot. O objetivo é estruturar a aplicação de forma modular, proteger os modelos de domínio e projetar APIs flexíveis e orientadas ao cliente.

A aplicação foi dividida em três módulos principais:

1. **common-domain**
2. **external-api-client**
3. **main-application**

---

## Estrutura de Módulos

### 1. `common-domain`
- Contém as entidades de domínio e enums compartilhados entre módulos.
- Objetivo: fornecer um "vocabulário comum" do sistema.
- Exemplo de classes:
    - `model/Usuario`
    - `model/Conta`
    - `enums/TipoConta`
    - `enums/Instituicao`

### 2. `external-api-client`
- Responsável pela comunicação com APIs externas.
- Contém **Feign Clients** service e DTOs específicos das respostas externas.
- Exemplo:
    - `client/CambioClient` → consulta saldo em USD e EUR
    - `dto/CambioApiResponseDTO` → DTO com cotações de moedas externas
    - `dto/CambioQueryResultDTO` → DTO que retorna o resultado da conversão de moedas
    - `dto/CotacaoDTO` → DTO com cotações de moedas externas
    - `service/CambioService` → Regra de negocio para converter o saldo em USD e EUR 

### 3. `main-application`
- Contém a lógica de negócio principal, repositórios e controladores REST.
- Contém DTOs de **Request** e **Response** da própria API.
- Mapeamento:
    - `RequestDTO` → Entidade (`Usuario`, `Conta`)
    - Entidade → `ResponseDTO` → Cliente
- Loaders de dados de teste:
    - `UsuarioLoader` → carrega usuários do arquivo `usuario.txt`
    - `ContaLoader` → carrega contas do arquivo `conta.txt` e enriquece saldo via API externa

---

## DTOs

### DTOs de Request
- Usados para receber dados das requisições HTTP (`POST`, `PUT`)
- Contêm validações de entrada (`@NotBlank`, `@Email`, etc.)
- Exemplo: `UsuarioRequestDTO`, `ContaRequestDTO`

### DTOs de Response
- Usados para enviar dados de volta ao cliente (`GET`, `POST`, `PUT`)
- Expondo apenas dados relevantes, incluindo informações enriquecidas por APIs externas
- Exemplo: `UsuarioResponseDTO`, `ContaResponseDTO` (com saldo convertido em USD e EUR)

---

## Loaders
- Implementados usando `ApplicationRunner` e anotação `@Component` com `@Order`.
- Função:
    - Ler arquivos de teste (`usuario.txt`, `conta.txt`)
    - Criar entidades de domínio
    - Chamar o **Service** para persistir e gerar ResponseDTO
Service.incluir(genericMapper.map(usuarioRequestDTO, Usuario.class));


## Subinndo a aplicação
#### No diretório raiz do projeto abra o terminal e rode o comando abaixo:
- `mvn clean install`

#### para startar apenas va até o diretoriro do projeto(main-application)
- `cd main-application`
#### rode o comando abaixo:
- `mvn spring-boot:run`

