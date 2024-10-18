package ma.harrati.school.zynerator.security.service.facade;

import ma.harrati.school.zynerator.security.bean.RoleUser;
import ma.harrati.school.zynerator.security.dao.criteria.core.RoleUserCriteria;
import ma.harrati.school.zynerator.service.IService;

import java.util.List;



public interface RoleUserService extends  IService<RoleUser,RoleUserCriteria>  {

    List<RoleUser> findByRoleId(Long id);
    int deleteByRoleId(Long id);
    long countByRoleAuthority(String authority);
    List<RoleUser> findByUserId(Long id);
    int deleteByUserId(Long id);
    long countByUserEmail(String email);



}
