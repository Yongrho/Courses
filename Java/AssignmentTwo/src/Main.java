public class Main {
    public static void main(String[] args) {
        // The songs we are going to play
        Song robotRock = new Song("Robot Rock", 300, MusicGenres.ROCK);
        Song freeBird = new Song("Free Bird", 600, MusicGenres.POP);
        Song savage = new Song("Savage", 242, MusicGenres.HIPHOP);
        Song rainOnMe = new Song("Rain On Me", 189, MusicGenres.POP);
        Song peopleIveBeenSad = new Song("People I've been sad", 446, MusicGenres.INDIE);
        Song rockStar = new Song("Rock Star", 182, MusicGenres.HIPHOP);
        Song dynamite = new Song("Dynamite", 224, MusicGenres.POP);
        Song deepInLove = new Song("Deep In Love", 290, MusicGenres.FOLK);
        Song wondrousLove = new Song("Wondrous Love", 213, MusicGenres.FOLK);
        Song sheWasYoung = new Song("She Was Young", 290, MusicGenres.JAZZ);
        Song dreamVoyager = new Song("The Dream Voyager", 301, MusicGenres.JAZZ);

        PlayList playList = new PlayList();
        playList.add(robotRock);
        playList.add(freeBird);
        playList.add(savage);
        playList.add(rainOnMe);
        playList.add(peopleIveBeenSad);
        playList.add(rockStar);
        playList.add(dynamite);
        playList.add(robotRock);
        playList.add(deepInLove);
        playList.add(wondrousLove);
        playList.add(sheWasYoung);
        playList.add(dreamVoyager);

        // log information about our songs to the console
        playList.show();

        // instantiate our music player objects
        MP3Player mp3Player = new MP3Player();
        RecordPlayer recordPlayer = new RecordPlayer();

        // play the songs
        mp3Player.setSelectedSong(robotRock, 100);
        mp3Player.play();

        recordPlayer.setSelectedSong(freeBird, 25);
        recordPlayer.play();
        System.out.println("Does the cover of the record player? " + recordPlayer.isRecordPlayerCoverOpen());

        // update the battery level of our mp3 player
        System.out.println("Current battery level: " + mp3Player.getBatteryLevel());

        // stop playing our songs
        mp3Player.stop();
        recordPlayer.stop();
        System.out.println("Does the cover of the record player? " + recordPlayer.isRecordPlayerCoverOpen());

        // add songs into a playlist of iPhone
        iPhone iphone = new iPhone(deepInLove, 50, true);
        iphone.setRepeatPlay(true);
        iphone.setShufflePlay(false);

        iphone.displayMode();
    }
}
