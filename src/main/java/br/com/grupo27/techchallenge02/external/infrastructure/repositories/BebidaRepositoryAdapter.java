package br.com.grupo27.techchallenge02.external.infrastructure.repositories;

import org.springframework.stereotype.Repository;

import br.com.grupo27.techchallenge02.core.entities.model.Bebida;
import br.com.grupo27.techchallenge02.external.infrastructure.dataBaseEntities.BebidaEntity;
import br.com.grupo27.techchallenge02.external.infrastructure.repositories.JPA.BebidaJPA;
import br.com.grupo27.techchallenge02.interfaceAdapters.interfaces.repository.BebidaRepositoryPort;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BebidaRepositoryAdapter implements BebidaRepositoryPort {

    private final BebidaJPA bebidaRepositoryJPA;

    public BebidaRepositoryAdapter(BebidaJPA bebidaRepositoryJPA) {
        this.bebidaRepositoryJPA = bebidaRepositoryJPA;
    }

    @Override
    public Bebida saveBebida(Bebida bebida) {
        BebidaEntity bebidaEntity = bebida.toEntity();
        bebidaEntity = bebidaRepositoryJPA.save(bebidaEntity);
        return bebidaEntity.toBebida();
    }

    @Override
    public Bebida updateBebida(Long id, Bebida bebida) {
        return bebidaRepositoryJPA.findById(id).map(bebidaEntity -> {
            bebidaEntity.setNome(bebida.getNome());
            bebidaEntity.setDescricao(bebida.getDescricao());
            bebidaEntity.setPreco(bebida.getPreco());
            bebidaEntity = bebidaRepositoryJPA.save(bebidaEntity);
            return bebidaEntity.toBebida();
        }).orElse(null);
    }

    @Override
    public Bebida findBebidaById(Long id) {
        return bebidaRepositoryJPA.findById(id).map(BebidaEntity::toBebida).orElse(null);
    }

    @Override
    public boolean deleteBebida(Long id) {
        if (bebidaRepositoryJPA.existsById(id)) {
            bebidaRepositoryJPA.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Bebida> listAllBebidas() {
        return bebidaRepositoryJPA.findAll().stream()
                .map(BebidaEntity::toBebida)
                .collect(Collectors.toList());
    }
}
