package org.example.dejimanage.tools;

import org.example.dejimanage.config.Constants;
import org.example.dejimanage.service.ProjectOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    private ProjectOrderService projectOrderService;
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    // 每隔一段时间执行一次
    @Scheduled(fixedRate = Constants.PROJECT_TIME)
    public void transferData() {
        projectOrderService.clearProjectCache();
    }
}
