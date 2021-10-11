public class Desktop extends Computer implements StorageType {
    private boolean isLED = false;
    private boolean connected_monitor = false;

    public Desktop (String model, Processors processor, int ram) {
        super(model, processor, ram);
    }


    @Override
    public void power(boolean on, boolean plugged) {
        if (on && plugged) {
            isLED = true;
        } else {
            isLED = false;
        }

    }

    @Override
    public boolean monitor() {
        if (connected_monitor) {
            return true;
        }
        return false;
    }

    @Override
    public void setBasicPriceByMonitor() {
        if (connected_monitor) {
            System.out.println("CAD $100 will be added to the retail price.");
            setPrice(getPrice() + 100);
        } else {
            System.out.println("There is no reason to pay extra without a monitor.");
        }
    }

    public void connectMonitor(boolean connected_monitor){
        this.connected_monitor = connected_monitor;
    }

    @Override
    public String getStorageType() {
        return "HDD";
    }
}
