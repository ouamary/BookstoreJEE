package controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import ejb.BookService;
import entities.Book;

@Named
@ViewScoped
public class BookBean extends EntityBean implements Serializable {
	@EJB
	private BookService bookService;
	
	public BookBean(){
	}
	
	public Book getBook(){
		System.out.println("getBook");
		return (Book)getEntity();
	}
	
	@PostConstruct
	public void init(){
		System.out.println("init");
		this.setService(bookService);
		this.setClazz(Book.class);
	}
	
	public StreamedContent getPhoto(){
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("bookId");
		/*UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("bookList:bookImage");
		Map attributes = component.getAttributes();
		String id = (String)attributes.get("bookId");*/
		
		if(id!=null && id.length()>0)
		{
			System.out.println(id);
			this.setEntityId(Long.parseLong(id));
		}
		Book book = this.getBook();
		InputStream input = null;
		if(book!=null)
		{
			byte[] byt = book.getPhoto();
			if(byt!=null && byt.length>0)
				input = new ByteArrayInputStream(byt);
			else
			{
				File f = new File("img/132x116.jpg");
			    byte[] phot = new byte[(int)(f.length())];
			    FileInputStream fi = null;
				try {
					fi = new FileInputStream(f);
					fi.read(phot);
					input = new ByteArrayInputStream(phot);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			    catch (IOException e) {
					e.printStackTrace();
				}
			}
		}			
    	return new DefaultStreamedContent(input, "image/jpeg");
	  }	
}
