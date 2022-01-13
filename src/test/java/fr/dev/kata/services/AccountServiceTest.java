package fr.dev.kata.services;

import fr.dev.kata.dto.TransactionDTO;
import fr.dev.kata.exceptions.AccountNotFoundException;
import fr.dev.kata.models.*;
import fr.dev.kata.repositories.AccountRepository;
import fr.dev.kata.services.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    public static final String ACCOUNT_ID = "accountId";
    private static final BigDecimal MIN_VALUE = new BigDecimal("0.01");
    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    TransactionService transactionService;

    @Captor
    ArgumentCaptor<Account> captureAccount;

    @Captor
    ArgumentCaptor<TransactionType> captureTransactionType;

    @Test
    void shouldCreateAccount() {
        Customer customer = Customer.builder()
                                .id("customerId")
                                .build();

        Bank bank = Bank.builder()
                            .id("bankId")
                            .build();

        Account accountDto = Account.builder()
                .numAccount(1234L)
                .customer(customer)
                .bank(bank)
                .build();

        Account account = Account.builder()
                .id(ACCOUNT_ID)
                .numAccount(1234L)
                .amount(BigDecimal.ZERO)
                .bank(bank)
                .customer(customer)
                .build();

        when(accountRepository.save(accountDto)).thenReturn(account);

        Account response = accountService.create(accountDto);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(ACCOUNT_ID);
        assertThat(response.getNumAccount()).isEqualTo(1234L);
        assertThat(response.getAmount()).isEqualTo(BigDecimal.ZERO);
        assertThat(response.getBank().getId()).isEqualTo("bankId");
        assertThat(response.getCustomer().getId()).isEqualTo("customerId");
        assertThat(response.getTransactions()).isNull();
    }

    @Test
    void shouldDepositAmountToAccountBalance() {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                                            .amount(BigDecimal.TEN)
                                            .build();

        Account account = Account.builder()
                .id(ACCOUNT_ID)
                .numAccount(1234L)
                .amount(BigDecimal.ZERO)
                .build();

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(BigDecimal.TEN)
                .type(TransactionType.DEPOSIT)
                .build();

        when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.ofNullable(account));
        when(transactionService.create(TransactionType.DEPOSIT, account, transactionDTO.getAmount())).thenReturn(transaction);

        Transaction result = accountService.deposit(ACCOUNT_ID, transactionDTO);

        Mockito.verify(transactionService).create(captureTransactionType.capture(), captureAccount.capture(), any());
        Account resultAccount = captureAccount.getValue();
        TransactionType transactionType = captureTransactionType.getValue();


        assertThat(result).isNotNull();
        assertThat(result.getAccount().getId()).isEqualTo(ACCOUNT_ID);
        assertThat(result.getAmount()).isEqualTo(BigDecimal.TEN);
        assertThat(result.getType()).isEqualTo(TransactionType.DEPOSIT);
        assertThat(resultAccount.getAmount()).isEqualTo(BigDecimal.TEN);
        assertThat(transactionType.toString()).isEqualTo(TransactionType.DEPOSIT.toString());
    }

    @Test
    void shouldThrowExceptionWhenDepositLessThanMinValue() {

        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(BigDecimal.ZERO)
                .build();

        assertThatThrownBy(() -> {
        accountService.deposit(ACCOUNT_ID, transactionDTO);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("amount must be greater than " + MIN_VALUE);
    }

    @Test
    void shouldWithdrawAmountFromAccountBalance() {
        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(BigDecimal.TEN)
                .build();

        Account account = Account.builder()
                .id(ACCOUNT_ID)
                .numAccount(1234L)
                .amount(BigDecimal.TEN)
                .build();

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(BigDecimal.TEN)
                .type(TransactionType.WITHDRAW)
                .build();

        when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.ofNullable(account));
        when(transactionService.create(TransactionType.WITHDRAW, account, transactionDTO.getAmount())).thenReturn(transaction);

        Transaction result = accountService.withdraw(ACCOUNT_ID, transactionDTO);

        assertThat(result).isNotNull();
        assertThat(result.getAccount().getId()).isEqualTo(ACCOUNT_ID);
        assertThat(result.getAmount()).isEqualTo(BigDecimal.TEN);
        assertThat(result.getAccount().getAmount()).isEqualTo(BigDecimal.ZERO);
        assertThat(result.getType()).isEqualTo(TransactionType.WITHDRAW);
    }
    @Test
    void shouldThrowExceptionWhenWithdrawLessThanZero() {

        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(BigDecimal.ZERO)
                .build();


        assertThatThrownBy(() -> {
            accountService.withdraw(ACCOUNT_ID, transactionDTO);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("amount must be grater than 0");
    }
    @Test
    void shouldThrowExceptionWhenAccountNotFound() {

        TransactionDTO transactionDTO = TransactionDTO.builder()
                .amount(BigDecimal.TEN)
                .build();


        when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> {
            accountService.withdraw(ACCOUNT_ID, transactionDTO);
        }).isInstanceOf(AccountNotFoundException.class)
                .hasMessageContaining("didn't find Account with id:");
    }

    @Test
    void shouldReturnAccountBalance()  {

        Account account = Account.builder()
                .id(ACCOUNT_ID)
                .numAccount(1234L)
                .amount(BigDecimal.TEN)
                .build();

        when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.ofNullable(account));

        BigDecimal result = accountService.getBalance(ACCOUNT_ID);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(BigDecimal.TEN);
    }
}
