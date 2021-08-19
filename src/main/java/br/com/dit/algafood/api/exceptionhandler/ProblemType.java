package br.com.dit.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	 
	ENTIDADE_EM_USO          ("/entidade-em-uso", "Entidade em uso"),
	IDENTIFICADOR_VAZIO      ("/identificador-vazio", "Identificador vazio"),
	ENTIDADE_VAZIA           ("/entidade-vazia", "Entidade vazia"),
	PARAMETRO_INVALIDO       ("/parametro-invalido", "Parâmetro inválido"),
	DADOS_INVALIDOS          ("/dados-invalidos", "Dados inválidos"),
	ERRO_DE_SISTEMA          ("/erro-sistema", "Erro de sistema"),
	RECURSO_NAO_ENCONTRADO   ("/recurso-nao-encontrado", "Recurso não encontrado"),
	PROPRIEDADE_NAO_PERMITIDA("/propriedade-nao-permitida", "Propriedade não permitida"),
	
	CORPO_DA_MENSAGEM_INCOMPREENSIVEL("/corpo-da-mensagem-incompreensivel", "Corpo da mesagem incompreensível");
	
	 
	private String title;
	private String uri;
	
	private ProblemType(String path, String title ) {
		this.title = title;
		this.uri   = "https://algafood.com.br" + path;
	}

}
