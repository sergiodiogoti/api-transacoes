package br.edu.infinet.sergiotransacoesapi.model.util;

public class ConstanteUtil {

    //usuario
    public final static String MSG_NENHUM_USUARIO_ENCONTRADO = "Nenhum usuário encontrado.";
    public final static String MSG_NENHUM_USUARIO_ENCONTRADO_PARA_ID_INFORMADO = "Nenhum usuario encontrado para o ID informado.";
    public final static String MSG_NENHUM_USUARIO_ENCONTRADO_PARA_CPF_INFORMADO = "Nenhum usuario encontrado para o CPF informado.";
    public final static String MSG_NENHUM_USUARIO_ENCONTRADO_PARA_O_PERFIL = "Nenhum usuario encontrado para o Perfil informado.";
    public final static String MSG_NENHUM_USUARIO_ENCONTRADO_PARA_A_RENDA = "Nenhum usuario encontrado para Renda informada.";



    //conta
    public final static String MSG_NENHUMA_CONTA_ENCONTRADA = "Nenhuma conta encontrada.";
    public final static String MSG_NENHUMA_CONTA_ENCONTRADA_PARA_ID_INFORMADO = "Nenhuma conta encontrada para o ID informado!";
    public final static String MSG_NENHUMA_CONTA_ENCONTRADA_PARA_CPF_E_TIPO_INFORMADO = "Nenhuma conta encontrada para o CPF e Tipo informado!";
    public final static String MSG_NAO_HA_CONTAS_COM_SALDO_MAIOR_QUE_O_VALOR_INFORMADO = "Usuario Não tem conta com saldo maior que o valor informado!";



    //Erros
    public final static String ERRO_INTERNO_SERVIDOR = "Erro Interno no Servidor";
    public final static String ERRO_CONFLITO_BANCO_DE_DADOS = "Violação de Integridade de Dados";
    public final static String ERRO_RECURSO_NAO_ENCONTRADO = "Recurso Não Encontrado";
    public final static String ERRO_DADOS_INVALIDOS = "Dados Inválidos";
    public final static String ERRO_REQUISICAO_INVALIDA = "Requisição inválida";
    public final static String ERRO_EXCLUSAO_ID_INVALIDO = "O ID para exclusão não pode ser nulo/zero!";
    public final static String ERRO_BUSCA_ID_INVALIDO = "O ID para busca não pode ser nulo/zero!";
    public final static String ERRO_BUSCA_CPF_INVALIDO = "O CPF para busca não pode ser nulo/zero!";
    public final static String ERRO_ALTERACAO_ID_INVALIDO = "O ID para alteração não pode ser nulo/zero!";
    public final static String ERRO_INATIVACAO_ID_INVALIDO = "O ID para inativação não pode ser nulo/zero!";
    public final static String ERRO_ID_REGISTRO_NOVO = "Ao incluir um novo registro o campo ID não pode ser preenchido";
    public final static String ERRO_USUARIO_JA_EXISTE = "Já existe um usuario cadastrado com o CPF informado! ";
    public final static String ERRO_CONTA_NOVA_SALDO_NEGATIVO = "Não é possivel cadastrar conta nova com saldo negativo!";
    public final static String ERRO_ID_MARCAR_CONTA_PRINCIPAL = "O ID para marcar a conta como principal não pode ser nulo/zero!";
    public final static String ERRO_RENDA_INVALIDA = "Renda Invalida";


}
