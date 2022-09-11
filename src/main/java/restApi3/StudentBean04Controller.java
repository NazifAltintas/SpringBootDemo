package restApi3;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class StudentBean04Controller {

    @Autowired
    StudentBean04Service studentBean04Service;

    @GetMapping(path = "/findStudentById/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public StudentBean04 findStudentById(@PathVariable Long id) {

        return studentBean04Service.findStudentById(id);
    }

    @GetMapping(path = "/findAllStudents")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public List<StudentBean04> findAllStudents() {

        return studentBean04Service.findAllStudents();
    }

    @DeleteMapping(path = "/deleteStudent/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public String deleteStudentById(@PathVariable long id) {
        return studentBean04Service.deleteStudentById(id);
    }

    /*	@DeleteMapping("{id}")
        public ResponseEntity<Long> deletePost(@PathVariable("id") Long id) {

            var isRemoved = studentBean04Service.deleteStudentById(id);

            if (!isRemoved) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(id, HttpStatus.OK);
        }
     */
    @PutMapping(path = "/updateStudentFully/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public StudentBean04 fullyUpdateStudentById(@PathVariable Long id, @Validated @RequestBody StudentBean04 newStudent) {

        return studentBean04Service.fullyUpdateStudent(id, newStudent);
    }

    @PatchMapping(path = "/updateStudentPartially/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public StudentBean04 partiallyUpdateStudentById(@PathVariable Long id, @Validated @RequestBody StudentBean04 newStudent) {

        return studentBean04Service.partiallyUpdateStudent(id, newStudent);

    }
    @PostMapping(path = "/addNewStudent")
    @PreAuthorize("hasAuthority('student:write')")
    public StudentBean04 postStudent( @Validated @RequestBody StudentBean04 newStudent) throws SQLException, ClassNotFoundException {

        return studentBean04Service.addNewStudent(newStudent);

    }
}
