package ma.harrati.school.unit.dao.facade.core.school;

import ma.harrati.school.bean.core.school.School;
import ma.harrati.school.dao.facade.core.school.SchoolDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.harrati.school.bean.core.school.Principal ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SchoolDaoTest {

@Autowired
    private SchoolDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        School entity = new School();
        entity.setId(id);
        underTest.save(entity);
        School loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        School entity = new School();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        School loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<School> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<School> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        School given = constructSample(1);
        School saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private School constructSample(int i) {
		School given = new School();
        given.setName("name-"+i);
        given.setAddress("address-"+i);
        given.setPhoneNumber("phoneNumber-"+i);
        given.setDescription("description-"+i);
        given.setPrincipal(new Principal(1L));
        return given;
    }

}
