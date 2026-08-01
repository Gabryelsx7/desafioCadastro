package com.example.desafioCadastro.service;


import com.example.desafioCadastro.controller.dto.PetRequestDTO;
import com.example.desafioCadastro.domain.Endereco;
import com.example.desafioCadastro.domain.Pet;
import com.example.desafioCadastro.domain.enums.SexoPet;
import com.example.desafioCadastro.domain.enums.TipoPet;
import com.example.desafioCadastro.repository.PetRepository;
import com.example.desafioCadastro.repository.PetSpecification;
import com.example.desafioCadastro.service.exception.PetNaoEncontradoException;
import com.example.desafioCadastro.service.exception.RegraDeNegocioException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional
    public Pet cadastrar(PetRequestDTO dto) {
        Pet pet = Pet.builder().nome(dto.nome())
                .sobrenome(valorNaoInformado(dto.sobrenome()))
                .tipoPet(dto.tipo()).sexoPet(dto.sexo())
                .endereco(new Endereco
                        (valorNaoInformado(dto.numeroCasa()),
                                valorNaoInformado(dto.cidade()),
                                valorNaoInformado(dto.rua())))
                .idade(normalizaridade(dto.idade()))
                .peso(dto.peso() == null ? BigDecimal.ZERO : dto.peso())
                .raca(valorNaoInformado(dto.raca()))
                .build();

        return petRepository.save(pet);
    }
    public Pet alterar(UUID id, PetRequestDTO dto) {
        Pet existente = buscarPorId(id);

        if (dto.tipo() != existente.getTipoPet() || dto.sexo() != existente.getSexoPet()) {
            throw new RegraDeNegocioException(
                    "Não é permitido alterar o tipo ou o sexo de um pet já cadastrado");
        }

        existente.setNome(dto.nome());
        existente.setSobrenome(valorNaoInformado(dto.sobrenome()));
        existente.getEndereco().setNumerodaCasa(valorNaoInformado(dto.numeroCasa()));
        existente.getEndereco().setCidade(valorNaoInformado(dto.cidade()));
        existente.getEndereco().setRua(valorNaoInformado(dto.rua()));
        existente.setIdade(normalizaridade(dto.idade()));
        existente.setPeso(dto.peso() == null ? existente.getPeso() : dto.peso());
        existente.setRaca(valorNaoInformado(dto.raca()));

        return petRepository.save(existente);
    }
    @Transactional
    public void deletar(UUID id) {
        Pet pet = buscarPorId(id);
        pet.setAtivo(false); // Busca apenas ativos
        petRepository.save(pet);
    }
    public Pet buscarPorId(UUID id) {
        return petRepository.findById(id).orElseThrow(() -> new PetNaoEncontradoException(id));
    }
        public List<Pet> buscar(TipoPet tipo, SexoPet sexo, String nome, String raca, BigDecimal idadeMinima, BigDecimal idadeMax, BigDecimal pesoMinima, BigDecimal pesoMax) {
        Specification<Pet> spec = Specification.where(PetSpecification.apenasAtivos())
                .and(PetSpecification.tipoIgual(tipo))
                .and(PetSpecification.sexoIgual(sexo))
                .and(PetSpecification.nomeContem(nome))
                .and(PetSpecification.racaIgual(raca))
                .and(PetSpecification.idadeEntre(idadeMinima, idadeMax))
                .and(PetSpecification.pesoEntre(pesoMinima, pesoMax));

        return petRepository.findAll(spec);
    }

    public List<Pet> ListarTodos() {
        return petRepository.findAll(Specification.where(PetSpecification.apenasAtivos()));
    }


    private String valorNaoInformado(String valor) {
        return (valor == null || valor.isBlank() ? Pet.NAO_INFORMADO : valor.trim());
    }

    private BigDecimal normalizaridade(BigDecimal idade) {
        return idade == null ? BigDecimal.ZERO : idade;
    }
    public List<Pet> listarTodos() {
        return petRepository.findAll(Specification.where(PetSpecification.apenasAtivos()));
    }
}
