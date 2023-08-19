package br.com.grupo27.techchallenge02.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.grupo27.techchallenge02.application.dto.BebidaDTO;
import br.com.grupo27.techchallenge02.core.entities.model.Bebida;
import br.com.grupo27.techchallenge02.interfaceAdapters.interfaces.repository.BebidaRepositoryPort;
import br.com.grupo27.techchallenge02.interfaceAdapters.interfaces.usecase.BebidaService;

public class BebidaServiceImpl implements BebidaService {

    private final BebidaRepositoryPort bebidaRepository;

    public BebidaServiceImpl(BebidaRepositoryPort bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    @Override
    public BebidaDTO createBebida(BebidaDTO bebidaDTO) {
        Bebida bebida = bebidaDTO.toBebida();
        bebida = bebidaRepository.saveBebida(bebida);
        return bebida.toDTO();
    }

    @Override
    public BebidaDTO updateBebida(Long id, BebidaDTO bebidaDTO) {
        Bebida bebida = bebidaDTO.toBebida();
        bebida = bebidaRepository.updateBebida(id, bebida);
        return bebida != null ? bebida.toDTO() : null;
    }

    @Override
    public BebidaDTO getBebidaById(Long id) {
        Bebida bebida = bebidaRepository.findBebidaById(id);
        return bebida != null ? bebida.toDTO() : null;
    }

    @Override
    public boolean deleteBebida(Long id) {
        return bebidaRepository.deleteBebida(id);
    }

    @Override
    public List<BebidaDTO> getAllBebidas() {
        return bebidaRepository.listAllBebidas().stream()
                .map(Bebida::toDTO)
                .collect(Collectors.toList());
    }
}
