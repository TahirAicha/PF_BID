package dossier_medical.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PatientDaoService {
	private static List<Patient> patients = new ArrayList<>();
	private static int usersCount=3;
	static {
		patients.add(new Patient(1,"Adam", new Date()));
		patients.add(new Patient(2,"Eve", new Date()));
		patients.add(new Patient(3,"Jack", new Date()));
	}
	
	
	public List<Patient> findAll(){
		return patients;
	}
	
	
	
	public Patient save(Patient patient) {
		if(patient.getId()==null) {
			patient.setId(++usersCount);
		}
		patients.add(patient);
		
		return patient;
	}
	
	
	public Patient findOne(int id) {
		for(Patient patient:patients) {
			if(patient.getId()==id) {
				return patient;
			}
			
		}
		return null;
		
	}
	public Patient deleteById(int id) {
		Iterator<Patient> iterator =patients.iterator();
		while(iterator.hasNext() ) {
			Patient patient = iterator.next();
			if(patient.getId()==id) {
				iterator.remove();
				return patient;
			}
		}
		
		return null;
		
	}
	
}
