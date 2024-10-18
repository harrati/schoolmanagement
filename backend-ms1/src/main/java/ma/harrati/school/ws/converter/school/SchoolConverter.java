package  ma.harrati.school.ws.converter.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.harrati.school.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.harrati.school.zynerator.util.ListUtil;

import ma.harrati.school.ws.converter.school.ProfessorConverter;
import ma.harrati.school.bean.core.school.Professor;
import ma.harrati.school.ws.converter.student.PaymentConverter;
import ma.harrati.school.bean.core.student.Payment;
import ma.harrati.school.ws.converter.school.StaffConverter;
import ma.harrati.school.bean.core.school.Staff;
import ma.harrati.school.ws.converter.school.PrincipalConverter;
import ma.harrati.school.bean.core.school.Principal;
import ma.harrati.school.ws.converter.student.StudentConverter;
import ma.harrati.school.bean.core.student.Student;



import ma.harrati.school.zynerator.util.StringUtil;
import ma.harrati.school.zynerator.converter.AbstractConverter;
import ma.harrati.school.zynerator.util.DateUtil;
import ma.harrati.school.bean.core.school.School;
import ma.harrati.school.ws.dto.school.SchoolDto;

@Component
public class SchoolConverter {

    @Autowired
    private ProfessorConverter professorConverter ;
    @Autowired
    private PaymentConverter paymentConverter ;
    @Autowired
    private StaffConverter staffConverter ;
    @Autowired
    private PrincipalConverter principalConverter ;
    @Autowired
    private StudentConverter studentConverter ;
    private boolean principal;
    private boolean staffs;
    private boolean professors;
    private boolean students;

    public  SchoolConverter() {
        init(true);
    }

    public School toItem(SchoolDto dto) {
        if (dto == null) {
            return null;
        } else {
        School item = new School();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getName()))
                item.setName(dto.getName());
            if(StringUtil.isNotEmpty(dto.getAddress()))
                item.setAddress(dto.getAddress());
            if(StringUtil.isNotEmpty(dto.getPhoneNumber()))
                item.setPhoneNumber(dto.getPhoneNumber());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(this.principal && dto.getPrincipal()!=null)
                item.setPrincipal(principalConverter.toItem(dto.getPrincipal())) ;


            if(this.staffs && ListUtil.isNotEmpty(dto.getStaffs()))
                item.setStaffs(staffConverter.toItem(dto.getStaffs()));
            if(this.professors && ListUtil.isNotEmpty(dto.getProfessors()))
                item.setProfessors(professorConverter.toItem(dto.getProfessors()));
            if(this.students && ListUtil.isNotEmpty(dto.getStudents()))
                item.setStudents(studentConverter.toItem(dto.getStudents()));


        return item;
        }
    }


    public SchoolDto toDto(School item) {
        if (item == null) {
            return null;
        } else {
            SchoolDto dto = new SchoolDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getName()))
                dto.setName(item.getName());
            if(StringUtil.isNotEmpty(item.getAddress()))
                dto.setAddress(item.getAddress());
            if(StringUtil.isNotEmpty(item.getPhoneNumber()))
                dto.setPhoneNumber(item.getPhoneNumber());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
            if(this.principal && item.getPrincipal()!=null) {
                dto.setPrincipal(principalConverter.toDto(item.getPrincipal())) ;

            }
        if(this.staffs && ListUtil.isNotEmpty(item.getStaffs())){
            staffConverter.init(true);
            staffConverter.setSchool(false);
            dto.setStaffs(staffConverter.toDto(item.getStaffs()));
            staffConverter.setSchool(true);

        }
        if(this.professors && ListUtil.isNotEmpty(item.getProfessors())){
            professorConverter.init(true);
            professorConverter.setSchool(false);
            dto.setProfessors(professorConverter.toDto(item.getProfessors()));
            professorConverter.setSchool(true);

        }
        if(this.students && ListUtil.isNotEmpty(item.getStudents())){
            studentConverter.init(true);
            studentConverter.setSchool(false);
            dto.setStudents(studentConverter.toDto(item.getStudents()));
            studentConverter.setSchool(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.staffs = value;
        this.professors = value;
        this.students = value;
    }
    public void initObject(boolean value) {
        this.principal = value;
    }
	
    public List<School> toItem(List<SchoolDto> dtos) {
        List<School> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (SchoolDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<SchoolDto> toDto(List<School> items) {
        List<SchoolDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (School item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(SchoolDto dto, School t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getPrincipal() == null  && dto.getPrincipal() != null){
            t.setPrincipal(new Principal());
        }else if (t.getPrincipal() != null  && dto.getPrincipal() != null){
            t.setPrincipal(null);
            t.setPrincipal(new Principal());
        }
        if (dto.getPrincipal() != null)
        principalConverter.copy(dto.getPrincipal(), t.getPrincipal());
        if (dto.getStaffs() != null)
            t.setStaffs(staffConverter.copy(dto.getStaffs()));
        if (dto.getProfessors() != null)
            t.setProfessors(professorConverter.copy(dto.getProfessors()));
        if (dto.getStudents() != null)
            t.setStudents(studentConverter.copy(dto.getStudents()));
    }

    public List<School> copy(List<SchoolDto> dtos) {
        List<School> result = new ArrayList<>();
        if (dtos != null) {
            for (SchoolDto dto : dtos) {
                School instance = new School();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ProfessorConverter getProfessorConverter(){
        return this.professorConverter;
    }
    public void setProfessorConverter(ProfessorConverter professorConverter ){
        this.professorConverter = professorConverter;
    }
    public PaymentConverter getPaymentConverter(){
        return this.paymentConverter;
    }
    public void setPaymentConverter(PaymentConverter paymentConverter ){
        this.paymentConverter = paymentConverter;
    }
    public StaffConverter getStaffConverter(){
        return this.staffConverter;
    }
    public void setStaffConverter(StaffConverter staffConverter ){
        this.staffConverter = staffConverter;
    }
    public PrincipalConverter getPrincipalConverter(){
        return this.principalConverter;
    }
    public void setPrincipalConverter(PrincipalConverter principalConverter ){
        this.principalConverter = principalConverter;
    }
    public StudentConverter getStudentConverter(){
        return this.studentConverter;
    }
    public void setStudentConverter(StudentConverter studentConverter ){
        this.studentConverter = studentConverter;
    }
    public boolean  isPrincipal(){
        return this.principal;
    }
    public void  setPrincipal(boolean principal){
        this.principal = principal;
    }
    public boolean  isStaffs(){
        return this.staffs ;
    }
    public void  setStaffs(boolean staffs ){
        this.staffs  = staffs ;
    }
    public boolean  isProfessors(){
        return this.professors ;
    }
    public void  setProfessors(boolean professors ){
        this.professors  = professors ;
    }
    public boolean  isStudents(){
        return this.students ;
    }
    public void  setStudents(boolean students ){
        this.students  = students ;
    }
}
