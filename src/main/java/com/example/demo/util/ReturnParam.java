package com.example.demo.util;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnParam<T> {
		/**
		  * 是否弹框
		 */
	 	private String isTan; 		
	 	/**
	 	  * 请求是否成功
	 	 */
	 	private Boolean success;    
	 	/**
	 	  * 结果的集合
	 	 */
	    private List<T> list;      
	    /**
	             * 结果的总条数
	     */
	    private int total;          
	    /**
	             * 其他的对象，可以使用Map<String, Object>的格式添加
	     */
	    private Object obj;         

}
