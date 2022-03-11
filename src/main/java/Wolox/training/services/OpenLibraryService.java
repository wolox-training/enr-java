package wolox.training.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wolox.training.constants.OpenLibraryServiceConstants;
import wolox.training.exceptions.BookNotFoundException;
import wolox.training.models.daos.BookInfoDAO;
import wolox.training.models.dtos.BookInfoDTO;

import java.util.Objects;

import static wolox.training.constants.OpenLibraryServiceConstants.*;

@Service
public class OpenLibraryService implements IOpenLibrary{

    @Override
    public BookInfoDTO bookInfo(String isbn) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(URL_BASE + GET_BOOK_INFO_BY_ISBN + isbn + RESPONSE_FORMAT_JSON, String.class);

        if (Objects.equals(res, "{}")){
            throw new BookNotFoundException();
        }

        res = res.replace("ISBN:"+isbn, OPEN_LIBRARY_RESPONSE_KEY);

        BookInfoDAO bookInfoDAO = new ObjectMapper().readValue(res, BookInfoDAO.class);

        return BookInfoDTO.build(bookInfoDAO, isbn);
    }
}
