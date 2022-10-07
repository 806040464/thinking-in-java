package com.zoe.study.jvm.classloader;

import java.sql.*;

public class JDBCDriver {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://116.204.70.26:3306/hero_all?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8", "root", "root");
            String sql = "select * from tb_user where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,16);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("id = " + resultSet.getInt("id"));
                System.out.println("real_name = " + resultSet.getString("real_name"));
                System.out.println("gender = " + resultSet.getString("gender"));
                System.out.println("profession = " + resultSet.getString("profession"));
                System.out.println("nick_name = " + resultSet.getString("nick_name"));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != resultSet){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (null != preparedStatement){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
