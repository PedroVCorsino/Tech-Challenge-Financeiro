package br.com.grupo27.techchallenge03.application.dto;

import java.math.BigDecimal;

import br.com.grupo27.techchallenge03.domain.enums.Categoria;

public record ProdutoDTO(Long id, String nome, String descricao, BigDecimal preco, Categoria categoria) {
   
}