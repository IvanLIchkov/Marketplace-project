package bg.softuni.booksrestserver.service;

import bg.softuni.booksrestserver.models.dtos.BookDto;
import bg.softuni.booksrestserver.models.entities.AuthorEntity;
import bg.softuni.booksrestserver.models.entities.BookEntity;
import bg.softuni.booksrestserver.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper mapper;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, ModelMapper mapper, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
        this.authorService = authorService;
    }

    public Optional<BookDto> findBookById(Long bookId){
        return this.bookRepository.findById(bookId)
                .map(bookEntity -> this.mapper.map(bookEntity, BookDto.class));
    }

    public List<BookDto> getAllBooks(){
        return this.bookRepository.findAll()
                .stream()
                .map(bookEntity -> this.mapper.map(bookEntity, BookDto.class))
                .collect(Collectors.toList());
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Long createBook(BookDto newBook){
        BookEntity bookToPersist = this.mapper.map(newBook, BookEntity.class);
        Optional<AuthorEntity> authorOpt = this.authorService.findAuthorByName(newBook.getAuthor().getName());

        bookToPersist.setAuthor(authorOpt.orElseGet(() -> createNewAuthor(newBook.getAuthor().getName())));

        Long id = bookRepository.save(bookToPersist).getId();
        return id;
    }

    private AuthorEntity createNewAuthor(String authorName){
        return this.authorService.saveNewAuthor(new AuthorEntity().setName(authorName));
    }

}
