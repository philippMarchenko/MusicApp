package com.devphill.musicapp.model;



public class Song {

    private static final String TAG = "Song";

    public long id;
    public String name;
    public String artistName;
    public long artistId;
    public String albumName;
    public long albumId;
    public long duration;
    public int year;
    public int dateAdded;
    public long playlistSongId;
    public long playlistSongPlayOrder;
    public int playCount;
    public long lastPlayed;
    public long startTime;
    private long elapsedTime = 0;
    private boolean isPaused;
    public int track;
    public int discNumber;
    public boolean isPodcast;
    public String path;
    public int bookMark;

    public String albumArtistName;

    private String durationLabel;
    private String bitrateLabel;
    private String sampleRateLabel;
    private String formatLabel;
    private String trackNumberLabel;
    private String discNumberLabel;
    private String fileSizeLabel;

    private String artworkKey;
    private String sortKey;

}