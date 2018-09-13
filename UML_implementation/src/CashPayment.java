public class CashPayment implements Payment {
    private int amountTendered;
    private int changeGiven;


    @Override
    public int calcAmount(int price, int paid) {
        this.amountTendered = paid;
        this.changeGiven = price - paid;
        return changeGiven;
    }
}
