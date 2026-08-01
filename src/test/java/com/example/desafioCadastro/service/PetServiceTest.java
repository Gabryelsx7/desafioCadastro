package com.example.desafioCadastro.service;

import com.example.desafioCadastro.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock//Cria um objeto falso (dublê)
    private PetRepository petRepository;

    @InjectMocks//Cria a instância real da Classe
    private PetService petService;


}