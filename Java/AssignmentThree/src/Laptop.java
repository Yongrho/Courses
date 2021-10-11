public class Laptop extends Computer implements StorageType {
    private int iBatteryLevel = 100;
    private final int BATTERY_CONSUMPTION = 5;
    private boolean isCharging = false;

    public Laptop (String model, Processors processor, int ram) {
        super(model, processor, ram);
    }

    @Override
    public void power(boolean on, boolean plugged) {
        if (plugged) {
            if (iBatteryLevel < 100) {
                if (on) {
                    isCharging = true;
                } else {
                    isCharging = false;
                }
            }
        } else {
            isCharging = false;
            iBatteryLevel -= BATTERY_CONSUMPTION;
        }
    }

    @Override
    public boolean monitor() {
        return true;
    }

    @Override
    public void setBasicPriceByMonitor() {
        System.out.println("A laptop does not have to pay extra.");
    }

    @Override
    public String getStorageType() {
        return "SSD";
    }
}
