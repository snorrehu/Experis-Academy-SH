public class CreditCardPayment implements Payment {
    private String amount;



    @Override
    public int calcAmount(int price, int paid) {
        authorize();

        if (authorize()){
            return 1;
        }
        else {
            return 0;
        }
    }

    public boolean authorize(){
        return true;
    }
}
