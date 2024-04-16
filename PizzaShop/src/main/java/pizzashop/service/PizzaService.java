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
        return getTotalAmountStatic(this.getPayments(), type);
        /*double total=0.0f;
        List<Payment> l=getPayments();
        // spargem if-ul in doua ca sa facem CC=5
        //if ((l==null) ||(l.size()==0)) return total;
        if(l==null) return total;
        //if(type==PaymentType.CARD && l.size()==0) return total;
        if(l.size()==0) return total;
        for (Payment p:l){
            if (p.getType().equals(type))
                total+=p.getAmount();
        }
        return total;*/
    }

    public static double getTotalAmountStatic(List<Payment> l, PaymentType type){
        double total=0.0f;
        // spargem if-ul in doua ca sa facem CC=5
        //if ((l==null) ||(l.size()==0)) return total;
        if(l==null)
            throw new IllegalArgumentException();
        //if(type==PaymentType.CARD && l.size()==0) return total;
        if(l.size()==0) return total;
        for (Payment p:l){
            if (p.getType().equals(type))
                total+=p.getAmount();
        }
        return total;
    }

}