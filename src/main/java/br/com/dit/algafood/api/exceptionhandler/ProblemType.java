package br.com.dit.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	 
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	IDENTIFICADOR_VAZIO("/identificador-vazio", "Identificador vazio"),
	ENTIDADE_VAZIA("/entidade-vazia", "Entidade vazia"),
	PROPRIEDADE_NAO_PERMITIDA("/propriedade-nao-permitida", "Propriedade não permitida"),
	CORPO_DA_MENSAGEM_INCOMPREENSIVEL("/corpo-da-mensagem-incompreensivel", "Corpo da mesagem incompreensível");
	
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title ) {
		this.title = title;
		this.uri   = "https://algafood.com.br" + path;
	}

}
