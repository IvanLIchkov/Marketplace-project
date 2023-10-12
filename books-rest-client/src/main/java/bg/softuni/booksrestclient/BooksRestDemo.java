package bg.softuni.booksrestclient;

import bg.softuni.booksrestclient.model.dtos.BookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BooksRestDemo implements CommandLineRunner {

    private static final String API_URI = "http://localhost:8080/api/books";

    private final RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(BooksRestDemo.API_URI);

    public BooksRestDemo(RestTemplate restTemplate){

        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<BookDto[]> allBooksResponse =
        restTemplate.getForEntity(API_URI, BookDto[].class);


        if ((allBooksResponse.hasBody())){
            BookDto[] booksResponseBody = allBooksResponse.getBody();

            for (BookDto bookDto : booksResponseBody) {
                LOGGER.info("Retrieved a book: {}", bookDto);
            }

        }
    }
}
