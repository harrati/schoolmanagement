package  ma.harrati.school.ws.converter.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.harrati.school.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.harrati.school.ws.converter.student.StudentConverter;
import ma.harrati.school.bean.core.student.Student;

import ma.harrati.school.bean.core.student.Student;


import ma.harrati.school.zynerator.util.StringUtil;
import ma.harrati.school.zynerator.converter.AbstractConverter;
import ma.harrati.school.zynerator.util.DateUtil;
import ma.harrati.school.bean.core.student.Payment;
import ma.harrati.school.ws.dto.student.PaymentDto;

@Component
public class PaymentConverter {

    @Autowired
    private StudentConverter studentConverter ;
    private boolean student;

    public  PaymentConverter() {
        initObject(true);
    }

    public Payment toItem(PaymentDto dto) {
        if (dto == null) {
            return null;
        } else {
        Payment item = new Payment();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getAmount()))
                item.setAmount(dto.getAmount());
            if(StringUtil.isNotEmpty(dto.getPaymentDate()))
                item.setPaymentDate(DateUtil.stringEnToDate(dto.getPaymentDate()));
            if(dto.getStudent() != null && dto.getStudent().getId() != null){
                item.setStudent(new Student());
                item.getStudent().setId(dto.getStudent().getId());
                item.getStudent().setEmail(dto.getStudent().getEmail());
            }




        return item;
        }
    }


    public PaymentDto toDto(Payment item) {
        if (item == null) {
            return null;
        } else {
            PaymentDto dto = new PaymentDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getAmount()))
                dto.setAmount(item.getAmount());
            if(item.getPaymentDate()!=null)
                dto.setPaymentDate(DateUtil.dateTimeToString(item.getPaymentDate()));
            if(this.student && item.getStudent()!=null) {
                dto.setStudent(studentConverter.toDto(item.getStudent())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.student = value;
    }
	
    public List<Payment> toItem(List<PaymentDto> dtos) {
        List<Payment> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PaymentDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PaymentDto> toDto(List<Payment> items) {
        List<PaymentDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Payment item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PaymentDto dto, Payment t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getStudent() == null  && dto.getStudent() != null){
            t.setStudent(new Student());
        }else if (t.getStudent() != null  && dto.getStudent() != null){
            t.setStudent(null);
            t.setStudent(new Student());
        }
        if (dto.getStudent() != null)
        studentConverter.copy(dto.getStudent(), t.getStudent());
    }

    public List<Payment> copy(List<PaymentDto> dtos) {
        List<Payment> result = new ArrayList<>();
        if (dtos != null) {
            for (PaymentDto dto : dtos) {
                Payment instance = new Payment();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public StudentConverter getStudentConverter(){
        return this.studentConverter;
    }
    public void setStudentConverter(StudentConverter studentConverter ){
        this.studentConverter = studentConverter;
    }
    public boolean  isStudent(){
        return this.student;
    }
    public void  setStudent(boolean student){
        this.student = student;
    }
}
