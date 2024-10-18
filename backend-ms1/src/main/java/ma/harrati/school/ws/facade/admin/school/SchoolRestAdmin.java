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

import ma.harrati.school.bean.core.school.School;
import ma.harrati.school.dao.criteria.core.school.SchoolCriteria;
import ma.harrati.school.service.facade.admin.school.SchoolAdminService;
import ma.harrati.school.ws.converter.school.SchoolConverter;
import ma.harrati.school.ws.dto.school.SchoolDto;
import ma.harrati.school.zynerator.controller.AbstractController;
import ma.harrati.school.zynerator.dto.AuditEntityDto;
import ma.harrati.school.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.harrati.school.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.harrati.school.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/school/")
public class SchoolRestAdmin {




    @Operation(summary = "Finds a list of all schools")
    @GetMapping("")
    public ResponseEntity<List<SchoolDto>> findAll() throws Exception {
        ResponseEntity<List<SchoolDto>> res = null;
        List<School> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<SchoolDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a school by id")
    @GetMapping("id/{id}")
    public ResponseEntity<SchoolDto> findById(@PathVariable Long id) {
        School t = service.findById(id);
        if (t != null) {
            converter.init(true);
            SchoolDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  school")
    @PostMapping("")
    public ResponseEntity<SchoolDto> save(@RequestBody SchoolDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            School myT = converter.toItem(dto);
            School t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                SchoolDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  school")
    @PutMapping("")
    public ResponseEntity<SchoolDto> update(@RequestBody SchoolDto dto) throws Exception {
        ResponseEntity<SchoolDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            School t = service.findById(dto.getId());
            converter.copy(dto,t);
            School updated = service.update(t);
            SchoolDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of school")
    @PostMapping("multiple")
    public ResponseEntity<List<SchoolDto>> delete(@RequestBody List<SchoolDto> dtos) throws Exception {
        ResponseEntity<List<SchoolDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<School> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified school")
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


    @Operation(summary = "Finds a school and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<SchoolDto> findWithAssociatedLists(@PathVariable Long id) {
        School loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        SchoolDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds schools by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<SchoolDto>> findByCriteria(@RequestBody SchoolCriteria criteria) throws Exception {
        ResponseEntity<List<SchoolDto>> res = null;
        List<School> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<SchoolDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated schools by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SchoolCriteria criteria) throws Exception {
        List<School> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<SchoolDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets school data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody SchoolCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<SchoolDto> findDtos(List<School> list){
        converter.initList(false);
        converter.initObject(true);
        List<SchoolDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<SchoolDto> getDtoResponseEntity(SchoolDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public SchoolRestAdmin(SchoolAdminService service, SchoolConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final SchoolAdminService service;
    private final SchoolConverter converter;





}
