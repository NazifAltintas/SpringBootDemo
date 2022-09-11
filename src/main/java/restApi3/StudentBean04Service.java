package restApi3;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentBean04Service {

    StudentBean04Repository studentRepo;

    @Autowired
    public StudentBean04Service(StudentBean04Repository studentRepo) {
        this.studentRepo = studentRepo;
    }

    //for GET Request Method
    public StudentBean04 findStudentById(Long id) {
        if (studentRepo.findById(id).isPresent()) {
            return studentRepo.findById(id).get();
        }
        return new StudentBean04();
    }

    //for GET Request Method
    public List<StudentBean04> findAllStudents() {

        return studentRepo.findAll();


    }

    //for DELETE Request Method
    public String deleteStudentById(Long id) {
        if (!studentRepo.existsById(id)) {
            throw new IllegalStateException(id + " does not exist ");
        } else {
            studentRepo.deleteById(id);
            return "Student whose id is " + id + " is succesfully deleted";
        }

    }
	
		
		 /*

	For PUT Request Method

		 Logic to update name

		 1) If user does not send name, name must be null
		 2) If user sends the same name app should not do any update
		 3) If user sends different name app should update
		 4) I will select the student to update by id, if Id does not exist I should throw Exception

		 Logic to update email

		 1) If user sends existing email, throw exception because emails must be unique
		 2) If user does not send email throw Exception
		 3) If user sends email in invalid format (Must have @ sign) throw Exception 
		 4) If user sends same email no need to update
		 5) If user sends different email do update

		 Logic to update DOB

		 1) If the date is after current date throw Exception
		 2) If the date is different do update
		 3) If the date is same no need do update
		 
		 */

    public StudentBean04 fullyUpdateStudent(Long id, StudentBean04 newStudent) {

        StudentBean04 existingStudentById = studentRepo.findById(id).orElseThrow(() -> new IllegalStateException(id + " does not exist"));

        //To update name

        if (newStudent.getName() == null) {
            existingStudentById.setName(null);
        } else if (!existingStudentById.getName().equals(newStudent.getName())) {
            existingStudentById.setName(newStudent.getName());
        }

        //To update email

        Optional<StudentBean04> existingStudentByEmail = studentRepo.findStudentBean04ByEmail(newStudent.getEmail());
        if (existingStudentByEmail.isPresent()) {
            throw new IllegalStateException(newStudent.getEmail() + " exists. Emails must be unique...");
        } else if (newStudent.getEmail() == null) {
            throw new IllegalArgumentException("Email is mandatory to update data...");
        } else if (!newStudent.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email is invalid please use valid format...");
        } else if (!newStudent.getEmail().equals(existingStudentByEmail.get().getEmail())) {
            existingStudentById.setEmail(newStudent.getEmail());
        }


        //To update DOB
        if (newStudent.getDob() == null) {
            existingStudentById.setAge(existingStudentById.getAge());
        } else if (Period.between(newStudent.getDob(), LocalDate.now()).isNegative()) {
            throw new IllegalStateException("Date of birth cannot be greater than current date");
        } else if (!existingStudentById.getDob().equals(newStudent.getDob())) {
            existingStudentById.setDob(newStudent.getDob());
        }


        //To update Age

        existingStudentById.setAge(newStudent.getAge());

        //To update error message

        existingStudentById.setErrMsg("No error...");


        return studentRepo.save(existingStudentById);
    }

    public StudentBean04 addNewStudent(StudentBean04 newStudent) throws ClassNotFoundException, SQLException {
        Optional<StudentBean04> existingStudentByEmail = studentRepo.findStudentBean04ByEmail(newStudent.getEmail());

        if (existingStudentByEmail.isPresent()) {
            throw new IllegalStateException("email exist, make it unique....");
        }
        if (newStudent.getEmail() == null) {
            throw new IllegalStateException("without using email, new data cannot be created.");
        }
//to create connection with DB, look at jdbc
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nazif", "root", "nazif2022");
        Statement st = con.createStatement();

        String sqlQuery = "SELECT MAX(id) FROM students";
        ResultSet result = st.executeQuery(sqlQuery);

        Long maxId = 0L;

        while (result.next()) {
            maxId = result.getLong(1);
        }

        newStudent.setId(maxId + 1);
        newStudent.setAge(newStudent.getAge());
        newStudent.setErrMsg("No error...");

        return studentRepo.save(newStudent);


    }

    public StudentBean04 partiallyUpdateStudent(Long id, StudentBean04 newStudent) {
        StudentBean04 existingStudentById = studentRepo.findById(id).orElseThrow(() -> new IllegalStateException(id + " does not exist"));

        //To update name

        if (newStudent.getName() == null) {
            existingStudentById.setName(null);
        } else if (!existingStudentById.getName().equals(newStudent.getName())) {
            existingStudentById.setName(newStudent.getName());
        }

        //To update email

        Optional<StudentBean04> existingStudentByEmail = studentRepo.findStudentBean04ByEmail(newStudent.getEmail());
        if (existingStudentByEmail.isPresent()) {
            throw new IllegalStateException(newStudent.getEmail() + " exists. Emails must be unique...");
        } else if (newStudent.getEmail() == null) {
            throw new IllegalArgumentException("Email is mandatory to update data...");
        } else if (!newStudent.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email is invalid please use valid format...");
        } else if (!newStudent.getEmail().equals(existingStudentByEmail.get().getEmail())) {
            existingStudentById.setEmail(newStudent.getEmail());
        }


        //To update DOB
        if (newStudent.getDob() == null) {
            existingStudentById.setAge(existingStudentById.getAge());
        } else if (Period.between(newStudent.getDob(), LocalDate.now()).isNegative()) {
            throw new IllegalStateException("Date of birth cannot be greater than current date");
        } else if (!existingStudentById.getDob().equals(newStudent.getDob())) {
            existingStudentById.setDob(newStudent.getDob());
        }


        //To update Age

        existingStudentById.setAge(newStudent.getAge());

        //To update error message

        existingStudentById.setErrMsg("No error...");


        return studentRepo.save(existingStudentById);
    }
}




