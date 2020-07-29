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
 * @description 诊断表
 * 
 * @author Jxl
 * @date 2015年6月4日
 * @time 下午12:42:08
 * 
 */
@Entity
@Table(name="hdt_diagnosis")
public class Diagnosis {

	/**
	 * 诊断ID
	 */
	@Id
	@Column(name="DiagnosisID",nullable = false)  
    @GeneratedValue
	private Integer DiagnosisID;
	/**
	 * 医院原始ID
	 */
	@Column
	private String OriginalID;
	/**
	 * 诊断ICD版本
	 */
	@Column
	private String DiagnosisICDVer;
	/**
	 * 诊断ICD编码
	 */
	@Column
	private String DiagnosisICDCode;
	/**
	 * 诊断ICD编码描述
	 */
	@Column
	private String DiagnosisICDDesc;
	/**
	 * 主要诊断确诊日期
	 */
	@Column
	private Date DiagnosisDatetime;
	/**
	 * 是否主要诊断
	 */
	@Column
	private Character IsFristDiagnosis;
	/**
	 * 入院病情（POA）
	 */
	@Column
	private Character IsPOA;
	/**
	 * 标记坏数据
	 */
	@Column
	private int DSID;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Encounter")
	private ADInfomation adInfomation;

	@JsonIgnore  
	public Integer getDiagnosisID() {
		return DiagnosisID;
	}

	public Date getDiagnosisDatetime() {
		return DiagnosisDatetime;
	}

	public Character getIsFristDiagnosis() {
		return IsFristDiagnosis;
	}

	public Character getIsPOA() {
		return IsPOA;
	}

	public void setDiagnosisID(Integer diagnosisID) {
		DiagnosisID = diagnosisID;
	}

	public void setDiagnosisDatetime(Date diagnosisDatetime) {
		DiagnosisDatetime = diagnosisDatetime;
	}

	public void setIsFristDiagnosis(Character isFristDiagnosis) {
		IsFristDiagnosis = isFristDiagnosis;
	}

	public void setIsPOA(Character isPOA) {
		IsPOA = isPOA;
	}

	public String getDiagnosisICDVer() {
		return DiagnosisICDVer;
	}

	public void setDiagnosisICDVer(String diagnosisICDVer) {
		DiagnosisICDVer = diagnosisICDVer;
	}

	public String getDiagnosisICDCode() {
		return DiagnosisICDCode;
	}

	public void setDiagnosisICDCode(String diagnosisICDCode) {
		DiagnosisICDCode = diagnosisICDCode;
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

	public String getDiagnosisICDDesc() {
		return DiagnosisICDDesc;
	}

	public void setDiagnosisICDDesc(String diagnosisICDDesc) {
		DiagnosisICDDesc = diagnosisICDDesc;
	}

	@Override
	public String toString() {
		return "Diagnosis [DiagnosisID=" + DiagnosisID + ", OriginalID=" + OriginalID + ", DiagnosisICDVer=" + DiagnosisICDVer + ", DiagnosisICDCode=" + DiagnosisICDCode
				+ ", DiagnosisDatetime=" + DiagnosisDatetime + ", IsFristDiagnosis=" + IsFristDiagnosis + ", IsPOA=" + IsPOA + ", DSID=" + DSID + "]";
	}
	
}
