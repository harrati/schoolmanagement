package  ma.harrati.school.ws.facade.admin.school;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.harrati.school.bean.core.school.Staff;
import ma.harrati.school.dao.criteria.core.school.StaffCriteria;
import ma.harrati.school.service.facade.admin.school.StaffAdminService;
import ma.harrati.school.ws.converter.school.StaffConverter;
import ma.harrati.school.ws.dto.school.StaffDto;
import ma.harrati.school.zynerator.controller.AbstractController;
import ma.harrati.school.zynerator.dto.AuditEntityDto;
import ma.harrati.school.zynerator.util.PaginatedList;


import ma.harrati.school.zynerator.security.bean.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.harrati.school.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.harrati.school.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/staff/")
public class StaffRestAdmin {




    @Operation(summary = "Finds a list of all staffs")
    @GetMapping("")
    public ResponseEntity<List<StaffDto>> findAll() throws Exception {
        ResponseEntity<List<StaffDto>> res = null;
        List<Staff> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StaffDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all staffs")
    @GetMapping("optimized")
    public ResponseEntity<List<StaffDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<StaffDto>> res = null;
        List<Staff> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StaffDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a staff by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StaffDto> findById(@PathVariable Long id) {
        Staff t = service.findById(id);
        if (t != null) {
            StaffDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a staff by email")
    @GetMapping("email/{email}")
    public ResponseEntity<StaffDto> findByEmail(@PathVariable String email) {
	    Staff t = service.findByReferenceEntity(new Staff(email));
        if (t != null) {
            StaffDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  staff")
    @PostMapping("")
    public ResponseEntity<StaffDto> save(@RequestBody StaffDto dto) throws Exception {
        if(dto!=null){
            Staff myT = converter.toItem(dto);
            Staff t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StaffDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  staff")
    @PutMapping("")
    public ResponseEntity<StaffDto> update(@RequestBody StaffDto dto) throws Exception {
        ResponseEntity<StaffDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Staff t = service.findById(dto.getId());
            converter.copy(dto,t);
            Staff updated = service.update(t);
            StaffDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of staff")
    @PostMapping("multiple")
    public ResponseEntity<List<StaffDto>> delete(@RequestBody List<StaffDto> dtos) throws Exception {
        ResponseEntity<List<StaffDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Staff> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified staff")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a staff and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StaffDto> findWithAssociatedLists(@PathVariable Long id) {
        Staff loaded =  service.findWithAssociatedLists(id);
        StaffDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds staffs by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StaffDto>> findByCriteria(@RequestBody StaffCriteria criteria) throws Exception {
        ResponseEntity<List<StaffDto>> res = null;
        List<Staff> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<StaffDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated staffs by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StaffCriteria criteria) throws Exception {
        List<Staff> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<StaffDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets staff data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StaffCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StaffDto> findDtos(List<Staff> list){
        List<StaffDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StaffDto> getDtoResponseEntity(StaffDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public StaffRestAdmin(StaffAdminService service, StaffConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final StaffAdminService service;
    private final StaffConverter converter;





}
