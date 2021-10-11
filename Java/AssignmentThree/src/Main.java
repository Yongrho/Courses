import java.util.ArrayList;

public class Main {
//    private static final ArrayList<Computer> processorOne = new ArrayList<>();
    private static final ArrayList<StorageType> processorOne = new ArrayList<>();
    private static final ArrayList<Computer> processorTwo = new ArrayList<>();

    public static void main(String[] args) {
        Laptop dell_5502 = new Laptop("5502", Computer.Processors.INTEL_CORE_I7, 16);
        dell_5502.power(true, true);

        Laptop lenovo_S330  = new Laptop("S330", Computer.Processors.INTEL_CORE_I3, 4);
        lenovo_S330.power(false, true);

        Laptop asus_C204EE = new Laptop("C204EE", Computer.Processors.INTEL_CORE_I5, 4);
        asus_C204EE.power(true, false);

        Laptop microsoft_S330  = new Laptop("S330", Computer.Processors.INTEL_CORE_I5, 8);
        microsoft_S330.power(false, false);

        Desktop hp_Elite = new Desktop("Elite", Computer.Processors.INTEL_CORE_I5, 8);
        hp_Elite.power(true, true);
        hp_Elite.connectMonitor(true);

        Desktop dell_Optiplex  = new Desktop("Optiplex", Computer.Processors.INTEL_CORE_I5, 16);
        dell_Optiplex.power(false, true);
        dell_Optiplex.connectMonitor(true);

        Desktop asus_Aspire = new Desktop("Aspire", Computer.Processors.INTEL_CORE_I3, 12);
        asus_Aspire.power(true, false);
        asus_Aspire.connectMonitor(false);

        Desktop beelink_BT3  = new Desktop("BT3", Computer.Processors.INTEL_CORE_I3, 4);
        beelink_BT3.power(false, false);
        beelink_BT3.connectMonitor(false);

        Desktop samsung_sense_  = new Desktop("Sense", Computer.Processors.INTEL_CORE_I7, 16);
        samsung_sense_.power(true, true);
        samsung_sense_.connectMonitor(true);

        // One collection should be of the type of your Interface you created
        processorOne.add(dell_5502);
        processorOne.add(lenovo_S330);
        processorOne.add(asus_Aspire);
        processorOne.add(beelink_BT3);

        // One collection should be of the type of your abstract class you created
        processorTwo.add(asus_C204EE);
        processorTwo.add(microsoft_S330);
        processorTwo.add(beelink_BT3);
        processorTwo.add(dell_Optiplex);

        // Call your interface methods in your interface type loop
        System.out.println("===== Call your interface methods in your interface type loop =====");
    /**
        for (Computer comOne : processorOne) {
            System.out.println(comOne.getModel() + " has " + comOne.getStorageType());
        }
    */
        for (StorageType comOne : processorOne) {
            System.out.println(comOne.getStorageType());
        }
        System.out.println();

        // Call your abstract methods in your abstract class type loop
        System.out.println("===== Call your abstract methods in your interface type loop =====");
        for (Computer comTwo : processorTwo) {
            if (comTwo.monitor()) {
                System.out.println(comTwo.getModel() + " has a monitor.");
            } else {
                System.out.println(comTwo.getModel() + " does not have a monitor.");
            }
            comTwo.setBasicPriceByMonitor();
        }
    }
}
