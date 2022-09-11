package restApi2;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StudentBean03Service {

	List<StudentBean03> listOfStudent = List.of(
	new StudentBean03(101L, "ali can", "ac@hmail.com", LocalDate.of(2000, 11, 5)),
			new StudentBean03(102L, "veli can", "vc@hmail.com", LocalDate.of(1996, 7, 15)),
			new StudentBean03(103L, "salih can", "sc@hmail.com", LocalDate.of(2005, 4, 23)));

	public StudentBean03 getStudentById(Long id) {
		if (!(listOfStudent.stream().filter(t -> t.getId()==id).collect(Collectors.toList()).isEmpty())) {
			return listOfStudent.stream().filter(t -> t.getId() == id).findFirst().get();
		}
		return new StudentBean03();
	}
	
	

	public List<StudentBean03> getAllStudent() {
		// TODO Auto-generated method stub
		return listOfStudent;
	}
}
