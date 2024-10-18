package  ma.harrati.school.dao.criteria.core.school;



import ma.harrati.school.zynerator.criteria.BaseCriteria;

import java.util.List;

public class SchoolCriteria extends  BaseCriteria  {

    private String name;
    private String nameLike;
    private String address;
    private String addressLike;
    private String phoneNumber;
    private String phoneNumberLike;
    private String description;
    private String descriptionLike;

    private PrincipalCriteria principal ;
    private List<PrincipalCriteria> principals ;


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getNameLike(){
        return this.nameLike;
    }
    public void setNameLike(String nameLike){
        this.nameLike = nameLike;
    }

    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getAddressLike(){
        return this.addressLike;
    }
    public void setAddressLike(String addressLike){
        this.addressLike = addressLike;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumberLike(){
        return this.phoneNumberLike;
    }
    public void setPhoneNumberLike(String phoneNumberLike){
        this.phoneNumberLike = phoneNumberLike;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }


    public PrincipalCriteria getPrincipal(){
        return this.principal;
    }

    public void setPrincipal(PrincipalCriteria principal){
        this.principal = principal;
    }
    public List<PrincipalCriteria> getPrincipals(){
        return this.principals;
    }

    public void setPrincipals(List<PrincipalCriteria> principals){
        this.principals = principals;
    }
}
