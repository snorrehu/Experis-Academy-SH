import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Sale extends JOptionPane{
    private int amount;

    Payment payment;

    public Sale(){
        String priceString;
        int amountPaid;
        String[] possibiities = {"Credit Card","Cash"};
        String paymentChoice;

        //Collect price
        priceString = JOptionPane.showInputDialog(null,"Enter Price");
        System.out.println("Price entered: " + priceString);
        int price = Integer.parseInt(priceString);

        if(priceString!=null){
            paymentChoice = (String)JOptionPane.showInputDialog(null, "Enter payment method", "Customized Dialog", JOptionPane.PLAIN_MESSAGE, icon,
                    possibiities, "Credit Card");
            System.out.println("Payment method chosen: " + paymentChoice);
            if(paymentChoice.equals("Credit Card")){
                CreditCardPayment cardPayment = new CreditCardPayment();
                int cardPaymentOutput = cardPayment.calcAmount(price,0);
                System.out.println(cardPaymentOutput);
                if(cardPaymentOutput==1){
                    JOptionPane.showMessageDialog(null, "Payment accepted!");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Payment declined...");
                }
            }
            else{
                amountPaid = Integer.parseInt(JOptionPane.showInputDialog(null,"Amount paid"));
                System.out.println("Amount entered: " + amountPaid);
                CashPayment cashPayment = new CashPayment();
                int change = cashPayment.calcAmount(price,amountPaid);
                JOptionPane.showMessageDialog(null, "Change given: " +change);
            }
        }


        //Collect payment method:

        /*
        //Create button listener
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(e.getActionCommand()+" clicked");
            }
        };
        */



    }



    public static void main(String[] args){
        new Sale();
    }
}
