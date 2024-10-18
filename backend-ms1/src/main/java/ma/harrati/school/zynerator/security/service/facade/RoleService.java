package ma.harrati.school.zynerator.security.service.facade;

import ma.harrati.school.zynerator.security.bean.Role;
import ma.harrati.school.zynerator.security.dao.criteria.core.RoleCriteria;
import ma.harrati.school.zynerator.service.IService;



public interface RoleService extends  IService<Role,RoleCriteria>  {
    Role findByAuthority(String authority);
    int deleteByAuthority(String authority);


    



}
