package com.jxl.jpa.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="people_dept")
public class PeopleDept implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id",nullable = false)  
    @GeneratedValue
	private Integer id;
	
	@Column(name="dept_name")  
    private String deptName;
	
	@Column(name="dept_address")  
    private String deptAddress;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
	private People people;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptAddress() {
		return deptAddress;
	}

	public void setDeptAddress(String deptAddress) {
		this.deptAddress = deptAddress;
	}

	@Override
	public String toString() {
		return "PeopleDept [id=" + id + ", deptName=" + deptName + ", deptAddress=" + deptAddress + "]";
	}
	
	
    
}
