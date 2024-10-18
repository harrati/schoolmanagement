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
import ma.harrati.school.bean.core.school.Principal;
import ma.harrati.school.ws.dto.school.PrincipalDto;

@Component
public class PrincipalConverter {



    public Principal toItem(PrincipalDto dto) {
        if (dto == null) {
            return null;
        } else {
        Principal item = new Principal();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getFirstName()))
                item.setFirstName(dto.getFirstName());
            if(StringUtil.isNotEmpty(dto.getLastName()))
                item.setLastName(dto.getLastName());
            if(StringUtil.isNotEmpty(dto.getPhoneNumber()))
                item.setPhoneNumber(dto.getPhoneNumber());
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


    public PrincipalDto toDto(Principal item) {
        if (item == null) {
            return null;
        } else {
            PrincipalDto dto = new PrincipalDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getFirstName()))
                dto.setFirstName(item.getFirstName());
            if(StringUtil.isNotEmpty(item.getLastName()))
                dto.setLastName(item.getLastName());
            if(StringUtil.isNotEmpty(item.getPhoneNumber()))
                dto.setPhoneNumber(item.getPhoneNumber());
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


	
    public List<Principal> toItem(List<PrincipalDto> dtos) {
        List<Principal> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (PrincipalDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<PrincipalDto> toDto(List<Principal> items) {
        List<PrincipalDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Principal item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(PrincipalDto dto, Principal t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Principal> copy(List<PrincipalDto> dtos) {
        List<Principal> result = new ArrayList<>();
        if (dtos != null) {
            for (PrincipalDto dto : dtos) {
                Principal instance = new Principal();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
