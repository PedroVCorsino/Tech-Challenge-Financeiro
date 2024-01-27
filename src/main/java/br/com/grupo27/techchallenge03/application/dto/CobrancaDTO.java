package br.com.grupo27.techchallenge03.application.dto;


public record CobrancaDTO(
    Long idPedido,
    String cliente,
    String valor
) {
    
}
