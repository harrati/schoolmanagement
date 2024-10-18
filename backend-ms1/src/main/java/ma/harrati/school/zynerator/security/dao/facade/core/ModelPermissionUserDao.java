package ma.harrati.school.zynerator.security.dao.facade.core;

import ma.harrati.school.zynerator.repository.AbstractRepository;
import ma.harrati.school.zynerator.security.bean.ModelPermissionUser;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ModelPermissionUserDao extends AbstractRepository<ModelPermissionUser,Long>  {

    List<ModelPermissionUser> findByActionPermissionId(Long id);
    int deleteByActionPermissionId(Long id);
    long countByActionPermissionReference(String reference);
    List<ModelPermissionUser> findByModelPermissionId(Long id);
    int deleteByModelPermissionId(Long id);
    long countByModelPermissionReference(String reference);
    List<ModelPermissionUser> findByUserId(Long id);
    ModelPermissionUser findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference( String username ,  String modelReference,  String actionReference);
    int deleteByUserId(Long id);
    long countByUserEmail(String email);
    List<ModelPermissionUser> findByUserUsername(String username);



}
