package dossier_medical.patient;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.net.URI;

import java.util.List;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


//User Resource
@RestController
public class PatientController {
	@Autowired
	private PatientDaoService service;
	
	
	
	@GetMapping("/users")
	public List<Patient> retreiveAllUsers(){
		return service.findAll();
	}
	
	
	@GetMapping("/users/{id}")
	public Resource<Patient> retreiveUser(@PathVariable int id) {
		Patient patient =service.findOne(id);
		if(patient==null) {
			throw new PatientNotFoundException("id: "+ id );
			
			
		}
		Resource<Patient> resource = new Resource<Patient>(patient);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retreiveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
				
				
	}
	@DeleteMapping("/users/{id}")
	public Patient deleteUser(@PathVariable int id) {
		Patient patient =service.deleteById(id);
		if(patient==null) {
			
			
			throw new PatientNotFoundException("id: "+ id );
			
		}
		return patient;
		
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Patient patient) {
		 Patient savedUser = service.save(patient);
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		 return ResponseEntity.created(location).build();
		 
	 }
	
	
	
	
	

}
