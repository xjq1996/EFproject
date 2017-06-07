package com.ef.video.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="ef_user")
public class User extends BaseEntity {

}
