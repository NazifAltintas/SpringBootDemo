package restApi1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentBean01Controller {
	
  @RequestMapping (method = RequestMethod.GET,path="/getRequest") 
  @ResponseBody
  public String getMethod() {
		
		return "getMethod is working here";
	}

}
