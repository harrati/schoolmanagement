package ma.harrati.school.dao.facade.core.school;

import ma.harrati.school.zynerator.repository.AbstractRepository;
import ma.harrati.school.bean.core.school.School;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SchoolDao extends AbstractRepository<School,Long>  {

    List<School> findByPrincipalId(Long id);
    int deleteByPrincipalId(Long id);
    long countByPrincipalEmail(String email);


}
