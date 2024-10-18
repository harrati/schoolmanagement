package ma.harrati.school.zynerator.security.service.impl;


import ma.harrati.school.zynerator.security.bean.RoleUser;
import ma.harrati.school.zynerator.security.dao.criteria.core.RoleUserCriteria;
import ma.harrati.school.zynerator.security.dao.facade.core.RoleUserDao;
import ma.harrati.school.zynerator.security.dao.specification.core.RoleUserSpecification;
import ma.harrati.school.zynerator.security.service.facade.RoleService;
import ma.harrati.school.zynerator.security.service.facade.RoleUserService;
import ma.harrati.school.zynerator.security.service.facade.UserService;
import ma.harrati.school.zynerator.service.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUserServiceImpl extends AbstractServiceImpl<RoleUser, RoleUserCriteria, RoleUserDao> implements RoleUserService {






    public List<RoleUser> findByRoleId(Long id){
        return dao.findByRoleId(id);
    }
    public int deleteByRoleId(Long id){
        return dao.deleteByRoleId(id);
    }
    public long countByRoleAuthority(String authority){
        return dao.countByRoleAuthority(authority);
    }
    public List<RoleUser> findByUserId(Long id){
        return dao.findByUserId(id);
    }
    public int deleteByUserId(Long id){
        return dao.deleteByUserId(id);
    }
    public long countByUserEmail(String email){
        return dao.countByUserEmail(email);
    }






    public void configure() {
        super.configure(RoleUser.class, RoleUserSpecification.class);
    }


    @Autowired
    private RoleService roleService ;
    @Autowired
    private UserService utilisateurService ;

    public RoleUserServiceImpl(RoleUserDao dao) {
        super(dao);
    }

}
