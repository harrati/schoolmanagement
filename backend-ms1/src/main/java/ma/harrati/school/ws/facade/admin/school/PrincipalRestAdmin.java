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

import ma.harrati.school.bean.core.school.Principal;
import ma.harrati.school.dao.criteria.core.school.PrincipalCriteria;
import ma.harrati.school.service.facade.admin.school.PrincipalAdminService;
import ma.harrati.school.ws.converter.school.PrincipalConverter;
import ma.harrati.school.ws.dto.school.PrincipalDto;
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
@RequestMapping("/api/admin/principal/")
public class PrincipalRestAdmin {




    @Operation(summary = "Finds a list of all principals")
    @GetMapping("")
    public ResponseEntity<List<PrincipalDto>> findAll() throws Exception {
        ResponseEntity<List<PrincipalDto>> res = null;
        List<Principal> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PrincipalDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all principals")
    @GetMapping("optimized")
    public ResponseEntity<List<PrincipalDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<PrincipalDto>> res = null;
        List<Principal> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PrincipalDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a principal by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PrincipalDto> findById(@PathVariable Long id) {
        Principal t = service.findById(id);
        if (t != null) {
            PrincipalDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a principal by email")
    @GetMapping("email/{email}")
    public ResponseEntity<PrincipalDto> findByEmail(@PathVariable String email) {
	    Principal t = service.findByReferenceEntity(new Principal(email));
        if (t != null) {
            PrincipalDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  principal")
    @PostMapping("")
    public ResponseEntity<PrincipalDto> save(@RequestBody PrincipalDto dto) throws Exception {
        if(dto!=null){
            Principal myT = converter.toItem(dto);
            Principal t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                PrincipalDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  principal")
    @PutMapping("")
    public ResponseEntity<PrincipalDto> update(@RequestBody PrincipalDto dto) throws Exception {
        ResponseEntity<PrincipalDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Principal t = service.findById(dto.getId());
            converter.copy(dto,t);
            Principal updated = service.update(t);
            PrincipalDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of principal")
    @PostMapping("multiple")
    public ResponseEntity<List<PrincipalDto>> delete(@RequestBody List<PrincipalDto> dtos) throws Exception {
        ResponseEntity<List<PrincipalDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Principal> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified principal")
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


    @Operation(summary = "Finds a principal and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<PrincipalDto> findWithAssociatedLists(@PathVariable Long id) {
        Principal loaded =  service.findWithAssociatedLists(id);
        PrincipalDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds principals by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<PrincipalDto>> findByCriteria(@RequestBody PrincipalCriteria criteria) throws Exception {
        ResponseEntity<List<PrincipalDto>> res = null;
        List<Principal> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<PrincipalDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated principals by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PrincipalCriteria criteria) throws Exception {
        List<Principal> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<PrincipalDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets principal data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody PrincipalCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<PrincipalDto> findDtos(List<Principal> list){
        List<PrincipalDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<PrincipalDto> getDtoResponseEntity(PrincipalDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public PrincipalRestAdmin(PrincipalAdminService service, PrincipalConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final PrincipalAdminService service;
    private final PrincipalConverter converter;





}
