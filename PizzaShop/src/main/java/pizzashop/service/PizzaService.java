package pizzashop.service;

import pizzashop.model.Order;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo){
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
    }

    public List<Order> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

//    public void addPayment(int table, PaymentType type, double amount){
//        Payment payment= new Payment(table, type, amount);
//        payRepo.add(payment);
//    }

    public void addPayment(int table, PaymentType type, double amount){
        if(table < 1 || table > 8)
            throw new IllegalArgumentException();
        if (amount <= 0)
            throw new IllegalArgumentException();
        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type){
        double total=0.0f;
        List<Payment> l=getPayments();
        if ((l==null) ||(l.size()==0)) return total;
        for (Payment p:l){
            if (p.getType().equals(type))
                total+=p.getAmount();
        }
        return total;
    }

}