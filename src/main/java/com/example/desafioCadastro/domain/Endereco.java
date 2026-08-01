package com.example.desafioCadastro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable // Indica que esta classe pode ser "embutida" em outra
@Getter
@Setter
@NoArgsConstructor // Cria um construtor sem argumentos
@AllArgsConstructor // Construtor com todos os atributos
public class Endereco {

    public static final String NAOINFORMADO = "NAO_INFORMADO";

    @Column(name = "numero_casa")
    private String numerodaCasa = NAOINFORMADO;

    @Column(name = "cidade")
    private String cidade= NAOINFORMADO;

    @Column(name = "rua")
    private String rua = NAOINFORMADO;
}
