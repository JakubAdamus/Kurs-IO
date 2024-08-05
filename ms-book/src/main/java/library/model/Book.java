package library.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String cover;
    @ManyToOne
    @JoinColumn(name = "writer_id")
    private Writer writer;
    private float rating;

    @ManyToMany
    @JoinTable(
            name = "Books_Libraries",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "library_id", referencedColumnName = "id")
    )

    private List<Library> libraries = new ArrayList<>();

    public Book(int id, String title, String cover, Writer writer, float rating) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.writer = writer;
        this.rating = rating;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    public void addLibrary(Library c) {
        this.libraries.add(c);
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", writer=" + writer +
                ", rating=" + rating +
                '}';
    }
}
