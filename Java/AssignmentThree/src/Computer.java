public abstract class Computer implements StorageType {
    /**
     * Remember, an abstract class shouldn't be something that would ever make sense to actually instantiate.
     * Its purpose is to serve as a definition of state and behavior for related subclasses.
     * Create the abstract class : It should have at least 2 instance variables and 3 abstract methods
     */
     enum Processors {
        INTEL_CELERON,
        INTEL_PENTIUM,
        INTEL_CORE_I3,
        INTEL_CORE_I5,
        INTEL_CORE_I7
    }

    private final String model;
    private final Processors processor;
    private final int ram;
    private int price;
    private boolean isPower = false;

    public Computer(String model, Processors processor, int ram) {
        this.model = model;
        this.processor = processor;
        this.ram = ram;
    }

    /**
     * Create 2 additional classes which extend your abstract class,
     * implementing the required methods from the abstract class
     */
    abstract public void power(boolean on, boolean plugged);
    abstract public boolean monitor();
    abstract public void setBasicPriceByMonitor();

    public void setPrice(int price){
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public String getProcessor() {
        switch (processor) {
            case INTEL_PENTIUM: return "Intel Pentium";
            case INTEL_CORE_I3: return "Intel Core i3";
            case INTEL_CORE_I5: return "Intel Core i5";
            case INTEL_CORE_I7: return "Intel Core i7";
            case INTEL_CELERON:
            default:
                return "Intel Celeron";
        }
    }

    public int getRam() {
        return ram;
    }

    public int getPrice() {
        return price;
    }
}
