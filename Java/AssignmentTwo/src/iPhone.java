public class iPhone extends Player {
    private boolean repeatPlay;
    private boolean shufflePlay;

    public iPhone(Song selectedSong, int volume, boolean isPlaying) {
        super(selectedSong, volume, isPlaying);
    }

    public void setRepeatPlay(boolean repeatPlay) {
        this.repeatPlay = repeatPlay;
    }

    public void setShufflePlay(boolean shufflePlay) {
        this.shufflePlay = shufflePlay;
    }

    public void displayMode() {
        System.out.println("The mode of iPhone");
        System.out.println("Repeat: " + repeatPlay + ", Shuffle: " + shufflePlay + ", Playing: " + isPlaying());
    }
}
