package bg.softuni.booksrestserver.service;

import bg.softuni.booksrestserver.models.entities.AuthorEntity;
import bg.softuni.booksrestserver.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<AuthorEntity> findAuthorByName(String authorName){
        return this.authorRepository.findByName(authorName);
    }

    public AuthorEntity saveNewAuthor(AuthorEntity authorEntity){
        this.authorRepository.save(authorEntity);
            return authorEntity;
    }
}
