package io.renren.modules.job.task;

import io.renren.modules.sys.dao.HostIpDao;
import io.renren.modules.sys.entity.HostIpEntity;
import io.renren.modules.sys.service.HostIpService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;


@Component("localHostTask")
public class LocalHostTask {
    @Autowired(required = false)
    private HostIpService hostIpService;

//    @Scheduled(cron = "0 0/1 * * * ?")
    public void localHost() {

        System.out.println("主机名："+ new Date());
        try{
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println("主机名："+localHost.getHostName());
            System.out.println("本地ip地址："+localHost.getHostAddress());
            HostIpEntity entity = new HostIpEntity();
            entity.setIp(localHost.getHostAddress());
            entity.setCreateDate(new Date());
            hostIpService.save(entity);
        }
        catch(UnknownHostException e)
        {
            e.printStackTrace();
        }
    }
}
