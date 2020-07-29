package com.jxl.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jxl.jpa.bean.ADInfomation;

/**
 * 
 * ClassName: AnnotationSetComm
 * date: 2018年5月12日 下午6:19:55
 * 
 * @author jiangxiaolong 
 * @version  
 * @since JDK 1.8 
 *
 * @describe 修改注解值
 *
 */
public class AnnotationSetComm {
	
	private static final Logger log = LoggerFactory.getLogger(TestAnnotationSet.class);
	
	public static void main(String[] args) {
		AnnotationSetComm.modifyColumnAttributesAnnotation(ADInfomation.class, "Encounter", "name", "123");
		AnnotationSetComm.modifyTableClassAnnotation(ADInfomation.class, "name", "123");
	}
	

	/**
	 * 修改类上的注解
	 * 注意，修改完后是全局修改，以后其他实例化的类注解值都已经改变
	 * 
	 * @param modifyClassz 修改的类
	 * @param annotationKey Table注解key名称
	 * @param annotationValue Table注解key对应的Value名称
	 */
	public static <T> void modifyTableClassAnnotation(Class<T> modifyClassz, String annotationKey, String annotationValue) {
		try {
			Table table = modifyClassz.getAnnotation(Table.class);
			log.info("【Table】注解原始值：" + table.name());
			// 获取 该注解 这个代理实例所持有的 InvocationHandler
			InvocationHandler h = Proxy.getInvocationHandler(table);
			// 获取 AnnotationInvocationHandler 的 memberValues 字段
			Field hField = h.getClass().getDeclaredField("memberValues");
			// 因为这个字段是 private final 修饰，所以要打开权限
			hField.setAccessible(true);
			// 获取 memberValues
			@SuppressWarnings("unchecked")
			Map<String, Object> memberValues = (Map<String, Object>) hField.get(h);
			// 修改 注解中 属性值
			memberValues.put(annotationKey, annotationValue);

			Table table1 = modifyClassz.getAnnotation(Table.class);
			log.info("【Table】注解修改值：" + table1.name());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 修改属性上的注解
	 * 注意，修改完后是全局修改，以后其他实例化的类注解值都已经改变
	 * 
	 * @param modifyClassz 修改的类
	 * @param modifyFieldName Column注解标记的字段名称
	 * @param annotationKey Column注解key名称
	 * @param annotationValue Column注解key对应的Value名称
	 */
	public static <T> void modifyColumnAttributesAnnotation(Class<T> modifyClassz, String modifyFieldName, String annotationKey, String annotationValue) {
		try {
			Field field = modifyClassz.getDeclaredField(modifyFieldName);
			Column column = field.getAnnotation(Column.class);
			log.info("【Column】注解原始值：" + column.name());
			// 获取 该注解 这个代理实例所持有的 InvocationHandler
			InvocationHandler h = Proxy.getInvocationHandler(column);
			// 获取 AnnotationInvocationHandler 的 memberValues 字段
			Field hField = h.getClass().getDeclaredField("memberValues");
			// 因为这个字段是 private final 修饰，所以要打开权限
			hField.setAccessible(true);
			// 获取 memberValues
			@SuppressWarnings("unchecked")
			Map<String, Object> memberValues = (Map<String, Object>) hField.get(h);
			// 修改 注解中 属性值
			memberValues.put(annotationKey, annotationValue);

			Field field1 = modifyClassz.getDeclaredField(modifyFieldName);
			Column column1 = field1.getAnnotation(Column.class);
			log.info("【Column】注解修改值：" + column1.name());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	
}
