package br.com.grupo27.techchallenge02.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.grupo27.techchallenge02.application.dto.LancheDTO;
import br.com.grupo27.techchallenge02.core.entities.model.Lanche;
import br.com.grupo27.techchallenge02.interfaceAdapters.interfaces.repository.LancheRepositoryPort;
import br.com.grupo27.techchallenge02.interfaceAdapters.interfaces.usecase.LancheService;

public class LancheServiceImpl implements LancheService {

    private final LancheRepositoryPort lancheRepository;

    public LancheServiceImpl(LancheRepositoryPort lancheRepository) {
        this.lancheRepository = lancheRepository;
    }

    @Override
    public LancheDTO createLanche(LancheDTO lancheDTO) {
        Lanche lanche = lancheDTO.toLanche();
        lanche = lancheRepository.saveLanche(lanche);
        return lanche.toDTO();
    }

    @Override
    public LancheDTO updateLanche(Long id, LancheDTO lancheDTO) {
        Lanche lanche = lancheDTO.toLanche();
        lanche = lancheRepository.updateLanche(id, lanche);
        return lanche != null ? lanche.toDTO() : null;
    }

    @Override
    public LancheDTO getLancheById(Long id) {
        Lanche lanche = lancheRepository.findLancheById(id);
        return lanche != null ? lanche.toDTO() : null;
    }

    @Override
    public boolean deleteLanche(Long id) {
        return lancheRepository.deleteLanche(id);
    }

    @Override
    public List<LancheDTO> getAllLanches() {
        return lancheRepository.listAllLanches().stream()
                .map(Lanche::toDTO)
                .collect(Collectors.toList());
    }
}