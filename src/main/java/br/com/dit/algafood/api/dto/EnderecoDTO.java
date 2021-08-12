package br.com.dit.algafood.api.dto;

import br.com.dit.algafood.domain.model.Cidade;
import br.com.dit.algafood.domain.model.Endereco;
import lombok.Value;


@Value
public class EnderecoDTO {

    String    cep;
    String    logradouro;
    String    numero;
    String    complemento;
    String    bairro;
    CidadeDTO cidade;

    public EnderecoDTO(Endereco endereco) {
        cep         = endereco.getCep();
        logradouro  = endereco.getLogradouro();
        numero      = endereco.getNumero();
        complemento = endereco.getComplemento();
        bairro      = endereco.getBairro();
        cidade      = new CidadeDTO(endereco.getCidade());
    }
}
