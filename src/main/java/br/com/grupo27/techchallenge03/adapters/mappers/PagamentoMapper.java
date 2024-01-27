package br.com.grupo27.techchallenge03.adapters.mappers;

import org.springframework.stereotype.Component;

import br.com.grupo27.techchallenge03.application.dto.PagamentoDTO;
import br.com.grupo27.techchallenge03.domain.model.Pagamento;
import br.com.grupo27.techchallenge03.external.infrastructure.dataBaseEntities.PagamentoEntity;

@Component
public class PagamentoMapper {
        public PagamentoDTO domainToDto(Pagamento pagamento){
                return new PagamentoDTO(pagamento.getId()
                        ,pagamento.getIdCobranca()
                        ,pagamento.getIdtx()
                        ,pagamento.getIdPedido()
                        ,pagamento.getPago()
                        ,pagamento.getCliente()
                        ,pagamento.getValor());
        }

        public PagamentoEntity domainToEntity(Pagamento pagamento) {
                return new PagamentoEntity(pagamento.getId()
                        ,pagamento.getIdCobranca()
                        ,pagamento.getIdtx()
                        ,pagamento.getIdPedido()
                        ,pagamento.getPago()
                        ,pagamento.getCliente()
                        ,pagamento.getValor());
        }

        public Pagamento entityToDomain(PagamentoEntity pagamentoEntity) {
                return new Pagamento(pagamentoEntity.getId()
                        ,pagamentoEntity.getIdCobranca()
                        ,pagamentoEntity.getIdtx()
                        ,pagamentoEntity.getIdPedido()
                        ,pagamentoEntity.getPago()
                        ,pagamentoEntity.getCliente()
                        ,pagamentoEntity.getValor());
        }
    
    public Pagamento dtoToDomain(PagamentoDTO pagamentoDTO) {
        return new Pagamento(pagamentoDTO.id()
                ,pagamentoDTO.idCobranca()
                ,pagamentoDTO.idtx()
                ,pagamentoDTO.idPedido()
                ,pagamentoDTO.pago()
                ,pagamentoDTO.cliente()
                ,pagamentoDTO.valor());
    }

}
