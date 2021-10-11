public class MortgageUtil {
    private static final double MAX_MORTGAGE_RATE = 0.75;

    public static double paymentCalculator(Property ppty, double mortgage) {
        int year;

        switch (ppty.getPropertyType()) {
            case APARTMENT:
                System.out.println("This type of property cannot calculate payment.");
                return 0;
            case CONDO:
                year = 20;
                break;
            case HOUSE:
            default:
                year = 30;
                break;
        }
        return mortgage / (year * 12);
    }

    public static double betPrice(Property ppty, int betPercent) {
        return ppty.getMarketValue() + ppty.getMarketValue() * betPercent / 100;
    }

    public static double mortgage(double betPrice) {
        return betPrice * MAX_MORTGAGE_RATE;
    }

    public static double additionalMoney(Person person, double betPrice, double mortgage) {
        return betPrice - mortgage - person.getSeedMoney();
    }
}
