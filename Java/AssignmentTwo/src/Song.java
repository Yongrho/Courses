public class Song {
    private final String name;
    private final int lengthInSeconds;
    private final MusicGenres genre;

    public Song(String name, int lengthInSeconds, MusicGenres genre) {
        this.name = name;
        this.lengthInSeconds = lengthInSeconds;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public int getLengthInSeconds() {
        return lengthInSeconds;
    }

    public String getMusicGenre() {
        return genre.getName();
    }
}
