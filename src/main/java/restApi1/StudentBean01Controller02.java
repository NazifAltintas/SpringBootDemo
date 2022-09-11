package restApi1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean01Controller02 {
	
	@Autowired
	@Qualifier(value="studentBean01")
	StudentInterface std1; 
	
	
	
	@GetMapping(path="/getStudy2")
	
	public String getStudy() {

		return std1.study();
	}

}
