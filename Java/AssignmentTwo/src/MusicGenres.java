public enum MusicGenres {
    ROCK("Rock"),
    HIPHOP("Hiphop"),
    JAZZ("Jazz"),
    POP("Pop"),
    INDIE("Indie"),
    FOLK("Folk");

    final String name;

    MusicGenres(String name) {
        this.name = name;
        }

    public String getName() {
        return name;
        }
}
