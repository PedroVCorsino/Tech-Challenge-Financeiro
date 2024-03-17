package br.com.grupo27.techchallenge03.application.usecases;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.grupo27.techchallenge03.adapters.gateways.PagamentoGateway;
import br.com.grupo27.techchallenge03.adapters.mappers.PagamentoMapper;
import br.com.grupo27.techchallenge03.application.dto.CobrancaDTO;
import br.com.grupo27.techchallenge03.application.dto.PagamentoDTO;
import br.com.grupo27.techchallenge03.domain.interfaces.services.rabbitmq.PedidoAsyncService;
import br.com.grupo27.techchallenge03.domain.interfaces.usecase.PagamentoUsecase;

import br.com.grupo27.techchallenge03.domain.interfaces.usecase.pix.PixUseCase;
import br.com.grupo27.techchallenge03.domain.model.Pagamento;


public class PagamentoUseCaseImpl implements PagamentoUsecase {

    private final PixUseCase pix;
    private final PagamentoGateway pagamentoGateway;
    private final PagamentoMapper pagamentoMapper;
    private final PedidoAsyncService pedidoAsyncService;

    private static final Logger logger = LoggerFactory.getLogger(PagamentoUseCaseImpl.class);

    public PagamentoUseCaseImpl(PixUseCase pix, PagamentoGateway pagamentoGateway, PagamentoMapper pagamentoMapper, PedidoAsyncService pedidoAsyncService) {
        this.pix = pix;
        this.pagamentoGateway = pagamentoGateway;
        this.pagamentoMapper = pagamentoMapper;
        this.pedidoAsyncService = pedidoAsyncService;
    }

    @Override
    public Boolean consultaStatusPagamento(Long id) {
        PagamentoDTO pagamentoDTO = pagamentoMapper.domainToDto(pagamentoGateway.findPagamentoByIdPedido(id));
        return pix.consultaStatusPagamento(pagamentoDTO.idtx());
    }

    @Override
    public Boolean verificaStatusPagamento(Long id) {
        
        
        //*  O codigo abaixo seria p processamento normal ao verificar que o pedido foi pago
        //* Vou forçar para que ele sempre retorne true para evitar que eu tenha que pagar uma pedido sempre que estiver testando.

        return true; //mock
        // PagamentoDTO pagamentoDTO = pagamentoMapper.domainToDto(pagamentoGateway.findPagamentoByIdPedido(id)); 
        // if (pagamentoDTO.pago()) {
        //     return true; 
        // }
    
        // boolean isPago = consultaStatusPagamento(pagamentoDTO.idPedido());


        // if (isPago) {
        //     Pagamento pagamento = pagamentoMapper.dtoToDomain(pagamentoDTO);
        //     pagamento.setPago(isPago);
        //     pagamentoGateway.updatePagamento(pagamento); 
        //     pedidoAsyncService.enviarParafilaDePedidosPagos(pagamentoDTO);
        //     return true;
        // } else {
        //     return false;
        // }

    }

    @Override
    public String geraQrCodePedido(Long id) {
        PagamentoDTO pagamentoDTO = pagamentoMapper.domainToDto(pagamentoGateway.findPagamentoByIdPedido(id));

        if (pagamentoDTO == null) {
            throw new RuntimeException("Pedido não encontrado");
        }

        String qrcode = pix.gerarQrCode(pagamentoDTO.idCobranca());

        if (qrcode != null) {
            return qrcode;
        }
        //? Forçando retorno fictício devido a problemas para conectar com a EFI
        return "Codigo de pagamento fictício";
    }

    @Override
    public Pagamento gerarCobranca(CobrancaDTO cobrancaDTO) {
        HashMap<String, String> cobrancaData = pix.gerarCobranca(cobrancaDTO);
        String idCobranca = cobrancaData.get("idCobranca");
        String idTx = cobrancaData.get("txid");
    
        if (idCobranca == null || idTx == null) {
            logger.error("Falha ao gerar a cobrança. Dados de cobrança ausentes. {}  {}" , idCobranca, idTx);
            idCobranca = "00001"; // Mock id ficticio
            idTx = "00001"; // Mock id ficticio   
        }

        Pagamento pagamento = new Pagamento(
            null,
            idCobranca,
            idTx,
            cobrancaDTO.idPedido(),
            false,
            cobrancaDTO.cliente(),
            cobrancaDTO.valor()

        );

        return pagamentoGateway.savePagamento(pagamento);
    }
    

}
