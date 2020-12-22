import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Playlist {

    private Scanner scanner = new Scanner (System.in);
    private String name;
    private Collection myCollection;
    private LinkedList<Song> myPlaylist;
    private boolean skippedForward;



    public Playlist (String name, Collection myCollection) {
        this.name = name;
        this.myCollection = myCollection;
        this.myPlaylist = new LinkedList<>();
        this.skippedForward = true;
    }

    public boolean addSong (String aTitle, String sTitle) {
        Song foundSong = this.myCollection.getSong(aTitle,sTitle);
        if (foundSong != null) {
            this.myPlaylist.add(foundSong);
            System.out.println(foundSong.getTitle() + " added to " + this.name + " playlist.");
            return true;
        }
        System.out.println("Song not found in " + this.myCollection.getName());
        return false;
    }

    public boolean addSong (String sTitle) {
        int index = -1;
        int songCount = 0;
        for (int i=0; i<this.myCollection.getAlbums().size(); i++) {
            Album searchedAlbum = this.myCollection.getAlbums().get(i);
            Song foundSong = searchedAlbum.findSong(sTitle);
            if (foundSong != null) {
                index = i;
                songCount++;
            }
        }
        if (songCount <= 1) {
            return addSong(this.myCollection.getAlbums().get(index).getTitle(), sTitle);
        }
        System.out.println("More than one song with this name in the collection. Action failed.");
        return false;
    }

    public boolean deleteSong (String sTitle) {
        ListIterator<Song> iterator = this.myPlaylist.listIterator();
        while (iterator.hasNext()) {
            Song songForDeleting = iterator.next();
            if (songForDeleting.getTitle().toLowerCase().equals(sTitle.toLowerCase())) {
                System.out.println("Deleting song: " + songForDeleting.getTitle());
                this.myPlaylist.remove(songForDeleting);
                return true;
            }
        }
        System.out.println("Song with this title not found in your playlist. Action failed.");
        return false;
    }

    public void printPlaylist () {
        ListIterator<Song> iterator = this.myPlaylist.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }
    }

    public void playlistMenu () {                       // A menu to play the songs in the playlist. This functionality was the main goal
        ListIterator<Song> iterator = this.myPlaylist.listIterator();               // of the challenge.
        boolean quitF = false;

        while (!quitF) {
            System.out.println("Choose: (1)Next, (2)Previous, (3)Repeat, (4)Print playlist, (5)Quit");
            int pick = scanner.nextInt();
            switch (pick) {
                case 1:
                    if (!this.skippedForward) {
                        iterator.next();
                    }
                    if (iterator.hasNext()) {
                        Song currentSong = iterator.next();
                        System.out.print("Now playing --> ");
                        currentSong.printSong();
                    } else {
                        System.out.println("End of playlist");
                    }
                    this.skippedForward = true;

                    break;
                case 2:
                    if (this.skippedForward) {
                        if (iterator.hasPrevious()) {
                            iterator.previous();
                        }
                    }
                    if (iterator.hasPrevious()) {
                        Song currentSong = iterator.previous();
                        System.out.print("Now playing --> ");
                        currentSong.printSong();
                    } else {
                        System.out.println("Start of playlist");
                    }
                    this.skippedForward = false;
                    break;
                case 3:
                    Song currentSong;
                    if (this.skippedForward) {
                        currentSong = iterator.previous();
                        this.skippedForward = false;
                    } else {
                        currentSong = iterator.next();
                        this.skippedForward = true;
                    }
                    System.out.print("Now playing --> ");
                    currentSong.printSong();
                    break;
                case 4:
                    printPlaylist();
                    break;
                case 5:
                    quitF = true;
                    break;
            }
        }
    }

    public void createPlaylistMenu () {                     // A menu to manually create the playlist from the populated Collection.
        boolean quitF = false;

        while (!quitF) {
            System.out.println("Choose: (1)Add song, (2)Delete song, (3)Print playlist, (4)Quit");
            int pick = scanner.nextInt();
            scanner.nextLine();
            switch (pick) {
                case 1:
                    System.out.print("Enter song's name: ");
                    String sName = scanner.nextLine();
                    if (!addSong(sName)) {
                        System.out.print("Enter album's name: ");
                        String aName = scanner.nextLine();
                        addSong(aName,sName);
                    }
                    break;
                case 2:
                    System.out.print("Enter song's name: ");
                    sName = scanner.nextLine();
                    deleteSong(sName);
                    break;
                case 3:
                    printPlaylist();
                    break;
                case 4:
                    quitF = true;
                    break;
            }
        }
    }
}

