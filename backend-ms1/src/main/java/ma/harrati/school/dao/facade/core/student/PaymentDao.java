package ma.harrati.school.dao.facade.core.student;

import ma.harrati.school.zynerator.repository.AbstractRepository;
import ma.harrati.school.bean.core.student.Payment;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PaymentDao extends AbstractRepository<Payment,Long>  {

    List<Payment> findByStudentId(Long id);
    int deleteByStudentId(Long id);
    long countByStudentEmail(String email);


}
