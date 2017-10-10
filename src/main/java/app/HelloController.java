package app;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/api")
public class HelloController {
//
//	@Autowired
//	PersonRepository personRepo;
//
//	@Autowired
//	TeamRepository teamRepo;
//	
//	@Autowired
//	HelloProperties props;
//	
//    @RequestMapping("/greetings")
//    public String index() {
//    	return "Greetings from Spring Boot! ";
//    }
//    
//    @RequestMapping(value = "/salutaBilbo", method = RequestMethod.GET)
//    public String salutaBilbo() {
//    	Person person;
//    	List<Person> persons = personRepo.findByLastName("Baggins");
//    	if (persons.isEmpty())
//			person = personRepo.save(new Person("Bilbo","Baggins"));
//    	else
//			person = persons.get(0);
//        
//    	return "Greetings from Spring Boot! " + person.toString();
//    }
//
//    @RequestMapping(value = "/provaPost", method = RequestMethod.POST)
//    @ResponseBody
//    public Person provaPost(@RequestBody Person p) {
//    	List<Person> persons = personRepo.findByLastName(p.getLastName());
//    	if(persons.isEmpty())
//    		return null;
//    	return persons.get(0);
//    }
//    
//    @RequestMapping(value = "/provaProp", method = RequestMethod.GET)
//	public String provaProp(@RequestParam String name) {
//		return props.getGreeting() + name;
//	}
//
//    @RequestMapping(value = "/creaPersonaConTeam", method = RequestMethod.POST)
//    public Person creaPersonaConTeam(@RequestBody Person p) {
//    	Team team = teamRepo.save(new Team("Juve", "Torino"));
//    	
//    	p.setSupportTeam(team);
//    	
//    	Person save = personRepo.save(p);
//		return save;
//    	
//    	
//    }
//
//    @RequestMapping(value = "/creaPersonaConPhones", method = RequestMethod.POST)
//    public Person creaPersonaConPhones(@RequestBody Person p) {
//    	Phone p1 = new Phone("pri", "341");
//    	Phone p2 = new Phone("sec", "342");
//    	Phone p3 = new Phone("ter", "343");
//    	List<Phone> list = new ArrayList<>();
//    	list.add(p1);
//    	list.add(p2);
//    	list.add(p3);
//    	
//    	p.setPhoneList(list);
//    	
//    	Person save = personRepo.save(p);
//    	return save;
//    	
//    	
//    }
//    @RequestMapping(value = "/provaQuery", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Person> provaQuery(String name, String surname) {
//    	List<Person> persons = personRepo.findDistinctPeopleByFirstNameOrLastNameIgnoreCase(name, surname);
//    	
//    	return persons;
//    }


}