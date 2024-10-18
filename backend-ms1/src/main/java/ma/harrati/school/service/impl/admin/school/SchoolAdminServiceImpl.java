package ma.harrati.school.service.impl.admin.school;


import ma.harrati.school.zynerator.exception.EntityNotFoundException;
import ma.harrati.school.bean.core.school.School;
import ma.harrati.school.dao.criteria.core.school.SchoolCriteria;
import ma.harrati.school.dao.facade.core.school.SchoolDao;
import ma.harrati.school.dao.specification.core.school.SchoolSpecification;
import ma.harrati.school.service.facade.admin.school.SchoolAdminService;
import ma.harrati.school.zynerator.service.AbstractServiceImpl;
import static ma.harrati.school.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.harrati.school.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.harrati.school.service.facade.admin.school.ProfessorAdminService ;
import ma.harrati.school.bean.core.school.Professor ;
import ma.harrati.school.service.facade.admin.school.StaffAdminService ;
import ma.harrati.school.bean.core.school.Staff ;
import ma.harrati.school.service.facade.admin.school.PrincipalAdminService ;
import ma.harrati.school.bean.core.school.Principal ;
import ma.harrati.school.service.facade.admin.student.StudentAdminService ;
import ma.harrati.school.bean.core.student.Student ;

import java.util.List;
@Service
public class SchoolAdminServiceImpl implements SchoolAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public School update(School t) {
        School loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{School.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public School findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public School findOrSave(School t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            School result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<School> findAll() {
        return dao.findAll();
    }

    public List<School> findByCriteria(SchoolCriteria criteria) {
        List<School> content = null;
        if (criteria != null) {
            SchoolSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private SchoolSpecification constructSpecification(SchoolCriteria criteria) {
        SchoolSpecification mySpecification =  (SchoolSpecification) RefelexivityUtil.constructObjectUsingOneParam(SchoolSpecification.class, criteria);
        return mySpecification;
    }

    public List<School> findPaginatedByCriteria(SchoolCriteria criteria, int page, int pageSize, String order, String sortField) {
        SchoolSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SchoolCriteria criteria) {
        SchoolSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<School> findByPrincipalId(Long id){
        return dao.findByPrincipalId(id);
    }
    public int deleteByPrincipalId(Long id){
        return dao.deleteByPrincipalId(id);
    }
    public long countByPrincipalEmail(String email){
        return dao.countByPrincipalEmail(email);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        staffService.deleteBySchoolId(id);
        professorService.deleteBySchoolId(id);
        studentService.deleteBySchoolId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<School> delete(List<School> list) {
		List<School> result = new ArrayList();
        if (list != null) {
            for (School t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public School create(School t) {
        School loaded = findByReferenceEntity(t);
        School saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getStaffs() != null) {
                t.getStaffs().forEach(element-> {
                    element.setSchool(saved);
                    staffService.create(element);
                });
            }
            if (t.getProfessors() != null) {
                t.getProfessors().forEach(element-> {
                    element.setSchool(saved);
                    professorService.create(element);
                });
            }
            if (t.getStudents() != null) {
                t.getStudents().forEach(element-> {
                    element.setSchool(saved);
                    studentService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public School findWithAssociatedLists(Long id){
        School result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setStaffs(staffService.findBySchoolId(id));
            result.setProfessors(professorService.findBySchoolId(id));
            result.setStudents(studentService.findBySchoolId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<School> update(List<School> ts, boolean createIfNotExist) {
        List<School> result = new ArrayList<>();
        if (ts != null) {
            for (School t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    School loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, School t, School loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(School school){
    if(school !=null && school.getId() != null){
        List<List<Staff>> resultStaffs= staffService.getToBeSavedAndToBeDeleted(staffService.findBySchoolId(school.getId()),school.getStaffs());
            staffService.delete(resultStaffs.get(1));
        emptyIfNull(resultStaffs.get(0)).forEach(e -> e.setSchool(school));
        staffService.update(resultStaffs.get(0),true);
        List<List<Professor>> resultProfessors= professorService.getToBeSavedAndToBeDeleted(professorService.findBySchoolId(school.getId()),school.getProfessors());
            professorService.delete(resultProfessors.get(1));
        emptyIfNull(resultProfessors.get(0)).forEach(e -> e.setSchool(school));
        professorService.update(resultProfessors.get(0),true);
        List<List<Student>> resultStudents= studentService.getToBeSavedAndToBeDeleted(studentService.findBySchoolId(school.getId()),school.getStudents());
            studentService.delete(resultStudents.get(1));
        emptyIfNull(resultStudents.get(0)).forEach(e -> e.setSchool(school));
        studentService.update(resultStudents.get(0),true);
        }
    }








    public School findByReferenceEntity(School t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(School t){
        if( t != null) {
            t.setPrincipal(principalService.findOrSave(t.getPrincipal()));
        }
    }



    public List<School> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<School>> getToBeSavedAndToBeDeleted(List<School> oldList, List<School> newList) {
        List<List<School>> result = new ArrayList<>();
        List<School> resultDelete = new ArrayList<>();
        List<School> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<School> oldList, List<School> newList, List<School> resultUpdateOrSave, List<School> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                School myOld = oldList.get(i);
                School t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                School myNew = newList.get(i);
                School t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private ProfessorAdminService professorService ;
    @Autowired
    private StaffAdminService staffService ;
    @Autowired
    private PrincipalAdminService principalService ;
    @Autowired
    private StudentAdminService studentService ;

    public SchoolAdminServiceImpl(SchoolDao dao) {
        this.dao = dao;
    }

    private SchoolDao dao;
}
