package  ma.harrati.school.ws.converter.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.harrati.school.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.harrati.school.zynerator.util.StringUtil;
import ma.harrati.school.zynerator.converter.AbstractConverter;
import ma.harrati.school.zynerator.util.DateUtil;
import ma.harrati.school.bean.core.school.Staff;
import ma.harrati.school.ws.dto.school.StaffDto;

@Component
public class StaffConverter {



    public Staff toItem(StaffDto dto) {
        if (dto == null) {
            return null;
        } else {
        Staff item = new Staff();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getFirstName()))
                item.setFirstName(dto.getFirstName());
            if(StringUtil.isNotEmpty(dto.getLastName()))
                item.setLastName(dto.getLastName());
            if(StringUtil.isNotEmpty(dto.getPhoneNumber()))
                item.setPhoneNumber(dto.getPhoneNumber());
            if(StringUtil.isNotEmpty(dto.getRole()))
                item.setRole(dto.getRole());
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


            //item.setRoles(dto.getRoles());

        return item;
        }
    }


    public StaffDto toDto(Staff item) {
        if (item == null) {
            return null;
        } else {
            StaffDto dto = new StaffDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getFirstName()))
                dto.setFirstName(item.getFirstName());
            if(StringUtil.isNotEmpty(item.getLastName()))
                dto.setLastName(item.getLastName());
            if(StringUtil.isNotEmpty(item.getPhoneNumber()))
                dto.setPhoneNumber(item.getPhoneNumber());
            if(StringUtil.isNotEmpty(item.getRole()))
                dto.setRole(item.getRole());
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


        return dto;
        }
    }


	
    public List<Staff> toItem(List<StaffDto> dtos) {
        List<Staff> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StaffDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StaffDto> toDto(List<Staff> items) {
        List<StaffDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Staff item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StaffDto dto, Staff t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Staff> copy(List<StaffDto> dtos) {
        List<Staff> result = new ArrayList<>();
        if (dtos != null) {
            for (StaffDto dto : dtos) {
                Staff instance = new Staff();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
