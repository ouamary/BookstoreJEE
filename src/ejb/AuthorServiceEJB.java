package ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entities.Author;

@Stateless
@Local(AuthorService.class)
public class AuthorServiceEJB extends GenericCRUDServiceEJB<Author> implements AuthorService{

	public List<Author> getAuthors(){
		Query query = em.createQuery("select a from Author a");
		List<Author> authors = query.getResultList();
		return authors;		
	}
	
	public Author getAuthorById(int id){
		Author author = null;
		try {
			author = (Author)em.createQuery("select a from Author a WHERE a.id=:id").setParameter("id",id).getSingleResult();
		}
		catch (NoResultException e) {
			    System.out.println(e.getMessage());
		}
		catch (Exception e) {
			    System.out.println(e.getMessage());
		}
		return author;
	}
}
