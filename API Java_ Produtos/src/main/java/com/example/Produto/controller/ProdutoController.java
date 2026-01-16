package com.example.Produto.controller;

import com.example.Produto.dto.ProdutoRequestDTO;
import com.example.Produto.dto.ProdutoResponseDTO;
import com.example.Produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoResponseDTO> listUsers () {
        return produtoService.listUsers();
    }

    @PostMapping
    public ProdutoResponseDTO toCreate(@RequestBody @Valid ProdutoRequestDTO dto) {
        return produtoService.toCreate(dto);
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO searchById (@PathVariable Long id) {
        return produtoService.searchById(id);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO updateProduct(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoRequestDTO dto) {
        return produtoService.updateProduct(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id) {
        produtoService.delete(id);
    }




}
