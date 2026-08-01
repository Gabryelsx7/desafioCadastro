package com.example.desafioCadastro.service.exception;

import java.util.UUID;

public class PetNaoEncontradoException extends RuntimeException{
    public PetNaoEncontradoException(UUID id){
        super("Pet não encontrado com id: " +id );
    }
}
