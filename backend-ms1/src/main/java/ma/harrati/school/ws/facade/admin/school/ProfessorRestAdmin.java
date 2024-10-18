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

import ma.harrati.school.bean.core.school.Professor;
import ma.harrati.school.dao.criteria.core.school.ProfessorCriteria;
import ma.harrati.school.service.facade.admin.school.ProfessorAdminService;
import ma.harrati.school.ws.converter.school.ProfessorConverter;
import ma.harrati.school.ws.dto.school.ProfessorDto;
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
@RequestMapping("/api/admin/professor/")
public class ProfessorRestAdmin {




    @Operation(summary = "Finds a list of all professors")
    @GetMapping("")
    public ResponseEntity<List<ProfessorDto>> findAll() throws Exception {
        ResponseEntity<List<ProfessorDto>> res = null;
        List<Professor> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProfessorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all professors")
    @GetMapping("optimized")
    public ResponseEntity<List<ProfessorDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ProfessorDto>> res = null;
        List<Professor> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProfessorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a professor by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ProfessorDto> findById(@PathVariable Long id) {
        Professor t = service.findById(id);
        if (t != null) {
            ProfessorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a professor by email")
    @GetMapping("email/{email}")
    public ResponseEntity<ProfessorDto> findByEmail(@PathVariable String email) {
	    Professor t = service.findByReferenceEntity(new Professor(email));
        if (t != null) {
            ProfessorDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  professor")
    @PostMapping("")
    public ResponseEntity<ProfessorDto> save(@RequestBody ProfessorDto dto) throws Exception {
        if(dto!=null){
            Professor myT = converter.toItem(dto);
            Professor t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ProfessorDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  professor")
    @PutMapping("")
    public ResponseEntity<ProfessorDto> update(@RequestBody ProfessorDto dto) throws Exception {
        ResponseEntity<ProfessorDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Professor t = service.findById(dto.getId());
            converter.copy(dto,t);
            Professor updated = service.update(t);
            ProfessorDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of professor")
    @PostMapping("multiple")
    public ResponseEntity<List<ProfessorDto>> delete(@RequestBody List<ProfessorDto> dtos) throws Exception {
        ResponseEntity<List<ProfessorDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Professor> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified professor")
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


    @Operation(summary = "Finds a professor and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ProfessorDto> findWithAssociatedLists(@PathVariable Long id) {
        Professor loaded =  service.findWithAssociatedLists(id);
        ProfessorDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds professors by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ProfessorDto>> findByCriteria(@RequestBody ProfessorCriteria criteria) throws Exception {
        ResponseEntity<List<ProfessorDto>> res = null;
        List<Professor> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ProfessorDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated professors by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProfessorCriteria criteria) throws Exception {
        List<Professor> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ProfessorDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets professor data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ProfessorCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ProfessorDto> findDtos(List<Professor> list){
        List<ProfessorDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ProfessorDto> getDtoResponseEntity(ProfessorDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }



    @Operation(summary = "Change password to the specified  utilisateur")
    @PutMapping("changePassword")
    public boolean changePassword(@RequestBody User dto) throws Exception {
        return service.changePassword(dto.getUsername(),dto.getPassword());
    }

   public ProfessorRestAdmin(ProfessorAdminService service, ProfessorConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ProfessorAdminService service;
    private final ProfessorConverter converter;





}
