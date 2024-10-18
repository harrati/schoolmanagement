package ma.harrati.school.service.facade.admin.school;

import java.util.List;
import ma.harrati.school.bean.core.school.Staff;
import ma.harrati.school.dao.criteria.core.school.StaffCriteria;
import ma.harrati.school.zynerator.service.IService;



public interface StaffAdminService {


    Staff findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	Staff create(Staff t);

    Staff update(Staff t);

    List<Staff> update(List<Staff> ts,boolean createIfNotExist);

    Staff findById(Long id);

    Staff findOrSave(Staff t);

    Staff findByReferenceEntity(Staff t);

    Staff findWithAssociatedLists(Long id);

    List<Staff> findAllOptimized();

    List<Staff> findAll();

    List<Staff> findByCriteria(StaffCriteria criteria);

    List<Staff> findPaginatedByCriteria(StaffCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StaffCriteria criteria);

    List<Staff> delete(List<Staff> ts);

    boolean deleteById(Long id);

    List<List<Staff>> getToBeSavedAndToBeDeleted(List<Staff> oldList, List<Staff> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
