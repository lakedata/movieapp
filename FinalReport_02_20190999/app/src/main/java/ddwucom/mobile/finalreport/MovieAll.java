package ddwucom.mobile.finalreport;

import java.io.Serializable;

public class MovieAll implements Serializable {
    private int _id; //영화포스터이미지
    private String title;
    private String director;
    private String actors;
    private Float star;
    private String story;
    private String releaseDate;

    public MovieAll(int _id, String title, String director, String actors, Float star, String story, String releaseDate) {
        this._id = _id;
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.star = star;
        this.story = story;
        this.releaseDate = releaseDate;
    }

    public MovieAll(int _id, String title, String director) {
        this._id = _id;
        this.title = title;
        this.director = director;
    }

    public MovieAll(String title) {
        this.title = title;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
