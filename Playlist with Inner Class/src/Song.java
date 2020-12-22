public class Song {

    private String title;
    private String duration;

    public Song (String title, String duration) {
        this.title = title;
        this.duration = duration;
    }

    public Song (String title) {
        this.title = title;
        this.duration = "00:00";
    }

    public String getTitle () {
        return title;
    }

    public void printSong () {
        System.out.println(this.title + "\t\t\t" + this.duration);
    }
}
