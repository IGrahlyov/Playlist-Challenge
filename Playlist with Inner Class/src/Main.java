import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Collection mineC = new Collection("mineC");
        Playlist newPlaylist = new Playlist("New", mineC);
        boolean flag =true;

        while (flag) {                      // Choose if you want to add the songs and albums manually or test with predefined ones.
            System.out.println("Choose (1)Auto-populate to test functionality, (2)Populate manually");
            int fPick = scanner.nextInt();
            switch (fPick) {
                case 1:

                    mineC.addAlbum("Album 1");
                    mineC.addAlbum("Album 2");
                    mineC.addAlbum("Album 3");
                    mineC.addAlbum("Album 4");
                    mineC.printAlbums();

                    mineC.addSong("Album 1", "A01S01");
                    mineC.addSong("Album 1", "A01S02");
                    mineC.addSong("Album 1", "A01S03");

                    mineC.addSong("Album 2", "A02S01");
                    mineC.addSong("Album 2", "A02S02");
                    mineC.addSong("Album 2", "A02S03");

                    mineC.addSong("Album 3", "A03S01");
                    mineC.addSong("Album 3", "A03S02");
                    mineC.addSong("Album 3", "A03S03");

                    mineC.addSong("Album 4", "A04S01");
                    mineC.addSong("Album 4", "A04S02");
                    mineC.addSong("Album 4", "A04S03");


                    newPlaylist.addSong("A01S03");
                    newPlaylist.addSong("A01S02");
                    newPlaylist.addSong("A03S02");
                    newPlaylist.addSong("A04S01");
                    newPlaylist.addSong("A02S02");
                    newPlaylist.addSong("A01S01");

                    newPlaylist.playlistMenu();
                    flag=false;
                    break;

                case 2:

                    boolean qFlag = false;
                    while (!qFlag) {
                        System.out.println("Pick an option: (1)Collection menu, (2)Create playlist menu (3)Play playlist, (4)Quit");
                        int pick = scanner.nextInt();
                        switch (pick) {
                            case 1:
                                mineC.collectionMenu();
                                break;
                            case 2:
                                newPlaylist.createPlaylistMenu();
                                break;
                            case 3:
                                newPlaylist.playlistMenu();
                                break;
                            case 4:
                                qFlag = true;
                                break;
                        }
                    }
                    flag=false;
                    break;
            }
        }
    }
}


