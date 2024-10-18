package ma.harrati.school.dao.facade.core.school;

import org.springframework.data.jpa.repository.Query;
import ma.harrati.school.zynerator.repository.AbstractRepository;
import ma.harrati.school.bean.core.school.Principal;
import org.springframework.stereotype.Repository;
import ma.harrati.school.bean.core.school.Principal;
import java.util.List;


@Repository
public interface PrincipalDao extends AbstractRepository<Principal,Long>  {
    Principal findByEmail(String email);
    int deleteByEmail(String email);

    Principal findByUsername(String username);

    @Query("SELECT NEW Principal(item.id,item.email) FROM Principal item")
    List<Principal> findAllOptimized();

}
