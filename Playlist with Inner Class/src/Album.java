import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Album {

    private Scanner scanner = new Scanner (System.in);
    private String title;
    private SongList songs;         // Choosing an inner class for the songs list may duplicate some code
                                    // but was part of the requirements of the challenge.
    public Album (String title) {
        this.title = title;
        this.songs = new SongList();
    }

    public String getTitle () {
        return this.title;
    }

    private class SongList {    // Inner class for the songs list in the album
        private LinkedList<Song> tracks;

        public SongList () {
            this.tracks = new LinkedList<>();
        }

        public boolean addSong () {                             // 3 overloaded methods for adding a song,
            System.out.println("Enter song's title: ");         // one of them is added just for the auto-populate option in Main class
            String tTitle = scanner.nextLine();
            System.out.println("Enter song's duration [mm:ss]:");
            String dur = scanner.nextLine();
            return addSong(tTitle, dur);
        }

        public boolean addSong (String tTitle) {
            return addSong (tTitle, "00:00");
        }

        public boolean addSong (String tTitle, String tDur) {
            if (findSong(tTitle) == null) {
                this.tracks.add(new Song(tTitle, tDur));
                return true;
            }
            System.out.println("Song with this title already exists in this album. Action failed.");
            return false;
        }

        public Song findSong (String tTitle) {
            for (Song checkedSong: this.tracks) {
                if (checkedSong.getTitle().toLowerCase().equals(tTitle.toLowerCase())) {
                    return checkedSong;
                }
            }
            return null;
        }

        public boolean deleteSong (String tTitle) {
            Song songForDeleting = findSong(tTitle);
            if (songForDeleting != null) {
                tracks.remove(songForDeleting);
                System.out.println("Song deleted successfully.");
                return true;
            }
            System.out.println("Song with this title not found in this album. Action failed.");
            return false;
        }

    }

    public boolean addSong () {
           return this.songs.addSong();
    }

    public boolean addSong (String title) {
        return this.songs.addSong(title);
    }

    public Song findSong (String title) {
        return this.songs.findSong(title);
    }

    public boolean deleteSong (String title) {
        return this.songs.deleteSong(title);
    }

    public void printSongList () {
        System.out.println(title);
        System.out.println("===========================");
        System.out.println("Title\t\t\t\tDuration");
        ListIterator<Song> iterator = this.songs.tracks.listIterator();
        int position = 1;
        while (iterator.hasNext()) {
            System.out.print(position + ". ");
            iterator.next().printSong();
            position++;
        }
        System.out.println("===========================");
    }
}
