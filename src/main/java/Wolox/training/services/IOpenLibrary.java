package wolox.training.services;

import wolox.training.models.daos.BookInfoDAO;
import wolox.training.models.dtos.BookInfoDTO;

public interface IOpenLibrary {
    BookInfoDTO bookInfo(String isbn) throws Exception;
}
