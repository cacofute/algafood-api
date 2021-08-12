package br.com.dit.algafood.infrastructure.repositorie;

import br.com.dit.algafood.domain.repositories.CozinhaRepository;
import br.com.dit.algafood.domain.repositories.CozinhaRepositoryQuery;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepositoryQuery {

    private CozinhaRepository cozinhaRepository;

    @Lazy
    public CozinhaRepositoryImpl(CozinhaRepository cozinhaRepository) {
        this.cozinhaRepository = cozinhaRepository;
    }


}
