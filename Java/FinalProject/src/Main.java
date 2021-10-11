import java.util.ArrayList;
import java.util.Random;

public class Main {
    private final static ArrayList<Property> properties = new ArrayList<>();
    private final static ArrayList<Owner> owners = new ArrayList<>();

    public static void main(String[] args) {
        setData();

        // Find 3 bedrooms property which rent is below 2000
        findRent(2000, 3);

        // find a property to buy
        chooseBuyer();
    }

    private static void setData() {
        Person yong = new Person("Yong Seung Rho", "902-123-1234", OwnerType.BUYER);
        yong.setSeedMoney(50000);

        Person wasim = new Person("Wasim Arafat", "902-456-7890", OwnerType.OWNER);
        Person shannon = new Person("Shannon Barnes", "902-567-8901", OwnerType.TENANT);
        Person ratan = new Person("Ratan Chandra Debnath", "902-111-2222", OwnerType.TENANT);
        Person renata = new Person("Renata Dantas", "902-678-0987", OwnerType.TENANT);

        Person daniel = new Person("Daniel De Ponte", "902-789-0123", OwnerType.BUYER);
        daniel.setSeedMoney(70000);

        Person brandon = new Person("Brandon Drake", "902-902-3456", OwnerType.BUYER);
        brandon.setSeedMoney(200000);

        Person madison = new Person("Madison Eisan", "902-777-8888", OwnerType.BUYER);
        madison.setSeedMoney(350000);

        Person linboya = new Person("Linboya Fu", "902-234-5678", OwnerType.AGENT);
        Person tristan = new Person("Tristan Gray", "902-667-9090", OwnerType.OWNER);
        Person alonso = new Person("Alonso Guerra", "902-555-4545", OwnerType.OWNER);
        Person cuong = new Person("Cuong Hoang", "902-887-5656", OwnerType.OWNER);

        Company wmware = new Company("WMWare", "902-321-4321", OwnerType.OWNER);
        wmware.setResidentManager("Reghd Imatarfi");

        Company killiam = new Company("Killiam", "902-654-0987", OwnerType.OWNER);
        killiam.setResidentManager("Chanhyo Kim");

        Company unversal = new Company("Universal", "902-765-1098", OwnerType.OWNER);
        unversal.setResidentManager("Kevin Master");

        owners.add(yong);
        owners.add(wasim);
        owners.add(shannon);
        owners.add(ratan);
        owners.add(renata);

        owners.add(daniel);
        owners.add(brandon);
        owners.add(madison);
        owners.add(linboya);
        owners.add(tristan);
        owners.add(alonso);
        owners.add(cuong);

        owners.add(wmware);
        owners.add(killiam);
        owners.add(unversal);

        // list up all users
        for (Owner owner : owners) {
            System.out.println(owner);
        }
        System.out.println();

        Apartment southPark = new Apartment("5415 Victoria Rd, Halifax", 3, 2);
        southPark.setRentPerMonth(1500);
        southPark.enableParking(false);
        southPark.setOwner(wmware);

        Apartment bedfordHighway = new Apartment("Peruz Ct, Bedford", 3, 1);
        bedfordHighway.setRentPerMonth(1920);
        bedfordHighway.setOwner(wmware);

        Apartment wentworth = new Apartment("131 Wentworth St, Halifax", 2, 1);
        wentworth.setRentPerMonth(2300);
        wentworth.enableParking(false);
        wentworth.setOwner(killiam);

        Apartment langbrae = new Apartment("26 Carrington St, Halifax", 1, 1);
        langbrae.setRentPerMonth(1100);
        langbrae.setOwner(killiam);

        Apartment lowerWater = new Apartment("LowerWater St, Halifax", 2, 2);
        lowerWater.setRentPerMonth(1650);
        lowerWater.setOwner(unversal);

        Condo herringCove = new Condo("540 Herring Cove Rd, Halifax", 3, 1);
        herringCove.setRentPerMonth(1800);
        herringCove.setOwner(unversal);

        Condo sackVille = new Condo("32 Romez Ct, Lower Sackville", 3, 1);
        sackVille.setMarketValue(400000);
        sackVille.enableParking(false);
        sackVille.setOwner(wasim);

        Condo qunipool = new Condo("6062 Quinpool Rd, Halifax", 2, 1);
        qunipool.setMarketValue(350000);
        qunipool.setOwner(tristan);

        Condo liverpool = new Condo("6481 Liverpool St, Halifax", 3, 2);
        liverpool.setRentPerMonth(1920);
        liverpool.enableParking(false);
        liverpool.setOwner(cuong);

        Condo marginal = new Condo("1070 Barrington St, Halifax", 2, 2);
        marginal.setRentPerMonth(1450);
        marginal.setOwner(alonso);

        House spinner = new House("Spinner Crescent, Lower Sackville", 4, 2);
        spinner.setRentPerMonth(3300);
        spinner.setOwner(cuong);

        House mainSt = new House("B3N4E9, Bible Hill", 2, 1);
        mainSt.setMarketValue(850000);
        mainSt.setOwner(alonso);

        House walton = new House("38 Walton Dr, Halifax", 3, 1);
        walton.setMarketValue(650000);
        walton.setOwner(tristan);

        House googseberry = new House("15 Gooseberry Close, Hubley", 3, 2);
        googseberry.setMarketValue(350000);
        googseberry.setOwner(killiam);

        House bauer = new House("2091 Bauer St, Halifax", 3, 1);
        bauer.setRentPerMonth(1850);
        bauer.setOwner(wmware);

        properties.add(southPark);
        properties.add(bedfordHighway);
        properties.add(wentworth);
        properties.add(langbrae);
        properties.add(lowerWater);

        properties.add(herringCove);
        properties.add(sackVille);
        properties.add(qunipool);
        properties.add(liverpool);
        properties.add(marginal);

        properties.add(spinner);
        properties.add(mainSt);
        properties.add(walton);
        properties.add(googseberry);
        properties.add(bauer);

        // list up all properties
        for (Property property : properties) {
            System.out.println(property);
        }
        System.out.println();
    }

