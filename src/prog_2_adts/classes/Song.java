package prog_2_adts.classes;

import prog_2_adts.src.uy.edu.um.adt.linkedlist.MyList;

import java.time.LocalDate;

class Song {
    private String spotifyId;
    private String name;
    private MyList<Artist> artists;
    private float tempo;
    private LocalDate snapshotDate;
    private Integer dailyRank;

    public Song(String spotifyId, String name, MyList<Artist> artists, float tempo, LocalDate snapshotDate, Integer dailyRank) {
        this.spotifyId = spotifyId;
        this.name = name;
        this.artists = artists;
        this.tempo = tempo;
        this.snapshotDate = snapshotDate;
        this.dailyRank = dailyRank;
    }

    public Integer getDailyRank() {
        return dailyRank;
    }

    public void setDailyRank(Integer dailyRank) {
        this.dailyRank = dailyRank;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(MyList<Artist> artists) {
        this.artists = artists;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    public LocalDate getSnapshotDate() {
        return snapshotDate;
    }

    public void setSnapshotDate(LocalDate snapshotDate) {
        this.snapshotDate = snapshotDate;
    }
}
