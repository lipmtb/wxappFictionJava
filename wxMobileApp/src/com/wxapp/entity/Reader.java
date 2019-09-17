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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 读者表reader
 * （readerId(主键),openId（当前应用唯一标识）,token（会话标识）,
 * nickName（读者昵称），avatarUrl(读者头像)，readerGender（读者性别））
 * @author li
 *
 */
@Entity
@Table(name="reader",catalog="fictiondb")
public class Reader {
	public Reader() {}
   public Reader(String nickName, String avatarUrl, String readerGender) {
		this.nickName = nickName;
		this.avatarUrl = avatarUrl;
		this.readerGender = readerGender;
	}
private int readerId;
   private String openId;
   private String token;
   private String nickName;
   private String avatarUrl;
   private String readerGender;
   private Set<Fiction> likeFictionSet=new HashSet<Fiction>();//读者点赞
   private Set<Fiction> collectFictionSet=new HashSet<Fiction>();//读者收藏
   private Set<Fiction> readFictionSet=new HashSet<Fiction>();//读者已阅读
   private Set<FictionComment> userCommentSet=new HashSet<FictionComment>();//读者评论列表
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="readerId",nullable=false,unique=true)
public int getReaderId() {
	return readerId;
}
public void setReaderId(int readerId) {
	this.readerId = readerId;
}

@Column(name="openId",length=60,nullable=false,unique=true)
public String getOpenId() {
	return openId;
}
public void setOpenId(String openId) {
	this.openId = openId;
}

@Column(name="token",length=200,nullable=false)
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}

@Column(name="nickName",length=60,nullable=false)
public String getNickName() {
	return nickName;
}
public void setNickName(String nickName) {
	this.nickName = nickName;
}

@Column(name="avatarUrl",length=400,nullable=false)
public String getAvatarUrl() {
	return avatarUrl;
}
public void setAvatarUrl(String avatarUrl) {
	this.avatarUrl = avatarUrl;
}

@Column(name="readerGender",length=4,nullable=true)
public String getReaderGender() {
	return readerGender;
}
public void setReaderGender(String readerGender) {
	this.readerGender = readerGender;
}
/**
 * 读者点赞
 * 
 */
@JsonIgnoreProperties(value= {"fLikeReaderSet"})
@ManyToMany(fetch=FetchType.EAGER)
@JoinTable(name="flike",joinColumns= {@JoinColumn(name="readerId")}, inverseJoinColumns= {@JoinColumn(name="fid")})
public Set<Fiction> getLikeFictionSet() {
	return likeFictionSet;
}
public void setLikeFictionSet(Set<Fiction> likeFictionSet) {
	this.likeFictionSet = likeFictionSet;
}
/**
 * 读者收藏
 */
@JsonIgnoreProperties(value= {"fCollectReaderSet"})
@ManyToMany(fetch=FetchType.EAGER)
@JoinTable(name="fcollect",joinColumns= {@JoinColumn(name="readerId")}, inverseJoinColumns= {@JoinColumn(name="fid")})
public Set<Fiction> getCollectFictionSet() {
	return collectFictionSet;
}
public void setCollectFictionSet(Set<Fiction> collectFictionSet) {
	this.collectFictionSet = collectFictionSet;
}

/**
 * 读者阅读
 */
@JsonIgnoreProperties(value= {"fReadReaderSet"})
@ManyToMany(fetch=FetchType.EAGER)
@JoinTable(name="fread",joinColumns= {@JoinColumn(name="readerId")}, inverseJoinColumns= {@JoinColumn(name="fid")})  
public Set<Fiction> getReadFictionSet() {
	return readFictionSet;
}
public void setReadFictionSet(Set<Fiction> readFictionSet) {
	this.readFictionSet = readFictionSet;
}

/**
 * 读者评论列表
 * 
 */
@OneToMany(mappedBy="reader",fetch=FetchType.EAGER,cascade= {CascadeType.ALL})
public Set<FictionComment> getUserCommentSet() {
	return userCommentSet;
}
public void setUserCommentSet(Set<FictionComment> userCommentSet) {
	this.userCommentSet = userCommentSet;
}




   
}
