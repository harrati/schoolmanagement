package ma.harrati.school.zynerator.security.service.facade;

import ma.harrati.school.zynerator.security.bean.ActionPermission;
import ma.harrati.school.zynerator.security.dao.criteria.core.ActionPermissionCriteria;
import ma.harrati.school.zynerator.service.IService;
import java.util.List;


public interface ActionPermissionService extends  IService<ActionPermission,ActionPermissionCriteria>  {

    List<ActionPermission> findAllOptimized();

}
