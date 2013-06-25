package entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity()
@NamedQueries({
@NamedQuery(name = "Book.findAll", query = "Select b From Book b"),
@NamedQuery(name = "Book.findLikeOnTitle", query = "Select b From Book b where b.title like :like"),
@NamedQuery(name = "Book.findByCategory", query = "Select b From Book b where b.category = :category")
})

@XmlRootElement
public class Book extends Persistent {
  private String title = "";
  private Category category;
  private String olid;
  private java.math.BigDecimal price = new BigDecimal(0);;
  private java.util.Date date = new Date();
  private List<Author> authors;
  private byte[] photo;
  private List<OrderItem> orderItems = new ArrayList();;

  public Book() {
    authors = new ArrayList();

  }

  public boolean equals(Object other) {
    if (other != null && other instanceof Book)
      return getTitle().equals(((Book) other).getTitle());
    return false;
  }

  public int hashCode() {
    return getTitle().hashCode();
  }

  public String toString() {
    return "Id:" + getId() + " Title:" + getTitle() + " Price:" + getPrice() + " Category:" + getCategory()
        + " Authors:" + getAuthors();
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @ManyToOne()
  public Category getCategory() {
    return this.category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getOlid() {
	return olid;
}

public void setOlid(String olid) {
	this.olid = olid;
}

public java.math.BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(java.math.BigDecimal price) {
    this.price = price;
  }

  @Temporal(TemporalType.DATE)
  public java.util.Date getDate() {
    return this.date;
  }

  public void setDate(java.util.Date date) {
    this.date = date;
  }

  @ManyToMany
  // @JoinTable(name = "BookAuthor", joinColumns = { @JoinColumn(name =
  // "bookId") }, inverseJoinColumns = { @JoinColumn(name = "authorId") })
  public List<Author> getAuthors() {
    return this.authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public void addAuthor(Author author) {
    authors.add(author);
  }

  public void removeAuthor(Author author) {
    authors.remove(author);
  }

  @Lob
  @Column(length = 100000)
  public byte[] getPhoto() {
    return this.photo;
  }

  public void setPhoto(byte[] photo) {
    this.photo = photo;
  }

  @OneToMany(mappedBy = "book")
  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }
  
  public int retourneElemDate(int elem){/*1 : jour, 2 : mois, 3: annee*/
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(this.date);
	  int nb =0;
	  switch(elem){
	  case 1 : nb = cal.get(Calendar.DAY_OF_MONTH);
	  	break;
	  case 2 : nb = cal.get(Calendar.MONTH)+1;
	  	break;
	  case 3 :nb = cal.get(Calendar.YEAR);
	  break;
	  }  
	  return nb;
  }

}
