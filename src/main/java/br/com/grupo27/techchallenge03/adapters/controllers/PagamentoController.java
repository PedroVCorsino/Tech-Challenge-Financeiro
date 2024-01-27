package br.com.grupo27.techchallenge03.adapters.controllers;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.grupo27.techchallenge03.application.dto.CobrancaDTO;
import br.com.grupo27.techchallenge03.application.dto.PagamentoDTO;
import br.com.grupo27.techchallenge03.domain.interfaces.usecase.PagamentoUsecase;

@Controller
public class PagamentoController {

    private final PagamentoUsecase pagamentoUsecase;

    public PagamentoController(PagamentoUsecase pagamentoUsecase) {
        this.pagamentoUsecase = pagamentoUsecase;
    }

    public ResponseEntity<?> geraCobranca(CobrancaDTO cobrancaDTO) {
        try {
            return ResponseEntity.ok(pagamentoUsecase.gerarCobranca(cobrancaDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
        
    }

    public ResponseEntity<Boolean> verificaPagamento(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pagamentoUsecase.verificaStatusPagamento(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    // public ResponseEntity<List<PedidoDTO>> getPedidosByStatusPagamento(@RequestParam boolean pago) {
    //     try {
    //         List<PedidoDTO> pedidos = pagamentoUsecase.findPedidosByStatusPagamento(pago);
    //         if (pedidos != null && !pedidos.isEmpty()) {
    //             return ResponseEntity.ok(pedidos);
    //         } else {
    //             return ResponseEntity.notFound().build();
    //         }
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     }
    // }

    public ResponseEntity<String> geraQrCodePedido(@PathVariable Long id) {
        try {
            String qrCode = pagamentoUsecase.geraQrCodePedido(id);
            if( qrCode != null){
                return ResponseEntity.ok(qrCode);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
