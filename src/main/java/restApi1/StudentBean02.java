package restApi1;

import org.springframework.stereotype.Component;

@Component
public class StudentBean02 implements StudentInterface{
	private String name;
	private int age;
	private String id;
	
	
	public StudentBean02() {
		// TODO Auto-generated constructor stub
		System.out.println("student02 bean without parameters");
	}
	
	public StudentBean02(String name, int age, String id) {
		
		this.name = name;
		this.age = age;
		this.id = id;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "StudentBean01 [name=" + name + ", age=" + age + ", id=" + id + "]";
	}

	@Override
	public String study() {
		// TODO Auto-generated method stub
		return  "it comes from studentbean02";
	}

}
