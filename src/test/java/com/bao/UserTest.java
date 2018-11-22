package com.bao;

import com.bao.mapper.UserMapper2;
import com.bao.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;
import java.util.List;


public class UserTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader readers = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(readers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        List<User> users = sqlSession.selectList("findAllUsers");
//        users.forEach(user -> System.out.println(user.getUserName()));
        UserMapper2 mapper = sqlSession.getMapper(UserMapper2.class);
        List<User> allUsers = mapper.findAllUsers();
        allUsers.forEach(user -> System.out.println(user.getUserName()));
    }

    @Test
    public void test() {
        Hashtable<String,String> table = new Hashtable<>();
        table.put("zhai", "1");
        table.put("zhang", "2");
        table.put("chen", "3");
        System.out.println(table.contains("1"));
    }

}
