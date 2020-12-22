
import java.util.ArrayList;
import java.util.Scanner;


public class Collection {

    private Scanner scanner = new Scanner (System.in);
    private String name;
    private ArrayList<Album> albums;

    public Collection (String name) {
        this.name = name;
        this.albums = new ArrayList<>();
    }

    public String getName () {
        return this.name;
    }

    public ArrayList<Album> getAlbums () {
        return this.albums;
    }

    public boolean addAlbum (String title) {
        if (findAlbum(title) == null) {
            albums.add(new Album(title));
            System.out.println(title + " added.");
            return true;
        }
        System.out.println("Album with that name already exists. Action failed.");
        return false;
    }

    public boolean deleteAlbum (String title) {
        Album albumForDeleting = findAlbum(title);
        if (albumForDeleting != null) {
            this.albums.remove(albumForDeleting);
            System.out.println(title + " deleted.");
            return true;
        }
        System.out.println("Album with that title not found in this collection. Action failed.");
        return false;
    }

    private Album findAlbum (String title) {
        int index = -1;
        for (int i=0; i<this.albums.size(); i++) {
            if (this.albums.get(i).getTitle().toLowerCase().equals(title.toLowerCase())) {
                index = i;
            }
        }
        if (index>=0) {
            return this.albums.get(index);
        }
        return null;
    }

    public void printAlbums () {
        System.out.println("Albums in this collection: ");
        for (int i=0; i<this.albums.size(); i++) {
            System.out.println((i+1) + ". " + this.albums.get(i).getTitle());
        }
        System.out.println("===========================");
    }

    public Song getSong (String aTitle, String sTitle) {
        Album foundAlbum = findAlbum(aTitle);
        if (foundAlbum != null) {
            return foundAlbum.findSong(sTitle);
        }
        System.out.println("Album not found. Action failed.");
        return null;
    }

    public boolean addSong (String aTitle) {
        Album foundAlbum = findAlbum(aTitle);
        if (foundAlbum != null) {
            return foundAlbum.addSong();
        }
        System.out.println("Album not found. Action failed.");
        return false;
    }

    public boolean addSong (String aTitle, String sTitle) {
        Album foundAlbum = findAlbum(aTitle);
        if (foundAlbum != null) {
            return foundAlbum.addSong(sTitle);
        }
        System.out.println("Album not found. Action failed.");
        return false;
    }

    public void collectionMenu () {         // A menu for manually populating the collection.
        boolean quitF = false;

        while (!quitF) {
            System.out.println("Choose: (1)Add album, (2)Add song, (3)Print collection, (4)Print album, (5)Delete song, (6)Delete album, (7)Quit");
            int pick = scanner.nextInt();
            scanner.nextLine();
            switch (pick) {
                case 1:
                    System.out.print("Enter album's name: ");
                    String aName = scanner.nextLine();
                    addAlbum(aName);
                    break;
                case 2:
                    System.out.print("Select album: ");
                    aName = scanner.nextLine();
                    addSong(aName);
                    break;
                case 3:
                    printAlbums();
                    break;
                case 4:
                    System.out.print("Select album: ");
                    aName = scanner.nextLine();
                    Album foundAlbum = findAlbum(aName);
                    if(foundAlbum != null) {
                       foundAlbum.printSongList();
                    } else {
                        System.out.println("Album not found.");
                    }
                    break;
                case 5:
                    System.out.print("Select album: ");
                    aName = scanner.nextLine();
                    foundAlbum = findAlbum(aName);
                    if(foundAlbum != null) {
                        System.out.print("Enter song's name: ");
                        String sName = scanner.nextLine();
                        foundAlbum.deleteSong(sName);
                    } else {
                        System.out.println("Album not found.");
                    }
                    break;
                case 6:
                    System.out.print("Select album: ");
                    aName = scanner.nextLine();
                    deleteAlbum(aName);
                case 7:
                    quitF = true;
                    break;
            }
        }
    }
}
