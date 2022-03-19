package com.nowcoder.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommunityApplication.class)
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testStrings() {
        String redisKey = "test::string";
        redisTemplate.opsForValue().set(redisKey,1);
        redisTemplate.opsForValue().increment(redisKey);
        System.out.println(redisTemplate.opsForValue().get(redisKey));
    }

    @Test
    public void testHashed(){
        String redisKey = "rest:Hashes";
        redisTemplate.opsForHash().put(redisKey,"username","kaichiu");
        System.out.println(redisTemplate.opsForHash().get(redisKey,"username"));
    }

    @Test
    public void testBoundOperations(){
        BoundValueOperations bound = redisTemplate.boundValueOps("test::string");
        bound.decrement();
    }

    //编程式事务
    @Test
    public void testTransaction() {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().set("test::transaction",111);
                return operations.exec();
            }
        });
        System.out.println("redis事务结束,此时可以使用执行redis命令后的结果");
        System.out.println(redisTemplate.opsForValue().get("test::transaction"));
    }
}
