package mock_tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PaymentRepositoryUnitTest {
    private PaymentRepository paymentRepository;
    static String filename = "test_payments.txt";

    @BeforeEach
    void setUp() throws IOException {
        paymentRepository = new PaymentRepository(filename);
    }

    @AfterEach
    void tearDown() {
        File file = new File(filename);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("1,CARD,12.0");
            bw.newLine();
            bw.write("2,CASH,22.4");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void test_getAll() {
        List<Payment> payments = paymentRepository.getAll();
        Assertions.assertEquals(2,payments.size());
        Assertions.assertEquals(new Payment(1,PaymentType.CARD,12.0), payments.get(0));
        Assertions.assertEquals(new Payment(2,PaymentType.CASH,22.4), payments.get(1));
    }

    @Test
    void test_add() {
        Payment payment = new Payment(1,PaymentType.CARD,10.0);
        paymentRepository.add(payment);
        List<Payment> payments = paymentRepository.getAll();
        Assertions.assertEquals(3,payments.size());
        Assertions.assertEquals(new Payment(1,PaymentType.CARD,12.0), payments.get(0));
        Assertions.assertEquals(new Payment(2,PaymentType.CASH,22.4), payments.get(1));
        Assertions.assertEquals(payment, payments.get(2));
    }
}
