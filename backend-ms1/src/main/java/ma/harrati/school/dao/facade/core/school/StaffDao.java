package ma.harrati.school.dao.facade.core.school;

import org.springframework.data.jpa.repository.Query;
import ma.harrati.school.zynerator.repository.AbstractRepository;
import ma.harrati.school.bean.core.school.Staff;
import org.springframework.stereotype.Repository;
import ma.harrati.school.bean.core.school.Staff;
import java.util.List;


@Repository
public interface StaffDao extends AbstractRepository<Staff,Long>  {
    Staff findByEmail(String email);
    int deleteByEmail(String email);

    Staff findByUsername(String username);

    @Query("SELECT NEW Staff(item.id,item.email) FROM Staff item")
    List<Staff> findAllOptimized();

}
