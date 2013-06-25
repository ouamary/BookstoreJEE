package controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotation.LoggedIn;
import ejb.AuthorService;
import entities.Author;


@Named
@SessionScoped
public class AuthorController implements Serializable {

	private static Logger log = LoggerFactory.getLogger(AuthorController.class);
	
	@EJB
	private AuthorService authorService;
		
	private Author currentAuthor;
	
	@Produces @LoggedIn @Named  
	public Author getCurrentAuthor() {
		return currentAuthor;
	}
	
	public List<Author> getList(){
		List<Author> list = authorService.findAll();
		Collections.sort(list);
		return list;
	}
	
	public List<Author> getAuthors(){
		return authorService.getAuthors();
	}
	
	public Author getAuthorById(int id){
		return authorService.getAuthorById(id);
	}
	
	public String displayAuthor(Long id){
		currentAuthor = authorService.find(id);
		return "author";
	}
}
