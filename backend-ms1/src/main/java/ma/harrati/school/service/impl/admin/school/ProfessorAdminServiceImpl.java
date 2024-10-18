package ma.harrati.school.service.impl.admin.school;


import ma.harrati.school.zynerator.exception.EntityNotFoundException;
import ma.harrati.school.bean.core.school.Professor;
import ma.harrati.school.dao.criteria.core.school.ProfessorCriteria;
import ma.harrati.school.dao.facade.core.school.ProfessorDao;
import ma.harrati.school.dao.specification.core.school.ProfessorSpecification;
import ma.harrati.school.service.facade.admin.school.ProfessorAdminService;
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


import java.time.LocalDateTime;
import ma.harrati.school.zynerator.security.service.facade.UserService;
import ma.harrati.school.zynerator.security.service.facade.RoleService;
import ma.harrati.school.zynerator.security.service.facade.RoleUserService;
import ma.harrati.school.zynerator.security.bean.Role;
import ma.harrati.school.zynerator.security.bean.RoleUser;
import ma.harrati.school.zynerator.security.common.AuthoritiesConstants;
import ma.harrati.school.zynerator.security.service.facade.ModelPermissionUserService;
import java.util.Collection;
import java.util.List;
@Service
public class ProfessorAdminServiceImpl implements ProfessorAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Professor update(Professor t) {
        Professor loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Professor.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Professor findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Professor findOrSave(Professor t) {
        if (t != null) {
            Professor result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Professor> findAll() {
        return dao.findAll();
    }

    public List<Professor> findByCriteria(ProfessorCriteria criteria) {
        List<Professor> content = null;
        if (criteria != null) {
            ProfessorSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ProfessorSpecification constructSpecification(ProfessorCriteria criteria) {
        ProfessorSpecification mySpecification =  (ProfessorSpecification) RefelexivityUtil.constructObjectUsingOneParam(ProfessorSpecification.class, criteria);
        return mySpecification;
    }

    public List<Professor> findPaginatedByCriteria(ProfessorCriteria criteria, int page, int pageSize, String order, String sortField) {
        ProfessorSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ProfessorCriteria criteria) {
        ProfessorSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Professor> delete(List<Professor> list) {
		List<Professor> result = new ArrayList();
        if (list != null) {
            for (Professor t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }


    public Professor findWithAssociatedLists(Long id){
        Professor result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Professor> update(List<Professor> ts, boolean createIfNotExist) {
        List<Professor> result = new ArrayList<>();
        if (ts != null) {
            for (Professor t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Professor loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Professor t, Professor loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Professor findByReferenceEntity(Professor t){
        return t==null? null : dao.findByEmail(t.getEmail());
    }



    public List<Professor> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Professor>> getToBeSavedAndToBeDeleted(List<Professor> oldList, List<Professor> newList) {
        List<List<Professor>> result = new ArrayList<>();
        List<Professor> resultDelete = new ArrayList<>();
        List<Professor> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Professor> oldList, List<Professor> newList, List<Professor> resultUpdateOrSave, List<Professor> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Professor myOld = oldList.get(i);
                Professor t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Professor myNew = newList.get(i);
                Professor t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }


    @Override
    public Professor create(Professor t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.PROFESSOR);
        role.setCreatedAt(LocalDateTime.now());
        Role myRole = roleService.create(role);
        RoleUser roleUser = new RoleUser();
        roleUser.setRole(myRole);
        if (t.getRoleUsers() == null)
            t.setRoleUsers(new ArrayList<>());

        t.getRoleUsers().add(roleUser);
        if (t.getModelPermissionUsers() == null)
            t.setModelPermissionUsers(new ArrayList<>());

        t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        Professor mySaved = dao.save(t);

        if (t.getModelPermissionUsers() != null) {
            t.getModelPermissionUsers().forEach(e -> {
                e.setUser(mySaved);
                modelPermissionUserService.create(e);
            });
        }
        if (t.getRoleUsers() != null) {
            t.getRoleUsers().forEach(element-> {
                element.setUser(mySaved);
                roleUserService.create(element);
            });
        }

        return mySaved;
     }

    public Professor findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }




    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;


    public ProfessorAdminServiceImpl(ProfessorDao dao) {
        this.dao = dao;
    }

    private ProfessorDao dao;
}
