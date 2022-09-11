package restApi1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean01Controller01 {
	
	@Autowired
	StudentBean01 std;
	
	@Autowired
	StudentBean02 std2;
	
	@GetMapping(path="/getStudent")
	public StudentBean01 getStudent() {
		
		std.setAge(45);
		std.setId("DFG125");
		std.setName("Jack");
		
		return std;
	}
	
	@GetMapping(path="/getStudentParameterized/{school}")
	public StudentBean01 getStudentParameterized(@PathVariable String school) {
		
		std.setAge(14);
		std.setId(String.format("D%sKL8M5", school));
		std.setName("Quebeck");
		
		return std;
	

}
	
	@GetMapping
	public String getMain() {
		return "get request without path and it will show in home page";
	}

	@GetMapping(path="/getStudy")
	public String getInterfaceMethod() {
		return std.study();
	}
	
	@GetMapping(path="/getStudy1")
	public String getInterfaceMethod1() {
		return std2.study();
	}
}
