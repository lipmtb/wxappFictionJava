package com.wxapp.entity;
import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
/**
 * 小说分类表ftype(fTypeId(主键),fTypeName(小说名))
 * @author li
 *
 */
@Entity
@Table(name="ftype",catalog="fictiondb")
public class FictionType {
  private int fTypeId;
  private String fTypeName;
  private Set<Fiction> fictionSet=new HashSet<Fiction>();
  
  
  public FictionType() {}
  
public FictionType(String fTypeName) {
	this.fTypeName = fTypeName;
}

@Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="fTypeId",nullable=false,unique=true)
public int getfTypeId() {
	return fTypeId;
}
public void setfTypeId(int fTypeId) {
	this.fTypeId = fTypeId;
}
@Column(name="fTypeName",length=40,nullable=false)
public String getfTypeName() {
	return fTypeName;
}
public void setfTypeName(String fTypeName) {
	this.fTypeName = fTypeName;
}

/**
 * 由Fiction多的一方管理关联关系
 * @return
 */
@OneToMany(mappedBy="ftype",fetch=FetchType.EAGER,cascade= {CascadeType.ALL})  
public Set<Fiction> getFictionSet() {
	return fictionSet;
}
public void setFictionSet(Set<Fiction> fictionSet) {
	this.fictionSet = fictionSet;
}
 
  
}
