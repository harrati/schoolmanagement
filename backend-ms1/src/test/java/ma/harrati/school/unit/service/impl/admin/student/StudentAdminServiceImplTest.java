package ma.harrati.school.unit.service.impl.admin.student;

import ma.harrati.school.bean.core.student.Student;
import ma.harrati.school.dao.facade.core.student.StudentDao;
import ma.harrati.school.service.impl.admin.student.StudentAdminServiceImpl;

import ma.harrati.school.bean.core.student.Payment ;
import ma.harrati.school.bean.core.school.School ;
import ma.harrati.school.bean.core.student.Student ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class StudentAdminServiceImplTest {

    @Mock
    private StudentDao repository;
    private AutoCloseable autoCloseable;
    private StudentAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllStudent() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveStudent() {
        // Given
        Student toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteStudent() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetStudentById() {
        // Given
        Long idToRetrieve = 1L; // Example Student ID to retrieve
        Student expected = new Student(); // You need to replace Student with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Student result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Student constructSample(int i) {
		Student given = new Student();
        given.setFirstName("firstName-"+i);
        given.setLastName("lastName-"+i);
        given.setPhoneNumber("phoneNumber-"+i);
        given.setRegistrationDate(LocalDateTime.now());
        given.setSchool(new School(1L));
        List<Payment> payments = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Payment element = new Payment();
                                                element.setId((long)id);
                                                element.setAmount(new BigDecimal(1*10));
                                                element.setPaymentDate(LocalDateTime.now());
                                                element.setStudent(new Student(Long.valueOf(3)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setPayments(payments);
        given.setPasswordChanged(false);
        given.setAccountNonLocked(false);
        given.setPassword("password-"+i);
        given.setEmail("email-"+i);
        given.setEnabled(false);
        given.setCredentialsNonExpired(false);
        given.setAccountNonExpired(false);
        given.setUsername("username-"+i);
        return given;
    }

}
