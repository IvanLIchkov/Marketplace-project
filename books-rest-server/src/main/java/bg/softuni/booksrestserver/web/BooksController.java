package bg.softuni.booksrestserver.web;

import bg.softuni.booksrestserver.models.dtos.BookDto;
import bg.softuni.booksrestserver.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId){
        Optional<BookDto> theBook = this.bookService.findBookById(bookId);

        return theBook.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBookById(@PathVariable("id") Long bookId){
        this.bookService.deleteBookById(bookId);

        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto newBook,
                                              UriComponentsBuilder uriComponentsBuilder){
        Long newBookId = bookService.createBook(newBook);

        return ResponseEntity.created(uriComponentsBuilder
                .path("api/books/{id}").build(newBookId)).build();
    }
}
