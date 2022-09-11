package restApi2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean03Controller {
	
	@Autowired
	StudentBean03Service std;
	
	@GetMapping(path="/getStudent/{id}")
	public StudentBean03 getStudentById(@PathVariable Long id) {
		StudentBean03 student= std.getStudentById(id);
		return student;
	}
	
	@GetMapping(path="/getStudents")
	public List<StudentBean03> getStudents() {
		return std.getAllStudent();
	}

}
