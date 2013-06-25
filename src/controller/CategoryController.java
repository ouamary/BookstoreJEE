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
import ejb.CategoryService;
import entities.Category;


@Named
@SessionScoped
public class CategoryController implements Serializable {

	private static Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	@EJB
	private CategoryService categoryService;
		
	private Category currentCat;
	
	@Produces @LoggedIn @Named  
	public Category getCurrentCat() {
		return currentCat;
	}
	
	public List<Category> getList(){
		List<Category> list = categoryService.findAll();
		Collections.sort(list);
		return list;
	}
	
	public List<Category> getCategories(){
		return categoryService.getCategories();
	}
	
	public Category getCategoryById(int id){
		return categoryService.getCategoryById(id);
	}
	
	public String displayCategory(Long id){
		currentCat = categoryService.find(id);
		return "category";
	}
}
