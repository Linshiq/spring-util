package com.lsq.demo.mybatisRe.jdbc;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class JdbcTest {

    private static final Logger logger = LogManager.getLogger(JdbcTest.class);

    private Connection connection;

    /*
    连接数据库
     */
    public void connectDatabase() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/lsq?useUnicode=true&characterEncoding=UTF-8";
        String userName = "root";
        String password = "qqqq";
        logger.info("——开始连接数据库——");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, userName, password);
            logger.info("——数据库连接成功——");
        } catch (Exception e) {
            logger.info("——数据库连接出现异常——");
            logger.info(e);
        }
    }

    /*
    JDBC单个查询操作
     */
    public void jdbcTest() throws Exception {
        String sql = "select id,name from student";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println("————————————");
            String id = rs.getString("id");
            System.out.println(id);
            String khzwmc = rs.getString("name");
            System.out.println(khzwmc);
        }
        rs.close();
        pstmt.close();
    }

    /*
    JDBC批量插入or更新or删除操作
     */
    public void jdbcBatchTest() throws Exception {
        String[] names = {"Jack", "Tom", "Rose"};
        // 关闭自动提交
        connection.setAutoCommit(false);
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        for (int i = 0; i < names.length; i++) {
            String sql = "insert into student(id, name) values(" + (i + 1) + ", " + names[i] + ")";
            stmt.addBatch(sql);
        }
        // 批量操作
        stmt.executeBatch();
        // 手动提交
        connection.commit();
        stmt.clearBatch();
        stmt.close();
    }

    /*
    关闭连接
     */
    public void closeConnection() throws SQLException {
        if (null != connection) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        JdbcTest test = new JdbcTest();
        test.connectDatabase();
        test.jdbcBatchTest();
        test.jdbcTest();
        test.closeConnection();
    }
}
