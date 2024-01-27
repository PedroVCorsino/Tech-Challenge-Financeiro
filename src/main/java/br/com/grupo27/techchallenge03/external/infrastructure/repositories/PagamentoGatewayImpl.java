package br.com.grupo27.techchallenge03.external.infrastructure.repositories;

import java.util.Date;

import org.springframework.stereotype.Repository;

import br.com.grupo27.techchallenge03.adapters.gateways.PagamentoGateway;
import br.com.grupo27.techchallenge03.adapters.mappers.PagamentoMapper;
import br.com.grupo27.techchallenge03.application.dto.PagamentoDTO;
import br.com.grupo27.techchallenge03.domain.model.Pagamento;
import br.com.grupo27.techchallenge03.external.infrastructure.dataBaseEntities.PagamentoEntity;
import br.com.grupo27.techchallenge03.external.infrastructure.repositories.JPA.PagamentoJPA;

@Repository
public class PagamentoGatewayImpl implements  PagamentoGateway {

    private final PagamentoJPA pagamentoJpa;
    private final PagamentoMapper pagamentoMapper;
        
    
    public PagamentoGatewayImpl(PagamentoJPA pagamentoJpa, PagamentoMapper pagamentoMapper) {
        this.pagamentoJpa = pagamentoJpa;
        this.pagamentoMapper = pagamentoMapper;
    }

    public Pagamento savePagamento(Pagamento pagamento){
        try {
            PagamentoEntity pagamentoEntity = pagamentoMapper.domainToEntity(pagamento);
            PagamentoEntity savedPagamentoEntity = pagamentoJpa.save(pagamentoEntity);
            return pagamentoMapper.entityToDomain(savedPagamentoEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        
    }

    public Pagamento findPagamentoByIdPedido(Long id){
        return pagamentoMapper.entityToDomain(pagamentoJpa.findPagamentoByIdPedido(id));
    }

    @Override
    public Pagamento updatePagamento(Pagamento pagamento) {
        PagamentoEntity pagamentoEntity = pagamentoMapper.domainToEntity(pagamento);
        PagamentoEntity savedPagamentoEntity = pagamentoJpa.save(pagamentoEntity);
        return savePagamento(pagamentoMapper.entityToDomain(savedPagamentoEntity));
    }

}