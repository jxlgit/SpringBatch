package com.jxl.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jxl.jpa.bean.People;

/**
 * 修改注解值
 * @author Administrator
 *
 */
public class TestAnnotationSet {
	
	private static final Logger log = LoggerFactory.getLogger(TestAnnotationSet.class);
	
	public static void main(String[] args) throws Exception {
		TestAnnotationSet annotationSet = new TestAnnotationSet();
		annotationSet.modifyClassAnnotation();
		annotationSet.modifyAttributesAnnotation();
	}
	
	/**
	 * 修改类上的注解
	 * 注意，修改完后是全局修改，以后其他实例化的类注解值都已经改变
	 * 
	 * @throws Exception
	 */
	public void modifyClassAnnotation() throws Exception {

		Table table = People.class.getAnnotation(Table.class);
		log.info("【Table】注解原始值：" + table.name());
		// 获取 该注解 这个代理实例所持有的 InvocationHandler
		InvocationHandler h = Proxy.getInvocationHandler(table);
		// 获取 AnnotationInvocationHandler 的 memberValues 字段
		Field hField = h.getClass().getDeclaredField("memberValues");
		// 因为这个字段事 private final 修饰，所以要打开权限
		hField.setAccessible(true);
		// 获取 memberValues
		@SuppressWarnings("unchecked")
		Map<String, Object> memberValues = (Map<String, Object>) hField.get(h);
		// 修改 注解中 属性值
		memberValues.put("name", "ddd");

		Table table1 = People.class.getAnnotation(Table.class);
		log.info("【Table】注解修改值：" + table1.name());
	}
	
	/**
	 * 修改属性上的注解
	 * 注意，修改完后是全局修改，以后其他实例化的类注解值都已经改变
	 * 
	 * @throws Exception
	 */
	public void modifyAttributesAnnotation() throws Exception{
		Field field = People.class.getDeclaredField("personId");
		Column column = field.getAnnotation(Column.class);
		log.info("【Column】注解原始值：" + column.name());
		// 获取 该注解 这个代理实例所持有的 InvocationHandler
		InvocationHandler h = Proxy.getInvocationHandler(column);
		// 获取 AnnotationInvocationHandler 的 memberValues 字段
		Field hField = h.getClass().getDeclaredField("memberValues");
		// 因为这个字段事 private final 修饰，所以要打开权限
		hField.setAccessible(true);
		// 获取 memberValues
		@SuppressWarnings("unchecked")
		Map<String, Object> memberValues = (Map<String, Object>) hField.get(h);
		// 修改 注解中 属性值
		memberValues.put("name", "ddd");

		People people = new People();
		Field field1 = people.getClass().getDeclaredField("personId");
		Column column1 = field1.getAnnotation(Column.class);
		log.info("【Column】注解修改值：" + column1.name());
	}

}
