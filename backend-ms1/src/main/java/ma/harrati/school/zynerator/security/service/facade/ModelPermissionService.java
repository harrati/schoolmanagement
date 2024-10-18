package ma.harrati.school.zynerator.security.service.facade;

import ma.harrati.school.zynerator.security.bean.ModelPermission;
import ma.harrati.school.zynerator.security.dao.criteria.core.ModelPermissionCriteria;
import ma.harrati.school.zynerator.service.IService;
import java.util.List;



public interface ModelPermissionService extends  IService<ModelPermission,ModelPermissionCriteria>  {
    List<ModelPermission> findAllOptimized();

}
