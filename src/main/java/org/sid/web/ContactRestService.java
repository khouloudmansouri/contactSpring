package org.sid.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import org.sid.dao.ContactRepository;
import org.sid.entites.Contact;

@RestController
public class ContactRestService {
	
	@Autowired
	private ContactRepository ContactRepository ;
	
	@RequestMapping(value="/contacts",method= RequestMethod.GET)
	public List<Contact> GetlistContact(){
		return ContactRepository.findAll();
		
	};
	
	@RequestMapping(value="/contacts/{id}",method= RequestMethod.GET)
	public Optional<Contact> GetContact(@PathVariable Long id){
		return ContactRepository.findById(id);
		
	};
	
	@RequestMapping(value="/contacts",method= RequestMethod.POST)
	public Contact save(@RequestBody Contact contact){
		return ContactRepository.save(contact);
		
	};
	
	@RequestMapping(value="/contacts/{id}",method= RequestMethod.DELETE)
	public boolean SupprimerContact(@PathVariable Long id){
		ContactRepository.deleteById(id);
		return true;
		
	};
	
	@RequestMapping(value="/Recherchercontacts",method= RequestMethod.GET)
	public Page<Contact> ChercherContact
	(@RequestParam(name="m", defaultValue="") String m, 
	 @RequestParam(name="page", defaultValue="0")	int page, 
	 @RequestParam(name="size", defaultValue="5")int size){
		return ContactRepository.chercher("%"+m+"%", new PageRequest(page,size));
		
	};
	
	@RequestMapping(value="/contacts/{id}",method= RequestMethod.PUT)
	public Contact Update(@PathVariable Long id, @RequestBody Contact contact){
		contact.setId(id);
		return ContactRepository.save(contact);
		
	};

}
