package vttp2022.csf.backend.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class BookDetails {
    private String bookId;
    private String title;
    private String authors;
    private String description;
    private Float rating;
    private String genres;
    private String imageUrl;

    public String getBookId() {
        return bookId;
    }
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthors() {
        return authors;
    }
    public void setAuthors(String authors) {
        this.authors = authors;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Float getRating() {
        return rating;
    }
    public void setRating(Float rating) {
        this.rating = rating;
    }
    public String getGenres() {
        return genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static BookDetails create(SqlRowSet rs) {
        BookDetails b = new BookDetails();
        b.setBookId(rs.getString("book_id"));
        b.setTitle(rs.getString("title"));
        b.setAuthors(rs.getString("authors"));
        b.setDescription(rs.getString("description"));
        b.setRating(rs.getFloat("rating"));
        b.setGenres(rs.getString("genres"));
        b.setImageUrl(rs.getString("image_url"));
        return b;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("bookId", bookId)
            .add("title", title)
            .add("authors", authors)
            .add("description", description)
            .add("rating", rating)
            .add("genres", genres)
            .add("imageUrl", imageUrl)
            .build();
    }
}
