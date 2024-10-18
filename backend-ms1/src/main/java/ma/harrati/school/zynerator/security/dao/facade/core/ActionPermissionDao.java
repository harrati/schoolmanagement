package ma.harrati.school.zynerator.security.dao.facade.core;

import ma.harrati.school.zynerator.repository.AbstractRepository;
import ma.harrati.school.zynerator.security.bean.ActionPermission;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ActionPermissionDao extends AbstractRepository<ActionPermission,Long>  {
    ActionPermission findByReference(String reference);
    int deleteByReference(String reference);


    @Query("SELECT NEW ActionPermission(item.id,item.reference) FROM ActionPermission item")
    List<ActionPermission> findAllOptimized();

}
