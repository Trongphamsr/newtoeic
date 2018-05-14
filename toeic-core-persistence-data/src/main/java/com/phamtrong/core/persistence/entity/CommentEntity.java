package com.phamtrong.core.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name= "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name="content")
    private String content;

    @Column(name="createddate")
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name="userid")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="listenguidelineid")
    private ListenguidelineEntity listenguidelineEntity;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ListenguidelineEntity getListenguidelineEntity() {
        return listenguidelineEntity;
    }

    public void setListenguidelineEntity(ListenguidelineEntity listenguidelineEntity) {
        this.listenguidelineEntity = listenguidelineEntity;
    }
}
