package ma.harrati.school.service.facade.admin.school;

import java.util.List;
import ma.harrati.school.bean.core.school.School;
import ma.harrati.school.dao.criteria.core.school.SchoolCriteria;
import ma.harrati.school.zynerator.service.IService;



public interface SchoolAdminService {



    List<School> findByPrincipalId(Long id);
    int deleteByPrincipalId(Long id);
    long countByPrincipalEmail(String email);




	School create(School t);

    School update(School t);

    List<School> update(List<School> ts,boolean createIfNotExist);

    School findById(Long id);

    School findOrSave(School t);

    School findByReferenceEntity(School t);

    School findWithAssociatedLists(Long id);

    List<School> findAllOptimized();

    List<School> findAll();

    List<School> findByCriteria(SchoolCriteria criteria);

    List<School> findPaginatedByCriteria(SchoolCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SchoolCriteria criteria);

    List<School> delete(List<School> ts);

    boolean deleteById(Long id);

    List<List<School>> getToBeSavedAndToBeDeleted(List<School> oldList, List<School> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
