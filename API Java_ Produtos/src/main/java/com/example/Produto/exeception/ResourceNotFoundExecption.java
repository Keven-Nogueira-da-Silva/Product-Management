package com.example.Produto.exeception;

public class ResourceNotFoundExecption extends RuntimeException {
    public ResourceNotFoundExecption(String message) {
        super(message);
    }
}
