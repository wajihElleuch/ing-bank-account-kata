package fr.dev.kata.services;

import fr.dev.kata.models.Bank;
import fr.dev.kata.repositories.BankRepository;
import fr.dev.kata.services.impl.BankServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {

    public static final String NAME = "name";
    public static final String ID = "ID";
    @InjectMocks
    BankServiceImpl bankService;

    @Mock
    BankRepository bankRepository;

    @Test
    void shouldCreateBank() {
        Bank bankDto = Bank.builder().name(NAME).build();

        Bank bank = Bank.builder()
                .id(ID)
                .name(NAME)
                .build();

        when(bankRepository.save(bankDto)).thenReturn(bank);

        Bank response = bankService.create(bankDto);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(ID);
        assertThat(response.getName()).isEqualTo(NAME);
    }
}
