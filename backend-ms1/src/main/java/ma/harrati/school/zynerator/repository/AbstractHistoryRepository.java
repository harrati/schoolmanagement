package ma.harrati.school.zynerator.repository;

import ma.harrati.school.zynerator.bean.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractHistoryRepository<H extends BaseEntity, ID> extends JpaRepository<H, ID>, JpaSpecificationExecutor<H> {
}
