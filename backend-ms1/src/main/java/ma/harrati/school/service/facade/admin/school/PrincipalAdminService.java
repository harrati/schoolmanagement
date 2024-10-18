package ma.harrati.school.service.facade.admin.school;

import java.util.List;
import ma.harrati.school.bean.core.school.Principal;
import ma.harrati.school.dao.criteria.core.school.PrincipalCriteria;
import ma.harrati.school.zynerator.service.IService;



public interface PrincipalAdminService {


    Principal findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	Principal create(Principal t);

    Principal update(Principal t);

    List<Principal> update(List<Principal> ts,boolean createIfNotExist);

    Principal findById(Long id);

    Principal findOrSave(Principal t);

    Principal findByReferenceEntity(Principal t);

    Principal findWithAssociatedLists(Long id);

    List<Principal> findAllOptimized();

    List<Principal> findAll();

    List<Principal> findByCriteria(PrincipalCriteria criteria);

    List<Principal> findPaginatedByCriteria(PrincipalCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PrincipalCriteria criteria);

    List<Principal> delete(List<Principal> ts);

    boolean deleteById(Long id);

    List<List<Principal>> getToBeSavedAndToBeDeleted(List<Principal> oldList, List<Principal> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
