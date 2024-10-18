package ma.harrati.school.zynerator.security.dao.criteria.core;



import ma.harrati.school.zynerator.criteria.BaseCriteria;

public class RoleCriteria extends  BaseCriteria  {

    private String authority;
    private String authorityLike;



    public RoleCriteria(){}

    public String getAuthority(){
        return this.authority;
    }
    public void setAuthority(String authority){
        this.authority = authority;
    }
    public String getAuthorityLike(){
        return this.authorityLike;
    }
    public void setAuthorityLike(String authorityLike){
        this.authorityLike = authorityLike;
    }


}
