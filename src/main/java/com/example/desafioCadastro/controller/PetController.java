package com.example.desafioCadastro.controller;


import com.example.desafioCadastro.controller.dto.PetRequestDTO;
import com.example.desafioCadastro.controller.dto.PetResponseDto;
import com.example.desafioCadastro.domain.Pet;
import com.example.desafioCadastro.domain.enums.SexoPet;
import com.example.desafioCadastro.domain.enums.TipoPet;
import com.example.desafioCadastro.service.PetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

//Receber as informaçoes http e retornar as informaçoes para o cliente
@RestController //No momento que compilar o projeto esse sera o controller
@RequestMapping("/api/pets")//

public class PetController {


    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponseDto cadastrar(@Valid @RequestBody PetRequestDTO dto){
        Pet pet = petService.cadastrar(dto);
        return PetResponseDto.from(pet);
    }

    @PutMapping("/{id}")
    public PetResponseDto alterar(@PathVariable UUID id, @Valid @RequestBody PetRequestDTO dto) {
        Pet pet = petService.alterar(id, dto);
        return PetResponseDto.from(pet);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable UUID id) {
        petService.deletar(id);
    }

    @GetMapping("/{id}")
    public PetResponseDto buscarPorId(@PathVariable UUID id) {
        return PetResponseDto.from(petService.buscarPorId(id));
    }

    @GetMapping()
    public List<PetResponseDto> buscar(
            @RequestParam(required = false) TipoPet tipo, // captura parâmetros
            @RequestParam(required = false) SexoPet sexo,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String raca,
            @RequestParam(required = false) BigDecimal idadeMin,
            @RequestParam(required = false) BigDecimal idadeMax,
            @RequestParam(required = false) BigDecimal pesoMin,
            @RequestParam(required = false) BigDecimal pesoMax
    ) {
        List<Pet> pets = (tipo == null && sexo == null && nome == null && raca == null
                && idadeMin == null && idadeMax == null && pesoMin == null && pesoMax == null)
                ? petService.listarTodos()
                : petService.buscar(tipo, sexo, nome, raca, idadeMin, idadeMax, pesoMin, pesoMax);

        return pets.stream().map(PetResponseDto::from).toList();
    }
}
