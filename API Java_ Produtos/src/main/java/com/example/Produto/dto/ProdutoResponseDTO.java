package com.example.Produto.dto;

import lombok.Data;

@Data
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private Double preco;
    private String categoria;
    private Integer estoque;
}
