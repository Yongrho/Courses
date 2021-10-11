public class MP3Player extends Player {
    private int batteryLevel = 100;
    private final int BATTERY_DRAIN = 5;

    /**
    public MP3Player() {
        super();
    }

    public MP3Player(Song selectedSong, int volume, boolean isPlaying) {
        super(selectedSong, volume, isPlaying);
    }
    */

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void updateBatteryLevel(int batteryLevel) {
        this.batteryLevel += batteryLevel;
    }

    @Override
    public void play() {
        super.play();
        batteryLevel -= BATTERY_DRAIN;
    }

    @Override
    public void setPlaying(boolean playing) {
        super.setPlaying(playing);
        if (playing == true) {
            batteryLevel -= BATTERY_DRAIN;
        }
    }
}
