package com.wxapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 小说章节表fchapter
 * (chapterId(主键),fId（小说id)外键，chapterNum (章节号),
 * chapterName章节名,chapterContent章节内容)
 * @author li
 *
 */
@Entity
@Table(name="fchapter",catalog="fictiondb")
public class FictionChapter {
	
  public FictionChapter() {}
private int chapterId;
  private Fiction fiction;
  private int chapterNum;
  private String chapterName;
  private String chapterContent;
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="chapterId",nullable=false,unique=true)
public int getChapterId() {
	return chapterId;
}
public void setChapterId(int chapterId) {
	this.chapterId = chapterId;
}

@JsonIgnoreProperties(value= {"fchapterSet"})
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="fId")
public Fiction getFiction() {
	return fiction;
}
public void setFiction(Fiction fiction) {
	this.fiction = fiction;
}

@Column(name="chapterNum",nullable=false)
public int getChapterNum() {
	return chapterNum;
}
public void setChapterNum(int chapterNum) {
	this.chapterNum = chapterNum;
}

@Column(name="chapterName",length=60,nullable=false)
public String getChapterName() {
	return chapterName;
}
public void setChapterName(String chapterName) {
	this.chapterName = chapterName;
}

@Column(name="chapterContent",length=6000,nullable=true)
public String getChapterContent() {
	return chapterContent;
}
public void setChapterContent(String chapterContent) {
	this.chapterContent = chapterContent;
}
  
  
  
}
