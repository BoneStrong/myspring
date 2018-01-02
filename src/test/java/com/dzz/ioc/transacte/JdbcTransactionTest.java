package com.dzz.ioc.transacte;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

/**
 * @author zoufeng
 * @since 2017/12/29
 */
public class JdbcTransactionTest {

    private Connection connection;

    private Statement statement;

    @Before
    public void getConnection() {
        try {
            //其实是类加载器加载类文件
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://172.24.4.10:3307/usermanage", "root", "root");
            if (!connection.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            }
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void close() {
        try {
            connection.close();
            System.out.println("关闭数据库连接");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleTransaction() {
        try {
            String sql = "select * from student";
            ResultSet rs = statement.executeQuery(sql);
            String name = null;
            int class_id = 0;
            while (rs.next()) {
                name = rs.getString("name");
                class_id = rs.getInt("class_id");
                //输出结果
                System.out.println(name + "\t" + class_id);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("数据库数据成功获取！！");
        }
    }

    /**
     * mysql本身不支持事务,在事务中开启第2个事务，第一个事务会自动提交
     * START TRANSACTION;
     * UPDATE student set class_id=2 where id=2;
     * # 尝试嵌套事务
     * START TRANSACTION;
     * UPDATE student set class_id=5 where id=1;
     * COMMIT;
     */
    @Test
    public void test1() {
        try {
            connection.setAutoCommit(false);
            statement.executeUpdate("UPDATE student SET  class_id=2 where id=1");
            connection.commit();
            System.out.println("执行成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
