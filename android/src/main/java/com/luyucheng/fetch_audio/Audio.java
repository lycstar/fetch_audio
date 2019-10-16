package com.luyucheng.fetch_audio;

import java.util.HashMap;

/**
 * @author Luyucheng
 * @package com.luyucheng.fetch_audio
 * @date 2019/10/15 22:16
 * @desc
 */
public class Audio {
    private long id;
    private long albumId;
    private String title;
    private String album;
    private String thumbUrl;
    private String artist;
    private String displayName;
    private String url;
    private String year;
    private long duration;
    private long size;

    Audio() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    HashMap<String, Object> toMap() {
        HashMap<String, Object> audioMap = new HashMap<>();
        audioMap.put("id", id);
        audioMap.put("albumId", albumId);
        audioMap.put("title", title);
        audioMap.put("album", album);
        audioMap.put("thumbUrl", thumbUrl);
        audioMap.put("artist", artist);
        audioMap.put("displayName", displayName);
        audioMap.put("year", year);
        audioMap.put("url", url);
        audioMap.put("duration", duration);
        audioMap.put("size", size);
        return audioMap;
    }
}
