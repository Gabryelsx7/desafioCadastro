package com.example.desafioCadastro.controller.dto;

import com.example.desafioCadastro.domain.Pet;
import com.example.desafioCadastro.domain.enums.SexoPet;
import com.example.desafioCadastro.domain.enums.TipoPet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PetResponseDto(
        UUID id,
        String name,
        String sobrenome,
        TipoPet tipoPet,
        SexoPet sexoPet,
        String numeroCasa,
        String cidade,
        String rua,
        BigDecimal idade,
        BigDecimal peso,
        String raca,
        LocalDateTime dataCadastro
) {
    public static PetResponseDto from(Pet pet) {
        return new PetResponseDto(
                pet.getId(),
                pet.getNome(),
                pet.getSobrenome(),
                pet.getTipoPet(),
                pet.getSexoPet(),
                pet.getEndereco().getNumerodaCasa(),
                pet.getEndereco().getCidade(),
                pet.getEndereco().getRua(),
                pet.getIdade(),
                pet.getPeso(),
                pet.getRaca(),
                pet.getDataCadastro()
                );
    }
}

