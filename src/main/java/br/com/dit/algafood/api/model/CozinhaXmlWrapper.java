package br.com.dit.algafood.api.model;

import java.util.List;

import br.com.dit.algafood.domain.model.Cozinha;
import lombok.Data;


@Data
public class CozinhaXmlWrapper {
	
	private List<Cozinha> cozinhas;

}
