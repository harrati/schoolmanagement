package ma.harrati.school.zynerator.security.dao.facade.core;

import ma.harrati.school.zynerator.repository.AbstractRepository;
import ma.harrati.school.zynerator.security.bean.RoleUser;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoleUserDao extends AbstractRepository<RoleUser,Long>  {

    List<RoleUser> findByRoleId(Long id);
    int deleteByRoleId(Long id);
    long countByRoleAuthority(String authority);
    List<RoleUser> findByUserId(Long id);
    int deleteByUserId(Long id);
    long countByUserEmail(String email);


}
