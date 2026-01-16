package com.example.Produto.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoRequestDTO {

    @NotBlank
    @Size(min = 1, max = 100)
    private String nome;

    @NotNull
    private Double preco;

    @NotBlank
    private String categoria;

    @NotNull
    @Min(0)
    private Integer estoque;
}
