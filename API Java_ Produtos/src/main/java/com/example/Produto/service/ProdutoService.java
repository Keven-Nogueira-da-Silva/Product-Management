package com.example.Produto.service;

import com.example.Produto.dto.ProdutoRequestDTO;
import com.example.Produto.dto.ProdutoResponseDTO;
import com.example.Produto.exeception.ResourceNotFoundExecption;
import com.example.Produto.model.Produtos;
import com.example.Produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoResponseDTO> listUsers () {
        return produtoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
    @Transactional
    public ProdutoResponseDTO toCreate (ProdutoRequestDTO dto) {
        Produtos produtos = new Produtos();

        produtos.setNome(dto.getNome());
        produtos.setCategoria(dto.getCategoria());
        produtos.setPreco(dto.getPreco());
        produtos.setEstoque(dto.getEstoque());

        Produtos salvo = produtoRepository.save(produtos);
        return toResponseDTO(salvo);
    }

    public ProdutoResponseDTO searchById (Long id) {
        Produtos produtos = produtoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExecption("Usuário com ID X não encontrado."));
        return toResponseDTO(produtos);
    }
    @Transactional
    public ProdutoResponseDTO updateProduct (Long id, ProdutoRequestDTO dto) {
        Produtos produtos = produtoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExecption("Não foi possível atualizar: usuário com ID X não existe."));
        produtos.setNome(dto.getNome());
        produtos.setEstoque(dto.getEstoque());
        produtos.setPreco(dto.getPreco());
        produtos.setCategoria(dto.getCategoria());
        return toResponseDTO(produtoRepository.save(produtos));
    }

    @Transactional
    public void delete(Long id) {
        Produtos produtos = produtoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundExecption("Não foi possível deletar: usuário com ID X não existe."));

        produtoRepository.delete(produtos);
    }

    private ProdutoResponseDTO toResponseDTO (Produtos produtos) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();

        dto.setId(produtos.getId());
        dto.setNome(produtos.getNome());
        dto.setCategoria(produtos.getCategoria());
        dto.setEstoque(produtos.getEstoque());
        dto.setPreco(produtos.getPreco());
        return dto;
    }
}
