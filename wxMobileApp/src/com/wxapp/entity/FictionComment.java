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
 * С˵���۱�fcomment((commentId)(����)��
 * fId(���),readerId(���)��commentContent(��������),commentTime(����ʱ��))
 * @author li
 *
 */
@Entity
@Table(name="fcomment",catalog="fictiondb")
public class FictionComment {

	public FictionComment() {}
	
     public FictionComment(String commentContent, String commentTime) {
		this.commentContent = commentContent;
		this.commentTime = commentTime;
	}

	private int commentId;
     private Reader reader;
     private Fiction fiction;
     private String commentContent;
     private String commentTime;
     
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="commentId",unique=true,nullable=false)
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	/**
	 * ��������
	 *String
	 */
  @Column(name="commentContent",length=100,nullable=false)
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	/**
	 * ����ʱ��
	 *String
	 */
	@Column(name="commentTime",length=20,nullable=false)
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
     	
/**
 * ������
 *
 */
	@JsonIgnoreProperties(value= {"userCommentSet","likeFictionSet","collectFictionSet","readFictionSet"})
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="readerId")
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	
	/**
	 * ����С˵
	 *
	 */
	@JsonIgnoreProperties(value= {"fCommentSet","ftype","fchapterSet","fLikeReaderSet","fCollectReaderSet","fReadReaderSet"})
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fId")
	public Fiction getFiction(){
		return fiction;
	}
	public void setFiction(Fiction fiction) {
		this.fiction = fiction;
	}
	
}
