package ma.harrati.school.dao.facade.core.school;

import org.springframework.data.jpa.repository.Query;
import ma.harrati.school.zynerator.repository.AbstractRepository;
import ma.harrati.school.bean.core.school.Professor;
import org.springframework.stereotype.Repository;
import ma.harrati.school.bean.core.school.Professor;
import java.util.List;


@Repository
public interface ProfessorDao extends AbstractRepository<Professor,Long>  {
    Professor findByEmail(String email);
    int deleteByEmail(String email);

    Professor findByUsername(String username);

    @Query("SELECT NEW Professor(item.id,item.email) FROM Professor item")
    List<Professor> findAllOptimized();

}
