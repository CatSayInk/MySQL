package MySQL;

import java.sql.*;

public class JDBCUtils {

    public static final String database = "test";
    public static final String user = "root";
    public static final String password = "abc1235800";
    public static final String url = "jdbc:mysql://localhost:3306/" + database + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. 获取连接 url 用户名 密码
        String DATABASE = "test";
        String USER = "root";
        String PASSWORD = "abc1235800";
        String url = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        Connection con = DriverManager.getConnection(url, USER, PASSWORD);
        System.out.println("连接数据库...");
        System.out.println(con);

        Statement statement = con.createStatement();
        //
        String sqlCreate, sqlUpdate, sqlDelete;
        String sqlRead = "SELECT * FROM test.student";
        ResultSet result = statement.executeQuery(sqlRead);

        read(result);


        // 关闭连接
        result.close();
        statement.close();
        con.close();
    }

    private static void read(ResultSet result) throws SQLException {
        while (result.next()) {
            System.out.println(result.getInt(1) + "," + result.getString(2) + "," + result.getString(3) + "," + result.getInt(4));
        }
    }
}