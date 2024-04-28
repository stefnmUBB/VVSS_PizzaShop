package mock_tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.IPaymentRepository;
import pizzashop.service.PizzaService;

import java.util.Arrays;
import java.util.List;

public class PizzaServiceTest {

    @Mock
    private IPaymentRepository paymentRepository;

    @InjectMocks
    private PizzaService pizzaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getPayments() {
        Payment p1 = new Payment(5, PaymentType.CARD, 10.5);
        Mockito.when(paymentRepository.getAll()).thenReturn(List.of(p1));
        Assertions.assertEquals(1, pizzaService.getPayments().size());
    }

    @Test
    public void test_addPayment() {

    }
}
