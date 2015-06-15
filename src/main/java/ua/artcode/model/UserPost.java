package ua.artcode.model;

import java.util.Date;

public class UserPost {

    private long id;
    private String title;
    private String body;
    private User owner;
    private Date creationDate;

    public UserPost() {
    }

    public UserPost(String title, String body, User owner, Date date) {
        this.title = title;
        this.body = body;
        this.owner = owner;
        this.creationDate = date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return  "Name: " + owner.getFullName() + " email: " + owner.getEmail() + "\n" +
                "Date: " + creationDate + "\n" +
                title + "\n" +
                body +
                "\n ==========";
    }

}
