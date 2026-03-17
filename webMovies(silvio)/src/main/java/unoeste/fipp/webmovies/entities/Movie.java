package unoeste.fipp.webmovies.entities;

public class Movie {
    private String title;
    private String year;
    private String category;
    private String poster;


    public Movie(String title, String year, String category) {
        this.title = title;
        this.year = year;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
