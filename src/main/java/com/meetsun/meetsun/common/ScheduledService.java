package com.meetsun.meetsun.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meetsun.meetsun.dao.LogInfoDao;
import com.meetsun.meetsun.entity.LogInfo;
import com.meetsun.meetsun.until.Tools;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheduledService {
	
	@Autowired
	private LogInfoDao logInfoDao;
	
	
	@Scheduled(cron = "0 0 0 */1 * ?") //每天凌晨执行
	//@Scheduled(cron = "15 0 0 15 * ? ") //每个月15日凌晨执行
	//@Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
		delLogScheduled();
	}
	public void delLogScheduled() {
		 //系统日志删除定时任务
		log.info("=====>>>>>系统日志删除开始  {}",Tools.get19DateTimes());
		logInfoDao.deleteLogInfoByDay(new LogInfo());
		log.info("=====>>>>>系统日志删除结束  {}",Tools.get19DateTimes());
	}
}
