package bg.softuni.booksrestserver.models.entities;

import jakarta.persistence.Entity;
<<<<<<< HEAD
=======
import jakarta.persistence.ManyToOne;
>>>>>>> origin/main
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "authors")
public class AuthorEntity extends BaseEntity{

    private String name;

    @OneToMany(mappedBy = "author")
    private List<BookEntity> books;

    public String getName() {
        return name;
    }

<<<<<<< HEAD
    public AuthorEntity setName(String name) {
        this.name = name;
        return this;
=======
    public void setName(String name) {
        this.name = name;
>>>>>>> origin/main
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
