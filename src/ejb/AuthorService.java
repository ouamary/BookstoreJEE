package ejb;

import java.util.List;

import entities.Author;

public interface AuthorService extends GenericCRUDService<Author> {
	 public List<Author> getAuthors();
	 public Author getAuthorById(int id);
}
