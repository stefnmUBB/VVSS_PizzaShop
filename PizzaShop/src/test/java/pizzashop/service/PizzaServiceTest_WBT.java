package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class PizzaServiceTest_WBT {
    private PaymentType type;
    private List<Payment> l;
    private double total;
    PizzaService srv;

    static String filename = "test_payments.txt";

    @BeforeEach
    void setUp() {
        List<Payment> payments = List.of(new Payment(1,PaymentType.CARD,12),new Payment(2,PaymentType.CASH,22.4));
        File file = new File(filename);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Payment p:payments) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            MenuRepository repoMenu = new MenuRepository("data/menu.txt");
            PaymentRepository payRepo = new PaymentRepository("test_payments.txt");
            srv = new PizzaService(repoMenu, payRepo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        // GARBAGE COLLECTOR
    }

    @Test
    void getTotalAmount_F02_TC01() {
        l = null;
        type = PaymentType.CASH;
        total = 0;
        Assertions.assertEquals(PizzaService.getTotalAmountStatic(l, type), total);
    }

    @Test
    void getTotalAmount_F02_TC02() {
        l = List.of();
        type = PaymentType.CASH;
        total = 0;
        Assertions.assertEquals(PizzaService.getTotalAmountStatic(l, type), total);
    }

    @Test
    void getTotalAmount_F02_TC03() {
        // ??
        l = List.of();
        type = PaymentType.CASH;
        total = 0;
        Assertions.assertEquals(PizzaService.getTotalAmountStatic(l, type), total);
    }

    @Test
    void getTotalAmount_F02_TC04() {
        l = List.of(new Payment(1, PaymentType.CASH, 5));
        type = PaymentType.CARD;
        total = 0;
        Assertions.assertEquals(PizzaService.getTotalAmountStatic(l, type), total);
    }

    @Test
    void getTotalAmount_F02_TC05() {
        l = List.of(
                new Payment(1, PaymentType.CARD, 2),
                new Payment(1, PaymentType.CARD, 3));
        type = PaymentType.CARD;
        total = 5;
        Assertions.assertEquals(PizzaService.getTotalAmountStatic(l, type), total);
    }


}
