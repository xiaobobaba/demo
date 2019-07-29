package com.example.demo.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class XiaoboJob implements BaseJob{
	public XiaoboJob() {  
        
    }
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("xiaobo+++++++++++++++++++++++++++++xiaobo"+new Date());
	}  

}