    private static void printProperties(Property property, boolean isRent) {
        System.out.println("Address: " + property.getAddress());
        if (isRent) {
            if (property.getOwner() instanceof Company) {
                System.out.println("Resident Manager: " + ((Company) property.getOwner()).getResidentManager() + "(" + property.getOwner().getTelephone() + ")");
            } else {
                System.out.println("Owner: " + property.getOwner().getName() + "(" + property.getOwner().getTelephone() + ")");
            }

            System.out.println("The average rent of " + property.getBedrooms() + " a month in this area: $" + property.getAverageRent(property.getBedrooms()));
            if (property.includeUtility()) {
                System.out.println("Utility included: Yes");
            } else {
                System.out.println("Utility included: No");
            }
            System.out.println("The rent a month: $" + property.getRentPerMonth());
        } else {
            System.out.println("Market value: $" + property.getMarketValue());
        }

        System.out.println("The number of bathroom: " + property.getBathrooms());
        System.out.println("The type of property: " + property.getPropertyType().getName());
        if (property.parking()) {
            System.out.println("Parking: Yes");
        } else {
            System.out.println("Parking: No");
        }

        if (property.smoking()) {
            System.out.println("Smoking: Yes");
        } else {
            System.out.println("Smoking: No");
        }

        if (property.pet()) {
            System.out.println("Pets: Yes");
        } else {
            System.out.println("Pets: No");
        }
        System.out.println("==================================");
    }

    private static void findRent(int hopeRentPerMonth, int hopeBedrooms) {
        Property ppty = null;
        int i = 0;

        System.out.println("===== The candidates to rent =====");
        do {
            ppty = properties.get(i);
            if (ppty.isRent() && ppty.getBedrooms() == hopeBedrooms && ppty.getRentPerMonth() < hopeRentPerMonth) {
                printProperties(ppty, true);
            }
        } while (++i < properties.size());
        System.out.println();
    }

    private static Property chooseProperty() {
        ArrayList<Property> sellerProperties = new ArrayList<>();

        for (Property ppty : properties) {
            if (!ppty.isRent() && ppty.getPropertyType() != EnumPropertyType.APARTMENT) {
                System.out.println(ppty);
                sellerProperties.add(ppty);
            }
        }

        // select a property randomly
        int ran = new Random().nextInt(sellerProperties.size());
        return sellerProperties.get(ran);
    }

    private static void chooseBuyer() {
        Owner buyer = null, owner = null;
        ArrayList<Owner> personBuyers = new ArrayList<>();
        int i = 0;
        int ran = 0, betPercent = 0;
        while (i < owners.size()) {
            owner = owners.get(i);

            if (owner.getOwnerType() == OwnerType.BUYER) {
                System.out.println(owner);
                ran = (int) (Math.random() * 10);
                if (ran > betPercent) {
                    betPercent = ran;
                    buyer = owner;
                }
            }
            i++;
        }

        // choose a property to buy
        Property target = chooseProperty();
        System.out.println("===== The summary of the real estate to buy =====");
        printProperties(target, false);

        System.out.println("===== The financial position =====");
        double betPrice = MortgageUtil.betPrice(target, betPercent);
        double mortgage = MortgageUtil.mortgage(betPrice);
        double additionalMoney = MortgageUtil.additionalMoney((Person) buyer, betPrice, mortgage);
        double payment = MortgageUtil.paymentCalculator(target, mortgage);

        System.out.println(buyer);

        System.out.println("You have $" + ((Person) buyer).getSeedMoney() + " and bet $" + betPrice);
        System.out.println("You can borrow $" + mortgage + " from the bank and have to pay $" + (int) payment + " a month for mortgage.");
        if (additionalMoney > 0) {
            System.out.println("You need more $" + additionalMoney + " to buy this real estate.");
        }
    }
}
