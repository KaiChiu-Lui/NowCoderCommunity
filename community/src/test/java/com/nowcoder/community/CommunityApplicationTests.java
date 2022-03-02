package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class) //对src的主配置类进行相同的配置
public class CommunityApplicationTests implements ApplicationContextAware {

    //通过重写ApplicationContextAware方法获得ApplicationContext.通过主配置类注册的Spring容器
    private ApplicationContext applicationContext;

    @Autowired
    private SimpleDateFormat simpleDateFormat;
    @Autowired
    //@Qualifier(Bean名字) 根据Bean的名字获取
    private AlphaDao alphaDao;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Test
    public void testApplicationContext(){
        System.out.println(applicationContext);
        System.out.println(alphaDao.select());
    }

}
