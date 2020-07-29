package com.jxl.jpa.bean;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 
 * @description 出入院信息表
 * 
 * @author Jxl
 * @date 2015年6月4日
 * @time 下午12:45:35
 * 
 */
@Entity
@Table(name="hdt_adinfomation")
public class ADInfomation {
	/**
	 * 出入院编号
	 */
	@Id
	@Column(name="Encounter",nullable = false)  
	private String Encounter;
	/**
	 * 医院原始ID（唯一）
	 */
	@Column
	private String OriginalID;
	/**
	 * 批次
	 */
	@Column
	private String BatchSign;
	/**
	 * 病人住院号
	 */
	@Column
	private String PatientAccount;
	/**
	 * 病人住院次数
	 */
	@Column
	private String VisitId;
	/**
	 * 病人身份证
	 */
	@Column
	private String IDCard;
	/**
	 * 健康卡号
	 */
	@Column
	private String HealthCardNo;
	/**
	 * 生日
	 */
	@Column
	private Date Birthday;
	/**
	 * 性别
	 */
	@Column
	private String Sex;
	/**
	 * 民族
	 */
	@Column
	private String Nation;
	/**
	 * 入院时间
	 */
	@Column
	private Date AdmissionDatetime;
	/**
	 * 入院科别
	 */
	@Column
	private String AdmissionDept;
	/**
	 * 入院途径
	 */
	@Column
	private String AdmissionSource;
	/**
	 * 入院情况
	 */
	@Column
	private String AdmissionType;
	/**
	 * 出院时间
	 */
	@Column
	private Date DischargeDatetime;
	/**
	 * 出院科别
	 */
	@Column
	private String DischargeDept;
	/**
	 * 离院方式
	 */
	@Column
	private String DischargeDisposition;
	/**
	 * 医疗付费方式
	 */
	@Column
	private String Payor;
	/**
	 * 主任医师
	 */
	@Column
	private String ChiefPhysicianName;
	/**
	 * 主任医师工号
	 */
	@Column
	private String ChiefPhysicianNO;
	/**
	 * 主治医师
	 */
	@Column
	private String MainPhysicianName;
	/**
	 * 主治医师工号
	 */
	@Column
	private String MainPhysicianNO;
	/**
	 * 主诊医师
	 */
	@Column
	private String DiagnosisPhysicianName;
	/**
	 * 主诊医师工号
	 */
	@Column
	private String DiagnosisPhysicianNO;
	/**
	 * 住院医师
	 */
	@Column
	private String LivePhysicianName;
	/**
	 * 住院医师工号
	 */
	@Column
	private String LivePhysicianNO;
	/**
	 * 出院医师
	 */
	@Column
	private String DischargePhysicianName;
	/**
	 * 出院医师工号
	 */
	@Column
	private String DischargePhysicianNO;
	/**
	 * 0 没有返院
		1 出院当天非预期再住院患者人数,<2 
		2 出院2-15天内再住院人数,
		3 出院16-31天内再住院人数,
	 */
	@Column
	private String FYQFY;
	/**
	 * 是否转科  Y 是    N  否    U  未知
	 */
	@Column
	private String IsTransferDept;
	/**
	 * 特殊科室标志    0、常规科室 1、疼痛科 2、康复科
	 */
	@Column
	private String DeptType;
	/**
	 * 标记坏数据
	 */
	@Column
	private int DSID; 
	/**
	 * 所有病种核算费用信息
	 */
	@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE},mappedBy = "adInfomation")
	private Charges charges;
	/**
	 * 所有诊断信息
	 */
	@OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE},mappedBy = "adInfomation")
	private List<Diagnosis> diagnosis;
	/**
	 * 所有手术信息
	 */
	@OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE},mappedBy = "adInfomation")
	private List<Procedure> procedures;
	
	public String getEncounter() {
		return Encounter;
	}

	public String getIDCard() {
		return IDCard;
	}

	public String getPatientAccount() {
		return PatientAccount;
	}

	public Date getBirthday() {
		return Birthday;
	}

	public String getNation() {
		return Nation;
	}

	public Date getAdmissionDatetime() {
		return AdmissionDatetime;
	}

	public String getAdmissionDept() {
		return AdmissionDept;
	}

	public String getAdmissionSource() {
		return AdmissionSource;
	}

	public String getAdmissionType() {
		return AdmissionType;
	}

	public Date getDischargeDatetime() {
		return DischargeDatetime;
	}

	public String getDischargeDept() {
		return DischargeDept;
	}

	public String getDischargeDisposition() {
		return DischargeDisposition;
	}

	public String getPayor() {
		return Payor;
	}

	public void setEncounter(String encounter) {
		Encounter = encounter;
	}

	public String getBatchSign() {
		return BatchSign;
	}

	public void setBatchSign(String batchSign) {
		BatchSign = batchSign;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public void setPatientAccount(String patientAccount) {
		PatientAccount = patientAccount;
	}

	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}

	public void setNation(String nation) {
		Nation = nation;
	}

	public void setAdmissionDatetime(Date admissionDatetime) {
		AdmissionDatetime = admissionDatetime;
	}

	public void setAdmissionDept(String admissionDept) {
		AdmissionDept = admissionDept;
	}

	public void setAdmissionSource(String admissionSource) {
		AdmissionSource = admissionSource;
	}

	public void setAdmissionType(String admissionType) {
		AdmissionType = admissionType;
	}

	public String getDeptType() {
		return DeptType;
	}

	public void setDeptType(String deptType) {
		DeptType = deptType;
	}

	public void setDischargeDatetime(Date dischargeDatetime) {
		DischargeDatetime = dischargeDatetime;
	}

	public void setDischargeDept(String dischargeDept) {
		DischargeDept = dischargeDept;
	}

	public void setDischargeDisposition(String dischargeDisposition) {
		DischargeDisposition = dischargeDisposition;
	}

	public void setPayor(String payor) {
		Payor = payor;
	}

	public Charges getCharges() {
		return charges;
	}

	public List<Diagnosis> getDiagnosis() {
		return diagnosis;
	}

	public List<Procedure> getProcedures() {
		return procedures;
	}

	public void setCharges(Charges charges) {
		this.charges = charges;
	}

	public void setDiagnosis(List<Diagnosis> diagnosis) {
		this.diagnosis = diagnosis;
	}

	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}

	public String getHealthCardNo() {
		return HealthCardNo;
	}

	public void setHealthCardNo(String healthCardNo) {
		HealthCardNo = healthCardNo;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getIsTransferDept() {
		return IsTransferDept;
	}

	public void setIsTransferDept(String isTransferDept) {
		IsTransferDept = isTransferDept;
	}

	public String getChiefPhysicianName() {
		return ChiefPhysicianName;
	}

	public void setChiefPhysicianName(String chiefPhysicianName) {
		ChiefPhysicianName = chiefPhysicianName;
	}

	public String getChiefPhysicianNO() {
		return ChiefPhysicianNO;
	}

	public void setChiefPhysicianNO(String chiefPhysicianNO) {
		ChiefPhysicianNO = chiefPhysicianNO;
	}

	public String getMainPhysicianName() {
		return MainPhysicianName;
	}

	public void setMainPhysicianName(String mainPhysicianName) {
		MainPhysicianName = mainPhysicianName;
	}

	public String getMainPhysicianNO() {
		return MainPhysicianNO;
	}

	public void setMainPhysicianNO(String mainPhysicianNO) {
		MainPhysicianNO = mainPhysicianNO;
	}

	public String getDiagnosisPhysicianName() {
		return DiagnosisPhysicianName;
	}

	public void setDiagnosisPhysicianName(String diagnosisPhysicianName) {
		DiagnosisPhysicianName = diagnosisPhysicianName;
	}

	public String getDiagnosisPhysicianNO() {
		return DiagnosisPhysicianNO;
	}

	public void setDiagnosisPhysicianNO(String diagnosisPhysicianNO) {
		DiagnosisPhysicianNO = diagnosisPhysicianNO;
	}

	public String getLivePhysicianName() {
		return LivePhysicianName;
	}

	public void setLivePhysicianName(String livePhysicianName) {
		LivePhysicianName = livePhysicianName;
	}

	public String getLivePhysicianNO() {
		return LivePhysicianNO;
	}

	public void setLivePhysicianNO(String livePhysicianNO) {
		LivePhysicianNO = livePhysicianNO;
	}

	public String getDischargePhysicianName() {
		return DischargePhysicianName;
	}

	public void setDischargePhysicianName(String dischargePhysicianName) {
		DischargePhysicianName = dischargePhysicianName;
	}

	public String getDischargePhysicianNO() {
		return DischargePhysicianNO;
	}

	public void setDischargePhysicianNO(String dischargePhysicianNO) {
		DischargePhysicianNO = dischargePhysicianNO;
	}

	public String getOriginalID() {
		return OriginalID;
	}

	public void setOriginalID(String originalID) {
		OriginalID = originalID;
	}

	public String getFYQFY() {
		return FYQFY;
	}

	public void setFYQFY(String fYQFY) {
		FYQFY = fYQFY;
	}

	public int getDSID() {
		return DSID;
	}

	public void setDSID(int dSID) {
		DSID = dSID;
	}

	public String getVisitId() {
		return VisitId;
	}

	public void setVisitId(String visitId) {
		VisitId = visitId;
	}

	@Override
	public String toString() {
		return "ADInfomation [Encounter=" + Encounter + ", OriginalID=" + OriginalID + ", IDCard=" + IDCard + ", PatientAccount=" + PatientAccount + ", HealthCardNo=" + HealthCardNo + ", Birthday="
				+ Birthday + ", Sex=" + Sex + ", Nation=" + Nation + ", AdmissionDatetime=" + AdmissionDatetime + ", AdmissionDept=" + AdmissionDept + ", AdmissionSource=" + AdmissionSource
				+ ", AdmissionType=" + AdmissionType + ", DischargeDatetime=" + DischargeDatetime + ", DischargeDept=" + DischargeDept + ", DischargeDisposition=" + DischargeDisposition + ", Payor="
				+ Payor + ", ChiefPhysicianName=" + ChiefPhysicianName + ", ChiefPhysicianNO=" + ChiefPhysicianNO + ", MainPhysicianName=" + MainPhysicianName + ", MainPhysicianNO=" + MainPhysicianNO
				+ ", DiagnosisPhysicianName=" + DiagnosisPhysicianName + ", DiagnosisPhysicianNO=" + DiagnosisPhysicianNO + ", LivePhysicianName=" + LivePhysicianName + ", LivePhysicianNO="
				+ LivePhysicianNO + ", DischargePhysicianName=" + DischargePhysicianName + ", DischargePhysicianNO=" + DischargePhysicianNO + ", DSID=" + DSID + ", charges="
				+ charges + ", diagnosis=" + diagnosis + ", procedures=" + procedures + "]";
	}
}
