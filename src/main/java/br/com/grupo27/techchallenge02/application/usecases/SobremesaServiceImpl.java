package br.com.grupo27.techchallenge02.application.usecases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.grupo27.techchallenge02.application.dto.SobremesaDTO;
import br.com.grupo27.techchallenge02.core.entities.model.Sobremesa;
import br.com.grupo27.techchallenge02.interfaceAdapters.interfaces.repository.SobremesaRepositoryPort;
import br.com.grupo27.techchallenge02.interfaceAdapters.interfaces.usecase.SobremesaService;

public class SobremesaServiceImpl implements SobremesaService {

    private final SobremesaRepositoryPort sobremesaRepository;

    public SobremesaServiceImpl(SobremesaRepositoryPort sobremesaRepository) {
        this.sobremesaRepository = sobremesaRepository;
    }

    @Override
    public SobremesaDTO createSobremesa(SobremesaDTO sobremesaDTO) {
        Sobremesa sobremesa = sobremesaDTO.toSobremesa();
        sobremesa = sobremesaRepository.saveSobremesa(sobremesa);
        return sobremesa.toDTO();
    }

    @Override
    public SobremesaDTO updateSobremesa(Long id, SobremesaDTO sobremesaDTO) {
        Sobremesa sobremesa = sobremesaDTO.toSobremesa();
        sobremesa = sobremesaRepository.updateSobremesa(id, sobremesa);
        return sobremesa != null ? sobremesa.toDTO() : null;
    }

    @Override
    public SobremesaDTO getSobremesaById(Long id) {
        Sobremesa sobremesa = sobremesaRepository.findSobremesaById(id);
        return sobremesa != null ? sobremesa.toDTO() : null;
    }

    @Override
    public boolean deleteSobremesa(Long id) {
        return sobremesaRepository.deleteSobremesa(id);
    }

    @Override
    public List<SobremesaDTO> getAllSobremesas() {
        return sobremesaRepository.listAllSobremesas().stream()
                .map(Sobremesa::toDTO)
                .collect(Collectors.toList());
    }
}
