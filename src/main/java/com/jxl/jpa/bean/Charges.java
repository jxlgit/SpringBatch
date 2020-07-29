package com.jxl.jpa.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @description 病种核算费用表
 * 
 * @author Jxl
 * @date 2015年6月4日
 * @time 下午12:43:12
 * 
 */
@Entity
@Table(name="hdt_charges")
public class Charges implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Encounter")
	private ADInfomation adInfomation;
	
	/**
	 * 医院原始ID
	 */
	@Column
	private String OriginalID;
	/**
	 * 总费用
	 */
	@Column
	private Double TypeZF;
	/**
	 * 自付金额
	 */
	@Column
	private Double ZFJE;
	/**
	 * 综合医疗服务费
	 */
	@Column
	private Double TypeZHYLFWF;
	/**
	 *   一般医疗服务费
	 */
	@Column
	private Double YLFUF;
	/**
	 *   一般治疗操作费	
	 */
	@Column
	private Double ZLCZF;
	/**
	 *   护理费
	 */
	@Column
	private Double HLF;
	/**
	 *   其他费用
	 */
	@Column
	private Double QTF;
	/**
	 * 诊断费
	 */
	@Column
	private Double TypeZDF;
	/**
	 *   病理诊断费	
	 */
	@Column
	private Double BLZDF;
	/**
	 *   实验室诊断费	
	 */
	@Column
	private Double SYSZDF;
	/**
	 *   影像学诊断费	
	 */
	@Column
	private Double YXXZDF;
	/**
	 *   临床诊断项目费	
	 */
	@Column
	private Double LCZDXMF;
	/**
	 * 治疗费	
	 */
	@Column
	private Double TypeZLF;
	/**
	 *   非手术治疗项目费	
	 */
	@Column
	private Double FSSZLXMF;
	/**
	 *   手术治疗费	
	 */
	@Column
	private Double SSZLF;
	/**
	 * 康复费	
	 */
	@Column
	private Double TypeKFF;
	/**
	 * 中医治疗费	
	 */
	@Column
	private Double TypeZYZLF;
	/**
	 * 西药费	
	 */
	@Column
	private Double TypeXYF;
	/**
	 *   抗菌药物费用	
	 */
	@Column
	private Double KJYWF;
	/**
	 * 中药费	
	 */
	@Column
	private Double TypeZYF;
	/**
	 *   中成药费	
	 */
	@Column
	private Double ZChengYF;
	/**
	 *   中草药费	
	 */
	@Column
	private Double ZCaoYF;
	/**
	 *  血液和血液制品费	
	 */
	@Column
	private Double TypeXYZPF;
	/**
	 *    血费	
	 */
	@Column
	private Double XF;
	/**
	 *    白蛋白类制品费	
	 */
	@Column
	private Double BDBLZPF;
	/**
	 *    球蛋白类制品费	
	 */
	@Column
	private Double QDBLZPF;
	/**
	 *    凝血因子类制品费	
	 */
	@Column
	private Double NXYZLZPF;
	/**
	 *    细胞因子类制品费	
	 */
	@Column
	private Double XBYZLZPF;
	/**
	 *  耗材费	
	 */
	@Column
	private Double TypeHCF;
	/**
	 *    检查用一次性医用材料费	
	 */
	@Column
	private Double HCYYCLF;
	/**
	 *    治疗用一次性医用材料费	
	 */
	@Column
	private Double YYCLF;
	/**
	 *    手术用一次性医用材料费	
	 */
	@Column
	private Double YCXYYCLF;
	/**
	 *  其他费	
	 */
	@Column
	private Double TypeQTF;

	/**
	 * 标记坏数据
	 */
	@Column
	private int DSID; 

	public String getOriginalID() {
		return OriginalID;
	}

	public void setOriginalID(String originalID) {
		OriginalID = originalID;
	}

	public Double getTypeZF() {
		return TypeZF;
	}

	public void setTypeZF(Double typeZF) {
		TypeZF = typeZF;
	}

	public Double getZFJE() {
		return ZFJE;
	}

	public void setZFJE(Double zFJE) {
		ZFJE = zFJE;
	}

	public Double getTypeZHYLFWF() {
		return TypeZHYLFWF;
	}

	public void setTypeZHYLFWF(Double typeZHYLFWF) {
		TypeZHYLFWF = typeZHYLFWF;
	}

	public Double getYLFUF() {
		return YLFUF;
	}

	public void setYLFUF(Double yLFUF) {
		YLFUF = yLFUF;
	}

	public Double getZLCZF() {
		return ZLCZF;
	}

	public void setZLCZF(Double zLCZF) {
		ZLCZF = zLCZF;
	}

	public Double getHLF() {
		return HLF;
	}

	public void setHLF(Double hLF) {
		HLF = hLF;
	}

	public Double getQTF() {
		return QTF;
	}

	public void setQTF(Double qTF) {
		QTF = qTF;
	}

	public Double getTypeZDF() {
		return TypeZDF;
	}

	public void setTypeZDF(Double typeZDF) {
		TypeZDF = typeZDF;
	}

	public Double getBLZDF() {
		return BLZDF;
	}

	public void setBLZDF(Double bLZDF) {
		BLZDF = bLZDF;
	}

	public Double getSYSZDF() {
		return SYSZDF;
	}

	public void setSYSZDF(Double sYSZDF) {
		SYSZDF = sYSZDF;
	}

	public Double getYXXZDF() {
		return YXXZDF;
	}

	public void setYXXZDF(Double yXXZDF) {
		YXXZDF = yXXZDF;
	}

	public Double getLCZDXMF() {
		return LCZDXMF;
	}

	public void setLCZDXMF(Double lCZDXMF) {
		LCZDXMF = lCZDXMF;
	}

	public Double getTypeZLF() {
		return TypeZLF;
	}

	public void setTypeZLF(Double typeZLF) {
		TypeZLF = typeZLF;
	}

	public Double getFSSZLXMF() {
		return FSSZLXMF;
	}

	public void setFSSZLXMF(Double fSSZLXMF) {
		FSSZLXMF = fSSZLXMF;
	}

	public Double getSSZLF() {
		return SSZLF;
	}

	public void setSSZLF(Double sSZLF) {
		SSZLF = sSZLF;
	}

	public Double getTypeKFF() {
		return TypeKFF;
	}

	public void setTypeKFF(Double typeKFF) {
		TypeKFF = typeKFF;
	}

	public Double getTypeZYZLF() {
		return TypeZYZLF;
	}

	public void setTypeZYZLF(Double typeZYZLF) {
		TypeZYZLF = typeZYZLF;
	}

	public Double getTypeXYF() {
		return TypeXYF;
	}

	public void setTypeXYF(Double typeXYF) {
		TypeXYF = typeXYF;
	}

	public Double getKJYWF() {
		return KJYWF;
	}

	public void setKJYWF(Double kJYWF) {
		KJYWF = kJYWF;
	}

	public Double getTypeZYF() {
		return TypeZYF;
	}

	public void setTypeZYF(Double typeZYF) {
		TypeZYF = typeZYF;
	}

	public Double getZChengYF() {
		return ZChengYF;
	}

	public void setZChengYF(Double zChengYF) {
		ZChengYF = zChengYF;
	}

	public Double getZCaoYF() {
		return ZCaoYF;
	}

	public void setZCaoYF(Double zCaoYF) {
		ZCaoYF = zCaoYF;
	}

	public Double getTypeXYZPF() {
		return TypeXYZPF;
	}

	public void setTypeXYZPF(Double typeXYZPF) {
		TypeXYZPF = typeXYZPF;
	}

	public Double getXF() {
		return XF;
	}

	public void setXF(Double xF) {
		XF = xF;
	}

	public Double getBDBLZPF() {
		return BDBLZPF;
	}

	public void setBDBLZPF(Double bDBLZPF) {
		BDBLZPF = bDBLZPF;
	}

	public Double getQDBLZPF() {
		return QDBLZPF;
	}

	public void setQDBLZPF(Double qDBLZPF) {
		QDBLZPF = qDBLZPF;
	}

	public Double getNXYZLZPF() {
		return NXYZLZPF;
	}

	public void setNXYZLZPF(Double nXYZLZPF) {
		NXYZLZPF = nXYZLZPF;
	}

	public Double getXBYZLZPF() {
		return XBYZLZPF;
	}

	public void setXBYZLZPF(Double xBYZLZPF) {
		XBYZLZPF = xBYZLZPF;
	}

	public Double getTypeHCF() {
		return TypeHCF;
	}

	public void setTypeHCF(Double typeHCF) {
		TypeHCF = typeHCF;
	}

	public Double getHCYYCLF() {
		return HCYYCLF;
	}

	public void setHCYYCLF(Double hCYYCLF) {
		HCYYCLF = hCYYCLF;
	}

	public Double getYYCLF() {
		return YYCLF;
	}

	public void setYYCLF(Double yYCLF) {
		YYCLF = yYCLF;
	}

	public Double getYCXYYCLF() {
		return YCXYYCLF;
	}

	public void setYCXYYCLF(Double yCXYYCLF) {
		YCXYYCLF = yCXYYCLF;
	}

	public Double getTypeQTF() {
		return TypeQTF;
	}

	public void setTypeQTF(Double typeQTF) {
		TypeQTF = typeQTF;
	}

	public int getDSID() {
		return DSID;
	}

	public void setDSID(int dSID) {
		DSID = dSID;
	}

	@Override
	public String toString() {
		return "Charges2 [OriginalID=" + OriginalID + ", TypeZF=" + TypeZF + ", ZFJE=" + ZFJE + ", TypeZHYLFWF=" + TypeZHYLFWF + ", YLFUF="
				+ YLFUF + ", ZLCZF=" + ZLCZF + ", HLF=" + HLF + ", QTF=" + QTF + ", TypeZDF=" + TypeZDF + ", BLZDF=" + BLZDF + ", SYSZDF=" + SYSZDF + ", YXXZDF=" + YXXZDF + ", LCZDXMF=" + LCZDXMF
				+ ", TypeZLF=" + TypeZLF + ", FSSZLXMF=" + FSSZLXMF + ", SSZLF=" + SSZLF + ", TypeKFF=" + TypeKFF + ", TypeZYZLF=" + TypeZYZLF + ", TypeXYF=" + TypeXYF + ", KJYWF=" + KJYWF
				+ ", TypeZYF=" + TypeZYF + ", ZChengYF=" + ZChengYF + ", ZCaoYF=" + ZCaoYF + ", TypeXYZPF=" + TypeXYZPF + ", XF=" + XF + ", BDBLZPF=" + BDBLZPF + ", QDBLZPF=" + QDBLZPF
				+ ", NXYZLZPF=" + NXYZLZPF + ", XBYZLZPF=" + XBYZLZPF + ", TypeHCF=" + TypeHCF + ", HCYYCLF=" + HCYYCLF + ", YYCLF=" + YYCLF + ", YCXYYCLF=" + YCXYYCLF + ", TypeQTF=" + TypeQTF
				+ ", DSID=" + DSID + "]";
	}

	

}
