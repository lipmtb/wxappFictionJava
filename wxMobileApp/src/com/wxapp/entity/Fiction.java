package com.wxapp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * С˵��fictiontb(fId(����)��fName(С˵��),
 * fAuthor(����),fBrief(С˵���)��
 * fCoverUrl(С˵����·��),fTypeId(С˵����id  ���)
 * @author li
 *
 */
@Entity
@Table(name="fictiontb",catalog="fictiondb")
public class Fiction {
  private int fId;
  private String fName;
  private String fBrief;
  private String fCoverUrl;
  private String fAuthor;
  private FictionType ftype;
  
  @JsonIgnoreProperties(value= {"fiction"})
  private Set<FictionChapter> fchapterSet=new HashSet<FictionChapter>();//С˵�½�
  
  @JsonIgnoreProperties(value= {"likeFictionSet"}) 
  private Set<Reader> fLikeReaderSet=new HashSet<Reader>();//С˵������
  
  @JsonIgnoreProperties(value= {"collectFictionSet"})
  private Set<Reader> fCollectReaderSet=new HashSet<Reader>();//С˵���ղ�
  
  @JsonIgnoreProperties(value= {"readFictionSet"})
  private Set<Reader> fReadReaderSet=new HashSet<Reader>();//С˵���Ķ�
  
  @JsonIgnoreProperties(value= {"fiction","reader"})
  private Set<FictionComment> fCommentSet=new HashSet<FictionComment>(); //С˵�������б�
  public Fiction() {}
  
public Fiction(String fName, String fBrief, String fCoverUrl,String fAuthor) {
	this.fName = fName;
	this.fBrief = fBrief;
	this.fCoverUrl = fCoverUrl;
	this.fAuthor=fAuthor;
}

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="fId",unique=true,nullable=false)
public int getfId() {
	return fId;
}
public void setfId(int fId) {
	this.fId = fId;
}

@Column(name="fName",length=60,nullable=false)
public String getfName() {
	return fName;
}
public void setfName(String fName) {
	this.fName = fName;
}
@Column(name="fBrief",length=400,nullable=false)
public String getfBrief() {
	return fBrief;
}
public void setfBrief(String fBrief) {
	this.fBrief = fBrief;
}

@Column(name="fCoverUrl",length=120,nullable=false)
public String getfCoverUrl() {
	return fCoverUrl;
}
public void setfCoverUrl(String fCoverUrl) {
	this.fCoverUrl = fCoverUrl;
}


@Column(name="fAuthor",length=40,nullable=false)
public String getfAuthor() {
	return fAuthor;
}

public void setfAuthor(String fAuthor) {
	this.fAuthor = fAuthor;
}
/**
 * С˵����
 * 
 */
@JsonIgnore
@JsonIgnoreProperties(value= {"fictionSet"})
@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
@JoinColumn(name="fTypeId")
public FictionType getFtype() {
	return ftype;
}
public void setFtype(FictionType ftype) {
	this.ftype = ftype;
}
/**
 * С˵�½�
 * 
 */
@JsonIgnore
@OneToMany(mappedBy="fiction",cascade={CascadeType.REMOVE},fetch=FetchType.EAGER)
public Set<FictionChapter> getFchapterSet() {
	return fchapterSet;
}

public void setFchapterSet(Set<FictionChapter> fchapterSet) {
	this.fchapterSet = fchapterSet;
}
/**
 * С˵����
 * 
 */
@JsonIgnore
@ManyToMany(mappedBy="likeFictionSet",fetch=FetchType.EAGER)
public Set<Reader> getfLikeReaderSet() {
	return fLikeReaderSet;
}

public void setfLikeReaderSet(Set<Reader> fLikeReaderSet) {
	this.fLikeReaderSet = fLikeReaderSet;
}
/*
 * С˵�ղ�
 */
@JsonIgnore
@ManyToMany(mappedBy="collectFictionSet",fetch=FetchType.EAGER)
public Set<Reader> getfCollectReaderSet() {
	return fCollectReaderSet;
}

public void setfCollectReaderSet(Set<Reader> fCollectReaderSet) {
	this.fCollectReaderSet = fCollectReaderSet;
}

/**
 * С˵���Ķ�
 */
@JsonIgnore
@ManyToMany(mappedBy="readFictionSet",fetch=FetchType.EAGER)
public Set<Reader> getfReadReaderSet() {
	return fReadReaderSet;
}

public void setfReadReaderSet(Set<Reader> fReadReaderSet) {
	this.fReadReaderSet = fReadReaderSet;
}
/**
 * С˵�������б�
 * 
 */
@JsonIgnore
@OneToMany(mappedBy="fiction",fetch=FetchType.EAGER,cascade= {CascadeType.ALL})
public Set<FictionComment> getfCommentSet() {
	return fCommentSet;
}

public void setfCommentSet(Set<FictionComment> fCommentSet) {
	this.fCommentSet = fCommentSet;
}



  
}
