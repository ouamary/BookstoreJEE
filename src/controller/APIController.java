package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;

import annotation.LoggedIn;
import ejb.BookService;
import entities.Book;

@Named
@SessionScoped
public class APIController implements Serializable {

	@EJB
	private BookService bookService;

	private String status;

	@Produces
	@LoggedIn
	@Named
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Book> getBooks() {
		return bookService.findAll();
	}

	public String getBookCovers() {
		URL url;

		try {

			for (Book b : getBooks()) {
				url = new URL("http://covers.openlibrary.org/b/olid/"
						+ b.getOlid() + "-L.jpg");
				URLConnection conn = url.openConnection();

				InputStream is = conn.getInputStream();
				b.setPhoto(IOUtils.toByteArray(is));
				bookService.update(b);
			}
			
			setStatus("OK");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
