package com.example.desafioCadastro.controller.dto;

import com.example.desafioCadastro.domain.enums.SexoPet;
import com.example.desafioCadastro.domain.enums.TipoPet;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record PetRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Pattern(regexp = "^[A-Za-zÀ-ú ]+$", message = "Nome não pode conter números ou caracteres especiais")
        String nome,

        @Pattern(regexp = "^[A-Za-zÀ-ú ]*$", message = "Sobrenome não pode conter números ou caracteres especiais")
        String sobrenome,

        @NotNull(message = "Tipo é obrigatório (CACHORRO ou GATO)")
        TipoPet tipo,

        @NotNull(message = "Sexo é obrigatório (MACHO ou FEMEA)")
        SexoPet sexo,

        String numeroCasa,
        String cidade,
        String rua,

        @DecimalMin(value = "0.0", message = "Idade não pode ser negativa")
        @DecimalMax(value = "20.0", message = "Idade não pode ser maior que 20 anos")
        BigDecimal idade,

        @DecimalMin(value = "0.5", message = "Peso não pode ser menor que 0.5kg")
        @DecimalMax(value = "60.0", message = "Peso não pode ser maior que 60kg")
        BigDecimal peso,

        @Pattern(regexp = "^[A-Za-zÀ-ú ]*$", message = "Raça não pode conter números ou caracteres especiais")
        String raca
){}

