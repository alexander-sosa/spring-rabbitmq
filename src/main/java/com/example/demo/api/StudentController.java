package com.example.demo.api;

import com.example.demo.bl.QueueBl;
import com.example.demo.bl.StudentBl;
import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jrojas
 */
@RestController
@RequestMapping("/v1/api/students")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
    private final StudentBl studentBl;
    private final QueueBl queueBl;

    @Value("${key}")
    String key;

    @Autowired
    public StudentController(StudentBl studentBl, QueueBl queueBl) {
        this.studentBl = studentBl;
        this.queueBl = queueBl;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        // Create a new student
        LOGGER.info("Creando estudiante con la siguiente información: {}", student);
        Student result = studentBl.saveStudent(student);

        LOGGER.info(queueBl.sendWithDirectExchange(result));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentsById(@PathVariable Long id) {
        // Get a specific student
        LOGGER.info("Obteniendo el estudiante con id: {}", id);

        Student student = studentBl.getStudentById(id);
        LOGGER.info(queueBl.sendWithFanoutExchange(student));
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getStudents() {
        // General list
        LOGGER.info("Listado general con KEY: {}", key);

        List<Student> studentList = studentBl.getStudents();
        LOGGER.info(queueBl.sendWithTopicExchange1(studentList.get(0)));
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @RequestMapping(value = "/paginate", method = RequestMethod.GET)
    public ResponseEntity<PageImpl<StudentDto>> getStudentsPaginate(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        // Paginated list
        LOGGER.warn("Get Students Paginate");
        LOGGER.info("Invocando al servicio REST para obtener el listado de estudiantes con KEY: {}", key);

        PageImpl<StudentDto> studentList = studentBl.getStudentsPaginate(page, size);
        LOGGER.info("Invocacion exitosa para obtener el listado de estudiantes {}", studentList);
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

}
