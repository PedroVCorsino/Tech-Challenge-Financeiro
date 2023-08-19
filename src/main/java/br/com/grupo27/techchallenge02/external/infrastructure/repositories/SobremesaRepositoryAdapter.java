package br.com.grupo27.techchallenge02.external.infrastructure.repositories;

import org.springframework.stereotype.Repository;

import br.com.grupo27.techchallenge02.core.entities.model.Sobremesa;
import br.com.grupo27.techchallenge02.external.infrastructure.dataBaseEntities.SobremesaEntity;
import br.com.grupo27.techchallenge02.external.infrastructure.repositories.JPA.SobremesaJPA;
import br.com.grupo27.techchallenge02.interfaceAdapters.interfaces.repository.SobremesaRepositoryPort;

import java.util.List;
import java.util.stream.Collectors;
@Repository
public class SobremesaRepositoryAdapter implements SobremesaRepositoryPort {

    private final SobremesaJPA sobremesaRepositoryJPA;

    public SobremesaRepositoryAdapter(SobremesaJPA sobremesaRepositoryJPA) {
        this.sobremesaRepositoryJPA = sobremesaRepositoryJPA;
    }

    @Override
    public Sobremesa saveSobremesa(Sobremesa sobremesa) {
        SobremesaEntity sobremesaEntity = sobremesa.toEntity();
        sobremesaEntity = sobremesaRepositoryJPA.save(sobremesaEntity);
        return sobremesaEntity.toSobremesa();
    }

    @Override
    public Sobremesa updateSobremesa(Long id, Sobremesa sobremesa) {
        return sobremesaRepositoryJPA.findById(id).map(sobremesaEntity -> {
            sobremesaEntity.setNome(sobremesa.getNome());
            sobremesaEntity.setDescricao(sobremesa.getDescricao());
            sobremesaEntity.setPreco(sobremesa.getPreco());
            sobremesaEntity = sobremesaRepositoryJPA.save(sobremesaEntity);
            return sobremesaEntity.toSobremesa();
        }).orElse(null);
    }

    @Override
    public Sobremesa findSobremesaById(Long id) {
        return sobremesaRepositoryJPA.findById(id).map(SobremesaEntity::toSobremesa).orElse(null);
    }

    @Override
    public boolean deleteSobremesa(Long id) {
        if (sobremesaRepositoryJPA.existsById(id)) {
            sobremesaRepositoryJPA.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Sobremesa> listAllSobremesas() {
        return sobremesaRepositoryJPA.findAll().stream()
                .map(SobremesaEntity::toSobremesa)
                .collect(Collectors.toList());
    }
}
