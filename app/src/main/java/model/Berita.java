package model;

public class Berita {
    private String link;
    private String title;
    private String pubDate;
    private String description;
    private String thumbnail;

    public Berita() {
    }

    public Berita(String link, String title, String pubDate, String description, String thumbnail) {
        this.link = link;
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}

