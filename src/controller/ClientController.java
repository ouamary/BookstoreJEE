package controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotation.LoggedIn;

import ejb.ClientService;
import entities.Client;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ClientController implements Serializable {

	//private static Logger log = LoggerFactory.getLogger(ClientController.class);
		
	@EJB 
	private ClientService clientService;
	
	@Inject
	private LoginForm loginForm;

	@Inject
	private RegisterForm registerForm;
	
	@Inject
	private MessageBean messageBean;
	
	private Client currentClient;
	
	@Produces @LoggedIn @Named  
	public Client getCurrentClient() {
		return currentClient;
	}

	public String doLogin() {
		currentClient = clientService.login(loginForm.getLogin(), loginForm.getPassword());
		if(currentClient == null){
			messageBean.addMessage("clientNotFound");
			return null;
		}	
		return "welcome";
	}

	public boolean isLoggedIn() {
		return currentClient != null;
	}

	public String doLogout() {
		currentClient = null;
		return "login";
	}
	
	public String register() {
		currentClient = clientService.register(registerForm.getLogin(), registerForm.getPassword());
		if(currentClient == null){
			return null;
		}
		return "welcome";
	}
	
	public String updateProfile(){		
		currentClient.setLogin(currentClient.getLogin());
		currentClient.setPassword(currentClient.getPassword());
		currentClient.setNom(currentClient.getNom());
		currentClient.setPrenom(currentClient.getPrenom());
		currentClient.setEmail(currentClient.getEmail());
		currentClient.setAdresse(currentClient.getAdresse());
		currentClient.setVille(currentClient.getVille());
		currentClient.setZipcode(currentClient.getZipcode());
		
		clientService.update(currentClient);
		if(currentClient == null){
			return null;
		}
		return "showProfile";
	}
	
	public String goToMyProfile(){
		return "showProfile";
	}
	
	public String goToLogin(){
		return "login";
	}
	
	public String seeMyOrders(){
		return "mesCommandes";
	}
}
