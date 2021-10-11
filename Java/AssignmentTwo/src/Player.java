public class Player {
    private Song selectedSong;
    private int volume;
    private boolean isPlaying;

    public Player() {
    }

    public Player(Song selectedSong, int volume, boolean isPlaying) {
        this.selectedSong = selectedSong;
        this.volume = volume;
        this.isPlaying = isPlaying;
    }

    public Song getSelectedSong() {
        return selectedSong;
    }

    public void setSelectedSong(Song selectedSong, int volume) {
        this.selectedSong = selectedSong;
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public void play() {
        System.out.println("play...");
        isPlaying = true;
    }

    public void stop() {
        System.out.println("stop...");
        isPlaying = false;
    }

    public void fastforward() {
        System.out.println("fastforward...");
        isPlaying = false;
    }

    public void rewind() {
        System.out.println("rewind...");
        isPlaying = false;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Player{" +
                "selectedSong=" + selectedSong +
                ", volume=" + volume +
                ", isPlaying=" + isPlaying +
                '}';
    }
}