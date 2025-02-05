import java.util.ArrayList;

public class Playlist {

    private ArrayList<String> songs;

    public Playlist() {
        this.songs = new ArrayList<String>();
    }

    public void addFile(String music) {
        this.songs.add(music);
    }

    public int getNumberOfFiles() {
        return this.songs.size();
    }

    public void listFiles() {
        for (int i = 0; i < this.songs.size(); i++) {
            System.out.println(i + " - " + this.songs.get(i));
        }
    }

    public void playAll() {
        for(String song : songs) {
            playFile(song);
        }
    }

    public void playFile(String song) {
        SoundEngine engine = new SoundEngine();
        engine.playCompletely(song);
    }

    public void removeFile(int index) {
        this.songs.remove(index);
    }

    public void removeFile(String music) {
        this.songs.remove(music);
    }
}