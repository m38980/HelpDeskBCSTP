package com.bcstp.helpdesk.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String name;
	private String department;
	private String updatedBy;
	private String tecnico;
	private String prioridade;
	private String status;
	private String descricao;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedOn;

	public Student() {
	}

	public Student(Integer id, 
			      String name, 
			      String department, 
			      String updatedBy, 
			      String tecnico,
			      String prioridade, 
			      String ststus, 
			      String descricao, 
			      Date updatedOn) {
		super();
		Id = id;
		this.name = name;
		this.department = department;
		this.updatedBy = updatedBy;
		this.tecnico = tecnico;
		this.descricao = descricao;
		this.prioridade = prioridade;
		this.status = status;
		this.updatedOn = updatedOn;
	}



	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	@Override
	public String toString() {
		return "Student [Id=" + Id + ", name=" + name + ", department=" + department + ", updatedBy=" + updatedBy
				+ ", tecnico=" + tecnico + ", prioridade=" + prioridade + ", status=" + status + ", descricao="
				+ descricao + ", updatedOn=" + updatedOn + "]";
	}

}
