package ma.harrati.school.dao.facade.core.student;

import org.springframework.data.jpa.repository.Query;
import ma.harrati.school.zynerator.repository.AbstractRepository;
import ma.harrati.school.bean.core.student.Student;
import org.springframework.stereotype.Repository;
import ma.harrati.school.bean.core.student.Student;
import java.util.List;


@Repository
public interface StudentDao extends AbstractRepository<Student,Long>  {
    Student findByEmail(String email);
    int deleteByEmail(String email);

    List<Student> findBySchoolId(Long id);
    int deleteBySchoolId(Long id);
    long countBySchoolId(Long id);
    Student findByUsername(String username);

    @Query("SELECT NEW Student(item.id,item.email) FROM Student item")
    List<Student> findAllOptimized();

}
