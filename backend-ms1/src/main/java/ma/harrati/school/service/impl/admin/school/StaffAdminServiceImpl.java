package ma.harrati.school.service.impl.admin.school;


import ma.harrati.school.zynerator.exception.EntityNotFoundException;
import ma.harrati.school.bean.core.school.Staff;
import ma.harrati.school.dao.criteria.core.school.StaffCriteria;
import ma.harrati.school.dao.facade.core.school.StaffDao;
import ma.harrati.school.dao.specification.core.school.StaffSpecification;
import ma.harrati.school.service.facade.admin.school.StaffAdminService;
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
public class StaffAdminServiceImpl implements StaffAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Staff update(Staff t) {
        Staff loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Staff.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Staff findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Staff findOrSave(Staff t) {
        if (t != null) {
            Staff result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Staff> findAll() {
        return dao.findAll();
    }

    public List<Staff> findByCriteria(StaffCriteria criteria) {
        List<Staff> content = null;
        if (criteria != null) {
            StaffSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private StaffSpecification constructSpecification(StaffCriteria criteria) {
        StaffSpecification mySpecification =  (StaffSpecification) RefelexivityUtil.constructObjectUsingOneParam(StaffSpecification.class, criteria);
        return mySpecification;
    }

    public List<Staff> findPaginatedByCriteria(StaffCriteria criteria, int page, int pageSize, String order, String sortField) {
        StaffSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(StaffCriteria criteria) {
        StaffSpecification mySpecification = constructSpecification(criteria);
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
    public List<Staff> delete(List<Staff> list) {
		List<Staff> result = new ArrayList();
        if (list != null) {
            for (Staff t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }


    public Staff findWithAssociatedLists(Long id){
        Staff result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Staff> update(List<Staff> ts, boolean createIfNotExist) {
        List<Staff> result = new ArrayList<>();
        if (ts != null) {
            for (Staff t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Staff loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Staff t, Staff loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Staff findByReferenceEntity(Staff t){
        return t==null? null : dao.findByEmail(t.getEmail());
    }



    public List<Staff> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Staff>> getToBeSavedAndToBeDeleted(List<Staff> oldList, List<Staff> newList) {
        List<List<Staff>> result = new ArrayList<>();
        List<Staff> resultDelete = new ArrayList<>();
        List<Staff> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Staff> oldList, List<Staff> newList, List<Staff> resultUpdateOrSave, List<Staff> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Staff myOld = oldList.get(i);
                Staff t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Staff myNew = newList.get(i);
                Staff t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    public Staff create(Staff t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.STAFF);
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

        Staff mySaved = dao.save(t);

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

    public Staff findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }




    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;


    public StaffAdminServiceImpl(StaffDao dao) {
        this.dao = dao;
    }

    private StaffDao dao;
}
