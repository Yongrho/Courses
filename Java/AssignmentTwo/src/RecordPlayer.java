public class RecordPlayer extends Player{
    private boolean isRecordPlayerCoverOpen;

    /**
    public RecordPlayer(Song selectedSong, int volume, boolean isPlaying) {
        super(selectedSong, volume, isPlaying);
    }
    */

    public boolean isRecordPlayerCoverOpen() {
        return isRecordPlayerCoverOpen;
    }

    public void setRecordPlayerCoverOpen(boolean recordPlayerCoverOpen) {
        isRecordPlayerCoverOpen = recordPlayerCoverOpen;
    }

    @Override
    public void play() {
        super.play();
        isRecordPlayerCoverOpen = false;
    }

    @Override
    public void stop() {
        super.stop();
        isRecordPlayerCoverOpen = true;
    }

    @Override
    public void setPlaying(boolean playing) {
        super.setPlaying(playing);
        isRecordPlayerCoverOpen = !playing;
    }
}
