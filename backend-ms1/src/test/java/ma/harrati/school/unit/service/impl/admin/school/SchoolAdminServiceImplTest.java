package ma.harrati.school.unit.service.impl.admin.school;

import ma.harrati.school.bean.core.school.School;
import ma.harrati.school.dao.facade.core.school.SchoolDao;
import ma.harrati.school.service.impl.admin.school.SchoolAdminServiceImpl;

import ma.harrati.school.bean.core.school.Professor ;
import ma.harrati.school.bean.core.school.School ;
import ma.harrati.school.bean.core.school.Staff ;
import ma.harrati.school.bean.core.school.Principal ;
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
class SchoolAdminServiceImplTest {

    @Mock
    private SchoolDao repository;
    private AutoCloseable autoCloseable;
    private SchoolAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new SchoolAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllSchool() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveSchool() {
        // Given
        School toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteSchool() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetSchoolById() {
        // Given
        Long idToRetrieve = 1L; // Example School ID to retrieve
        School expected = new School(); // You need to replace School with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        School result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private School constructSample(int i) {
		School given = new School();
        given.setName("name-"+i);
        given.setAddress("address-"+i);
        given.setPhoneNumber("phoneNumber-"+i);
        given.setDescription("description-"+i);
        given.setPrincipal(new Principal(1L));
        List<Staff> staffs = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Staff element = new Staff();
                                                element.setId((long)id);
                                                element.setFirstName("firstName"+id);
                                                element.setLastName("lastName"+id);
                                                element.setPhoneNumber("phoneNumber"+id);
                                                element.setRole("role"+id);
                                                element.setPasswordChanged(true);
                                                element.setAccountNonLocked(true);
                                                element.setPassword("password"+id);
                                                element.setEmail("email"+id);
                                                element.setEnabled(true);
                                                element.setCredentialsNonExpired(true);
                                                element.setAccountNonExpired(true);
                                                element.setUsername("username"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setStaffs(staffs);
        List<Professor> professors = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Professor element = new Professor();
                                                element.setId((long)id);
                                                element.setFirstName("firstName"+id);
                                                element.setLastName("lastName"+id);
                                                element.setPhoneNumber("phoneNumber"+id);
                                                element.setSpeciality("speciality"+id);
                                                element.setPasswordChanged(true);
                                                element.setAccountNonLocked(true);
                                                element.setPassword("password"+id);
                                                element.setEmail("email"+id);
                                                element.setEnabled(true);
                                                element.setCredentialsNonExpired(true);
                                                element.setAccountNonExpired(true);
                                                element.setUsername("username"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setProfessors(professors);
        List<Student> students = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Student element = new Student();
                                                element.setId((long)id);
                                                element.setFirstName("firstName"+id);
                                                element.setLastName("lastName"+id);
                                                element.setPhoneNumber("phoneNumber"+id);
                                                element.setRegistrationDate(LocalDateTime.now());
                                                element.setSchool(new School(Long.valueOf(5)));
                                                element.setPasswordChanged(true);
                                                element.setAccountNonLocked(true);
                                                element.setPassword("password"+id);
                                                element.setEmail("email"+id);
                                                element.setEnabled(true);
                                                element.setCredentialsNonExpired(true);
                                                element.setAccountNonExpired(true);
                                                element.setUsername("username"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setStudents(students);
        return given;
    }

}
