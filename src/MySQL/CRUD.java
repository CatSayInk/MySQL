package MySQL;

import java.sql.*;

public class CRUD {
    public static void main(String[] args) throws SQLException {
        selectAll();
        insert("测试用户", "男", 100);
        delete(20);
        update(8, 99);
    }

    public static void selectAll() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        String sql = "SELECT * FROM test.student";
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.println(result.getInt(1) + "," + result.getString(2) + "," + result.getString(3) + "," + result.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(result, statement, connection);
        }
    }

    public static void insert(String name, String gender, int score) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "INSERT INTO test.student(name, gender, score) VALUES (?,?,?)";
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, gender);
            statement.setInt(3, score);
            if (statement.executeUpdate() == 1) {
                System.out.println("数据插入成功！");
            } else System.out.println("数据插入失败！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(result, statement, connection);
        }
    }

    public static void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "DELETE FROM test.student WHERE id = ?";
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            if (statement.executeUpdate() == 1) {
                System.out.println("数据删除成功！");
            } else System.out.println("数据删除失败！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(result, statement, connection);
        }
    }

    public static void update(int id, int score) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String sql = "UPDATE test.student SET score = ? WHERE id = ?";
        try {
            connection = JDBCUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, score);
            statement.setInt(2, id);
            if (statement.executeUpdate() == 1) {
                System.out.println("数据更新成功！");
            } else System.out.println("数据更新失败！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(result, statement, connection);
        }
    }
}

