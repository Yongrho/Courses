public class Biology extends Course {
    private final String[] arrayBiologyHighlightedTopic = {"Cell Wall", "Species Exploration"};
    private boolean hasLabComponent;

    public Biology(String name, boolean hasTextbook) {
        super(name, hasTextbook);
    }

    public void setHasLabComponent(boolean hasLabComponent) {
        this.hasLabComponent = hasLabComponent;
    }

    public boolean getHasLabComponent() {
        return hasLabComponent;
    }
}
