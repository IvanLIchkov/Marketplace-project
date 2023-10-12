package bg.softuni.booksrestserver.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class BookEntity extends BaseEntity{

    private String title;

    private String isbn;

    @ManyToOne
    private AuthorEntity author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "titile='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
<<<<<<< HEAD
                ", author=" + (author != null ? author.getName() : null) +
=======
                ", author=" + author +
>>>>>>> origin/main
                '}';
    }
}
