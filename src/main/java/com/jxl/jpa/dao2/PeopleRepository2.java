package com.jxl.jpa.dao2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jxl.jpa.bean.People;

public interface PeopleRepository2 extends JpaRepository<People, Integer> {

	List<People> findByLastName(String lastName);

	People findByLastNameAndFirstName(String lastName, String firstName);

	//自定义@Query时方法名称可以完全自定义，不需要按照规则
    @Query("select p from People p where p.lastName=:lastName and p.firstName=:firstName")
    People customQuery(@Param("lastName") String lastName, @Param("firstName") String firstName);

    
}