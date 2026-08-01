package com.example.desafioCadastro.repository;

import com.example.desafioCadastro.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

// JpaRepository: Provê operações básicas de CRUD (save, delete, findById) para a entidade Pet (chave UUID)
// JpaSpecificationExecutor: Permite criar consultas dinâmicas e filtros avançados no banco
public interface PetRepository extends JpaRepository<Pet , UUID>, JpaSpecificationExecutor<Pet> {
}
