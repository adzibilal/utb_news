package model;


import java.util.List;

public class Data {
    private String link;
    private String description;
    private String title;
    private String image;
    private List<Post> posts;

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public List<Post> getPosts() {
        return posts;
    }
}


