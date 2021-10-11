import java.util.ArrayList;

public class PlayList {
    private final ArrayList<Song> playList = new ArrayList<>();

    public PlayList() {
    }

    public void add(Song song) {
        playList.add(song);
    }

    public void show() {
        System.out.println("===== The play list =====");
        for (Song song : playList) {
            System.out.println("Song: " + song.getName() + ", Length: " + song.getLengthInSeconds() + " Genre: " + song.getMusicGenre());
        }
    }
}
