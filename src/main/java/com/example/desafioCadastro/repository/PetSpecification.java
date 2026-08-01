package com.example.desafioCadastro.repository;

import com.example.desafioCadastro.domain.Pet;
import com.example.desafioCadastro.domain.enums.SexoPet;
import com.example.desafioCadastro.domain.enums.TipoPet;
import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class PetSpecification {

    private PetSpecification() {
    }

    public static Specification<Pet> nomeContem(String termo) {
        return (root, query, cb) -> {
            if (termo == null || termo.isBlank()) {
                return null;
            }
            // Concatena nome e sobrenome
            Expression<String> nomeCompleto = cb.concat(
                    cb.concat(root.get("nome"), " "),
                    cb.concat(root.get("sobrenome"), "")
            );

            // Aplica lower + unaccent
            Expression<String> nomeSemAcento = cb.function("unaccent", String.class, cb.lower(nomeCompleto));
            String termoSemAcento = "%" + termo.trim().toLowerCase() + "%";

            return cb.like(nomeSemAcento, cb.function("unaccent", String.class, cb.literal(termoSemAcento)));
        };
    }

    public static Specification<Pet> tipoIgual(TipoPet tipo) {
        return (root, query, cb) -> tipo == null ? null : cb.equal(root.get("tipo"), tipo);
    }

    public static Specification<Pet> sexoIgual(SexoPet sexo) {
        return (root, query, cb) -> sexo == null ? null : cb.equal(root.get("sexo"), sexo);
    }

    public static Specification<Pet> racaIgual(String raca) {
        return (root, query, cb) -> (raca == null || raca.isBlank())
                ? null : cb.equal(cb.lower(root.get("raca")), raca.trim().toLowerCase());
    }

    public static Specification<Pet> pesoEntre(BigDecimal min, BigDecimal max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min != null && max != null) return cb.between(root.get("peso"), min, max);
            if (min != null) return cb.greaterThanOrEqualTo(root.get("peso"), min);
            return cb.lessThanOrEqualTo(root.get("peso"), max);
        };
    }

    public static Specification<Pet> idadeEntre(BigDecimal min, BigDecimal max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min != null && max != null) return cb.between(root.get("idade"), min, max);
            if (min != null) return cb.greaterThanOrEqualTo(root.get("idade"), min);
            return cb.lessThanOrEqualTo(root.get("idade"), max);
        };
    }

    public static Specification<Pet> apenasAtivos() {
        return (root, query, cb) -> cb.isTrue(root.get("ativo"));
    }
}