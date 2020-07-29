package com.jxl.jpa.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @description 手术表
 * 
 * @author Jxl
 * @date 2015年6月4日
 * @time 下午12:41:03
 * 
 */
@Entity
@Table(name="hdt_procedure")
public class Procedure {

	/**
	 * 手术ID
	 */
	@Id
	@Column(name="ProcedureID",nullable = false)  
    @GeneratedValue
	private Integer ProcedureID;
	/**
	 * 医院原始ID
	 */
	@Column
	private String OriginalID;
	/**
	 * 手术ICD版本
	 */
	@Column
	private String ProcedureICDVer;
	/**
	 * 手术ICD编码
	 */
	@Column
	private String ProcedureICDCode;
	/**
	 * 手术ICD编码描述
	 */
	@Column
	private String ProcedureICDDesc;
	/**
	 * 手术日期
	 */
	@Column
	private Date ProcedureDatetime;
	/**
	 * 是否主要手术
	 */
	@Column
	private Character IsFristProcedure;
	/**
	 * 手术医师
	 */
	@Column
	private String PhysicianName;
	/**
	 * 手术医师工号
	 */
	@Column
	private String PhysicianNO;
	/**
	 * 手术级别
	 */
	@Column
	private String ProcedureLevel;
	/**
	 * 标记坏数据
	 */
	@Column
	private int DSID;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Encounter")
	private ADInfomation adInfomation;

	@JsonIgnore  
	public Integer getProcedureID() {
		return ProcedureID;
	}

	public Date getProcedureDatetime() {
		return ProcedureDatetime;
	}

	public Character getIsFristProcedure() {
		return IsFristProcedure;
	}


	public void setProcedureID(Integer procedureID) {
		ProcedureID = procedureID;
	}

	public void setProcedureDatetime(Date procedureDatetime) {
		ProcedureDatetime = procedureDatetime;
	}

	public void setIsFristProcedure(Character isFristProcedure) {
		IsFristProcedure = isFristProcedure;
	}

	public String getProcedureICDVer() {
		return ProcedureICDVer;
	}

	public void setProcedureICDVer(String procedureICDVer) {
		ProcedureICDVer = procedureICDVer;
	}

	public String getProcedureICDCode() {
		return ProcedureICDCode;
	}

	public void setProcedureICDCode(String procedureICDCode) {
		ProcedureICDCode = procedureICDCode;
	}

	public String getPhysicianName() {
		return PhysicianName;
	}

	public void setPhysicianName(String physicianName) {
		PhysicianName = physicianName;
	}

	public String getPhysicianNO() {
		return PhysicianNO;
	}

	public void setPhysicianNO(String physicianNO) {
		PhysicianNO = physicianNO;
	}

	public String getOriginalID() {
		return OriginalID;
	}

	public void setOriginalID(String originalID) {
		OriginalID = originalID;
	}

	public int getDSID() {
		return DSID;
	}

	public void setDSID(int dSID) {
		DSID = dSID;
	}

	public String getProcedureICDDesc() {
		return ProcedureICDDesc;
	}

	public void setProcedureICDDesc(String procedureICDDesc) {
		ProcedureICDDesc = procedureICDDesc;
	}

	public String getProcedureLevel() {
		return ProcedureLevel;
	}

	public void setProcedureLevel(String procedureLevel) {
		ProcedureLevel = procedureLevel;
	}

	@Override
	public String toString() {
		return "Procedure [ProcedureID=" + ProcedureID + ", OriginalID=" + OriginalID + ", ProcedureICDVer=" + ProcedureICDVer + ", ProcedureICDCode=" + ProcedureICDCode
				+ ", ProcedureDatetime=" + ProcedureDatetime + ", IsFristProcedure=" + IsFristProcedure + ", PhysicianName=" + PhysicianName + ", PhysicianNO=" + PhysicianNO + ", DSID=" + DSID + "]";
	}
}
