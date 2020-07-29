package com.jxl.jpa.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="people")
public class People implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="person_id",nullable = false)  
    @GeneratedValue
	private Integer personId;
	
	@Column(name="first_name")  
    private String lastName;
	
	@Column(name="last_name")  
    private String firstName;
	
	/**
	 *  fetch=FetchType.LAZY为默认的数据延迟加载，fetch=FetchType.EAGER为急加载。
		cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE}
		其中： 
		CascadeType.PERSIST级联新增（又称级联保存）； 
		CascadeType.MERGE:级联合并（级联更新）； 
		CascadeType.REMOVE:级联删除； 
		CascadeType.REFRESH:级联刷新 
		CascadeType.ALL:以上四种都是； 
		一般采用CascadeType.MERGE:级联合并（级联更新）即可。默认值是均不进行关联。
		
		
		数据库中1对多的关系，关联关系总是被多方维护的即外键建在多方，我们在单方对象的@OneToMany(mappedby="") 把关系的维护交给多方对象的属性去维护关系
                        对于mappedBy复习下：
		　　a） 只有OneToOne,OneToMany,ManyToMany上才有mappedBy属性，ManyToOne不存在该属性；		
		　　b） mappedBy标签一定是定义在the owned side（被拥有方的），他指向theowning side（拥有方）；		
		　　c） 关系的拥有方负责关系的维护，在拥有方建立外键。所以用到@JoinColumn		
		　　d）mappedBy跟JoinColumn/JoinTable总是处于互斥的一方

	 */
	@OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE},mappedBy = "people")
	private List<PeopleDept> peopleDepts;
	
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public List<PeopleDept> getPeopleDepts() {
		return peopleDepts;
	}
	public void setPeopleDepts(List<PeopleDept> peopleDepts) {
		this.peopleDepts = peopleDepts;
	}
	@Override
	public String toString() {
		return "People [personId=" + personId + ", lastName=" + lastName + ", firstName=" + firstName + "]";
	}
    
}
