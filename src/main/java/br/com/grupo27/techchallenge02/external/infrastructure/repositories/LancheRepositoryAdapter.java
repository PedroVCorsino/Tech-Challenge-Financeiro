package br.com.grupo27.techchallenge02.external.infrastructure.repositories;

import org.springframework.stereotype.Repository;

import br.com.grupo27.techchallenge02.application.config.mappers.produtos.LancheMapper;
import br.com.grupo27.techchallenge02.core.entities.model.Lanche;
import br.com.grupo27.techchallenge02.external.infrastructure.dataBaseEntities.LancheEntity;
import br.com.grupo27.techchallenge02.external.infrastructure.repositories.JPA.LancheJPA;
import br.com.grupo27.techchallenge02.interfaceAdapters.interfaces.repository.LancheRepositoryPort;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LancheRepositoryAdapter implements LancheRepositoryPort {

    private final LancheJPA lancheJPA;
    private final LancheMapper lancheMapper;

    public LancheRepositoryAdapter(LancheJPA lancheJPA, LancheMapper lancheMapper) {
        this.lancheJPA = lancheJPA;
        this.lancheMapper = lancheMapper;
    }

    @Override
    public Lanche saveLanche(Lanche lanche) {
        LancheEntity lancheEntity = lancheMapper.domainToEntity(lanche);
        lancheEntity = lancheJPA.save(lancheEntity);
        return lancheMapper.entityToDomain(lancheEntity);
    }

    @Override
    public Lanche updateLanche(Long id, Lanche lanche) {
        return lancheJPA.findById(id).map(lancheEntity -> {
            LancheEntity updatedLancheEntity = lancheMapper.domainToEntity(lanche);
            updatedLancheEntity.setId(lancheEntity.getId());
            updatedLancheEntity = lancheJPA.save(updatedLancheEntity);
            return lancheMapper.entityToDomain(updatedLancheEntity);
        }).orElse(null);
    }

    @Override
    public Lanche findLancheById(Long id) {
        return lancheJPA.findById(id)
                .map(lancheMapper::entityToDomain)
                .orElse(null);
    }

    @Override
    public boolean deleteLanche(Long id) {
        if (lancheJPA.existsById(id)) {
            lancheJPA.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Lanche> listAllLanches() {
        return lancheJPA.findAll().stream()
                .map(lancheMapper::entityToDomain)
                .collect(Collectors.toList());
    }
}
