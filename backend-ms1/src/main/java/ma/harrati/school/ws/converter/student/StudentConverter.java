package  ma.harrati.school.ws.converter.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.harrati.school.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.harrati.school.zynerator.util.ListUtil;

import ma.harrati.school.ws.converter.student.PaymentConverter;
import ma.harrati.school.bean.core.student.Payment;
import ma.harrati.school.ws.converter.school.SchoolConverter;
import ma.harrati.school.bean.core.school.School;

import ma.harrati.school.bean.core.school.School;


import ma.harrati.school.zynerator.util.StringUtil;
import ma.harrati.school.zynerator.converter.AbstractConverter;
import ma.harrati.school.zynerator.util.DateUtil;
import ma.harrati.school.bean.core.student.Student;
import ma.harrati.school.ws.dto.student.StudentDto;

@Component
public class StudentConverter {

    @Autowired
    private PaymentConverter paymentConverter ;
    @Autowired
    private SchoolConverter schoolConverter ;
    private boolean school;
    private boolean payments;

    public  StudentConverter() {
        init(true);
    }

    public Student toItem(StudentDto dto) {
        if (dto == null) {
            return null;
        } else {
        Student item = new Student();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getFirstName()))
                item.setFirstName(dto.getFirstName());
            if(StringUtil.isNotEmpty(dto.getLastName()))
                item.setLastName(dto.getLastName());
            if(StringUtil.isNotEmpty(dto.getPhoneNumber()))
                item.setPhoneNumber(dto.getPhoneNumber());
            if(StringUtil.isNotEmpty(dto.getRegistrationDate()))
                item.setRegistrationDate(DateUtil.stringEnToDate(dto.getRegistrationDate()));
            item.setPasswordChanged(dto.getPasswordChanged());
            item.setAccountNonLocked(dto.getAccountNonLocked());
            if(StringUtil.isNotEmpty(dto.getPassword()))
                item.setPassword(dto.getPassword());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            item.setEnabled(dto.getEnabled());
            item.setCredentialsNonExpired(dto.getCredentialsNonExpired());
            item.setAccountNonExpired(dto.getAccountNonExpired());
            if(StringUtil.isNotEmpty(dto.getUsername()))
                item.setUsername(dto.getUsername());
            if(dto.getSchool() != null && dto.getSchool().getId() != null){
                item.setSchool(new School());
                item.getSchool().setId(dto.getSchool().getId());
                item.getSchool().setId(dto.getSchool().getId());
            }


            if(this.payments && ListUtil.isNotEmpty(dto.getPayments()))
                item.setPayments(paymentConverter.toItem(dto.getPayments()));

            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public StudentDto toDto(Student item) {
        if (item == null) {
            return null;
        } else {
            StudentDto dto = new StudentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getFirstName()))
                dto.setFirstName(item.getFirstName());
            if(StringUtil.isNotEmpty(item.getLastName()))
                dto.setLastName(item.getLastName());
            if(StringUtil.isNotEmpty(item.getPhoneNumber()))
                dto.setPhoneNumber(item.getPhoneNumber());
            if(item.getRegistrationDate()!=null)
                dto.setRegistrationDate(DateUtil.dateTimeToString(item.getRegistrationDate()));
            if(StringUtil.isNotEmpty(item.getPasswordChanged()))
                dto.setPasswordChanged(item.getPasswordChanged());
            if(StringUtil.isNotEmpty(item.getAccountNonLocked()))
                dto.setAccountNonLocked(item.getAccountNonLocked());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getEnabled()))
                dto.setEnabled(item.getEnabled());
            if(StringUtil.isNotEmpty(item.getCredentialsNonExpired()))
                dto.setCredentialsNonExpired(item.getCredentialsNonExpired());
            if(StringUtil.isNotEmpty(item.getAccountNonExpired()))
                dto.setAccountNonExpired(item.getAccountNonExpired());
            if(StringUtil.isNotEmpty(item.getUsername()))
                dto.setUsername(item.getUsername());
            if(this.school && item.getSchool()!=null) {
                dto.setSchool(schoolConverter.toDto(item.getSchool())) ;

            }
        if(this.payments && ListUtil.isNotEmpty(item.getPayments())){
            paymentConverter.init(true);
            paymentConverter.setStudent(false);
            dto.setPayments(paymentConverter.toDto(item.getPayments()));
            paymentConverter.setStudent(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.payments = value;
    }
    public void initObject(boolean value) {
        this.school = value;
    }
	
    public List<Student> toItem(List<StudentDto> dtos) {
        List<Student> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StudentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StudentDto> toDto(List<Student> items) {
        List<StudentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Student item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StudentDto dto, Student t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getSchool() == null  && dto.getSchool() != null){
            t.setSchool(new School());
        }else if (t.getSchool() != null  && dto.getSchool() != null){
            t.setSchool(null);
            t.setSchool(new School());
        }
        if (dto.getSchool() != null)
        schoolConverter.copy(dto.getSchool(), t.getSchool());
        if (dto.getPayments() != null)
            t.setPayments(paymentConverter.copy(dto.getPayments()));
    }

    public List<Student> copy(List<StudentDto> dtos) {
        List<Student> result = new ArrayList<>();
        if (dtos != null) {
            for (StudentDto dto : dtos) {
                Student instance = new Student();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public PaymentConverter getPaymentConverter(){
        return this.paymentConverter;
    }
    public void setPaymentConverter(PaymentConverter paymentConverter ){
        this.paymentConverter = paymentConverter;
    }
    public SchoolConverter getSchoolConverter(){
        return this.schoolConverter;
    }
    public void setSchoolConverter(SchoolConverter schoolConverter ){
        this.schoolConverter = schoolConverter;
    }
    public boolean  isSchool(){
        return this.school;
    }
    public void  setSchool(boolean school){
        this.school = school;
    }
    public boolean  isPayments(){
        return this.payments ;
    }
    public void  setPayments(boolean payments ){
        this.payments  = payments ;
    }
}
