package com.example.desafioCadastro;

import com.example.desafioCadastro.controller.dto.PetRequestDTO;
import com.example.desafioCadastro.domain.Pet;
import com.example.desafioCadastro.domain.enums.SexoPet;
import com.example.desafioCadastro.domain.enums.TipoPet;
import com.example.desafioCadastro.repository.PetRepository;
import com.example.desafioCadastro.service.PetService;
import com.example.desafioCadastro.service.exception.RegraDeNegocioException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

	@Mock
	private PetRepository petRepository;

	@InjectMocks
	private PetService petService;

	@Test
	void naoDeveAlterarTipoDeUmPetJaCadastrado() {
		UUID id = UUID.randomUUID();
			 Pet existente = Pet.builder()
				.id(id)
				.nome("Rex")
				.tipoPet(TipoPet.CACHORRO)
				.sexoPet(SexoPet.MACHO)
				.build();

		when(petRepository.findById(id)).thenReturn(Optional.of(existente));

		PetRequestDTO dtoComTipoDiferente = new PetRequestDTO(
				"Rex", "Silva", TipoPet.GATO, SexoPet.MACHO,
				null, null, null, BigDecimal.ONE, BigDecimal.TEN, "Vira-lata"
		);

		assertThatThrownBy(() -> petService.alterar(id, dtoComTipoDiferente))
				.isInstanceOf(RegraDeNegocioException.class)
				.hasMessageContaining("tipo ou o sexo");
	}

	@Test
	void devePreencherCamposEmBrancoComNaoInformado() {
		when(petRepository.save(any(Pet.class))).thenAnswer(invocation -> invocation.getArgument(0));

		PetRequestDTO dto = new PetRequestDTO(
				"Florzinha", null, TipoPet.GATO, SexoPet.FEMEA,
				null, null, null, new BigDecimal("6"), new BigDecimal("5"), null
		);

		Pet salvo = petService.cadastrar(dto);

		assertThat(salvo.getSobrenome()).isEqualTo(Pet.NAO_INFORMADO);
		assertThat(salvo.getRaca()).isEqualTo(Pet.NAO_INFORMADO);
		assertThat(salvo.getEndereco().getCidade()).isEqualTo(Pet.NAO_INFORMADO);
	}
}
