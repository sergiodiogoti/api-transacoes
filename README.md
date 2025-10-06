# 🧩 Feature 4: Autenticação e Autorização com Spring Security

Este documento detalha a implementação da **segurança na API RESTful**, utilizando o **Spring Security** para aplicar os pilares de **autenticação e autorização**, seguindo os princípios de **Menor Privilégio** e **Segurança por Padrão**.

---

## 1. ⚙️ Configuração e Escolhas Técnicas

| Item | Implementação | Justificativa |
|------|----------------|----------------|
| **Autenticação** | **HTTP Basic** | Escolhido conforme requisito da feature. É simples e eficaz para aplicações RESTful *stateless* (sem sessões). |
| **Autorização** | **Baseada em Roles (`ADMIN`, `USER`) via URL/Request Matching** | Controle centralizado na classe `SecurityConfig`, garantindo separação de responsabilidades e evitando lógica de permissão nos Controllers. |
| **Usuários** | **Gerenciamento em memória (InMemoryUserDetailsManager)** | Implementação rápida para validação de conceitos. As senhas são codificadas com `BCryptPasswordEncoder`, garantindo segurança mínima adequada. |
| **Princípios** | **Menor Privilégio e Segurança por Padrão** | O usuário `USER` possui apenas permissão de leitura (GET), enquanto `ADMIN` possui permissão total. A regra final `anyRequest().authenticated()` garante que todas as rotas exigem autenticação. |

---

## 2. 🔒 Regras de Autorização Aplicadas

As regras foram configuradas para segregar permissões, garantindo que o **ADMIN** possa gerenciar recursos e o **USER** apenas consultá-los.

| Contexto | Método | Permissão | Requisito Atendido |
|-----------|----------|-------------|----------------------|
| `/api/usuarios/**` | `GET` | `ADMIN` ou `USER` | Leitura (consulta) permitida a todos os usuários autenticados. |
| `/api/usuarios/**` | `POST`, `PUT`, `DELETE` | Apenas `ADMIN` | Criação, alteração e exclusão restritas a administradores. |
| `/api/contas/**` | `GET` | `ADMIN` ou `USER` | Consulta de contas liberada a todos os usuários autenticados. |
| `/api/contas/**` | `POST`, `PUT`, `DELETE` | Apenas `ADMIN` | Operações de modificação/exclusão restritas a administradores. |
| `*` (todas as demais) | Qualquer método | Autenticação obrigatória | Implementa a **Segurança por Padrão**, protegendo todo o sistema. |

---

## 3. 🧪 Evidências dos Testes (Validação da Segurança)

Abaixo estão os principais **cenários de teste realizados via Postman e cURL**, que comprovam a correta aplicação da autenticação e autorização.

---

### 🧱 Cenário A — Falha na Autenticação (`401 Unauthorized`)

| Teste | Objetivo | Resultado | Evidência                              |
|--------|-----------|------------|----------------------------------------|
| **Credenciais Ausentes** | Validar a *Segurança por Padrão* — acesso sem autenticação. | `401 Unauthorized` por falta de credenciais. | `prints/nao_authorizado.png`             |
| **Credenciais Inválidas** | Validar o funcionamento do HTTP Basic. | `401 Unauthorized` devido à senha incorreta. | `prints/password_basic_auth_invalida.png` |

📘 **Conclusão:**  
O sistema exige autenticação válida para qualquer acesso, confirmando a aplicação correta de `anyRequest().authenticated()`.

---

### ✅ Cenário B — Sucesso na Autorização (`200 OK` / `204 No Content`)

| Teste | Usuário | Requisição | Resultado | Evidência                               |
|--------|----------|-------------|-------------|-----------------------------------------|
| **Leitura de Usuários** | `admin/admin123` | `GET /api/usuarios` | `200 OK` — Dados retornados com sucesso. | `prints/authorizado.png`                  |
| **Exclusão de Conta** | `admin/admin123` | `DELETE /api/contas/1` | `204 No Content` — Exclusão bem-sucedida. | `prints/admin_pode_excluir_registros.png` |

📘 **Conclusão:**  
O usuário **ADMIN** possui permissão total sobre os recursos, conforme regra `hasRole("ADMIN")`.

---

### 🚫 Cenário C — Falha na Autorização (`403 Forbidden`)

| Teste | Usuário | Requisição | Resultado | Evidência                                  |
|--------|----------|-------------|-------------|--------------------------------------------|
| **Tentativa de Exclusão de Conta** | `user/user123` | `DELETE /api/contas/1` | `403 Forbidden` — Operação não permitida. | `prints/user_nao_pode_excluir_registros.png` |

📘 **Conclusão:**  
O usuário **USER** foi autenticado com sucesso, mas suas **roles não permitem** a operação de exclusão.  
Isso valida o **Princípio do Menor Privilégio**, garantindo que apenas usuários com a role `ADMIN` possam realizar ações destrutivas.

---

## 4. 🧾 Conclusão Geral

- O sistema foi protegido com autenticação **HTTP Basic**, simples e eficaz para APIs RESTful.
- As **roles ADMIN e USER** foram corretamente segregadas e aplicadas via *URL Matching*.
- O **princípio da Segurança por Padrão** foi respeitado — todas as requisições exigem autenticação.
- O projeto mantém **separação de responsabilidades** entre controladores, serviços e configuração de segurança.
- Todos os testes práticos (Postman e cURL) comprovaram o funcionamento da camada de segurança implementada.

---


## ⚙️ 4. Como Executar a Aplicação

1. **No terminal, Compile e instale os módulos**:
   ```bash
   mvn clean install
   
2. **acesse o diretorio**:
    ```bash
    cd main-application

3. **rode o comando**:
    ```bash
    mvn spring-boot:run

## ⚙️ Testar os Endpoins
-Na raiz do projeto tem uma collection **sergio-antonio-api.postman_collection.json**, importe no postman/insomnia

