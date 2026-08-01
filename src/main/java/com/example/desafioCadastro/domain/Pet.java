package com.example.desafioCadastro.domain;


import com.example.desafioCadastro.domain.enums.SexoPet;
import com.example.desafioCadastro.domain.enums.TipoPet;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pet {

    public static final String NAO_INFORMADO = "NAO_INFORMADO";

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    private String sobrenome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false) //Se tentar salvar sem definir o tipo, o Java impede por conta do nullable = false.
    private TipoPet tipoPet;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private SexoPet sexoPet;

    @Embedded       // Maapeia os campos da classe Endereco como colunas nesta mesma tabela
    @Builder.Default // Garante que o Lombok preserve o 'new Endereco()' ao usar o padrão Builder
    private Endereco endereco = new Endereco();

    @Builder.Default
    private BigDecimal idade = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal peso = BigDecimal.ZERO;

    @Builder.Default
    private String raca = NAO_INFORMADO;

    private boolean ativo = true;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @PrePersist//Avisa ao JPA/Hibernate: Execute este método antes de disparar o INSERT no banco
    void aoCadastrar() {
        this.dataCadastro = LocalDateTime.now();
        if (this.raca == null || this.raca.isBlank()) {
            this.raca = NAO_INFORMADO;
        }
    }
}
