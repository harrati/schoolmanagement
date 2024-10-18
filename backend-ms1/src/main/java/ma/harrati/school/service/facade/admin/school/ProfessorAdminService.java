package ma.harrati.school.service.facade.admin.school;

import java.util.List;
import ma.harrati.school.bean.core.school.Professor;
import ma.harrati.school.dao.criteria.core.school.ProfessorCriteria;
import ma.harrati.school.zynerator.service.IService;



public interface ProfessorAdminService {


    Professor findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	Professor create(Professor t);

    Professor update(Professor t);

    List<Professor> update(List<Professor> ts,boolean createIfNotExist);

    Professor findById(Long id);

    Professor findOrSave(Professor t);

    Professor findByReferenceEntity(Professor t);

    Professor findWithAssociatedLists(Long id);

    List<Professor> findAllOptimized();

    List<Professor> findAll();

    List<Professor> findByCriteria(ProfessorCriteria criteria);

    List<Professor> findPaginatedByCriteria(ProfessorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ProfessorCriteria criteria);

    List<Professor> delete(List<Professor> ts);

    boolean deleteById(Long id);

    List<List<Professor>> getToBeSavedAndToBeDeleted(List<Professor> oldList, List<Professor> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
