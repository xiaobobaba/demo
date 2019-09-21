package com.example.demo.util;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnParam<T> {
	 private Boolean success;    //请求是否成功
	    private List<T> list;       //结果的集合
	    private int total;          //结果的总条数
	    private Object obj;         //其他的对象，可以使用Map<String, Object>的格式添加

}
