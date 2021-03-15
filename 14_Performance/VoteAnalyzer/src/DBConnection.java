import java.sql.*;

class DBConnection {

    private static Connection connection;
    // размер блока для загрузки в DB, voters
    private static final int batchSize = 40_000;
    private static PreparedStatement preparedStatement = null;

    //private static StringBuilder insertQuery = new StringBuilder();


    public static Connection getConnection() {
        if (connection == null) {
            try {
                String dbName = "learn";
                String dbUser = "root";
                String dbPass = "Test09876543212345";
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass + "&useSSL=false&allowPublicKeyRetrieval=true&useTimezone=true&serverTimezone=GMT&useUnicode=true&characterEncoding=utf8");

                connection.setAutoCommit(false);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "count INT NOT NULL, " +
                        "PRIMARY KEY(id), " +
                        "KEY(name(50)));");
                connection.commit();
                String insertSQL = "INSERT INTO voter_count (name, birthDate,count) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(insertSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void countVoter(String name, String birthDay, int counter) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, birthDay);
        preparedStatement.setInt(3, 1);
        preparedStatement.addBatch();

        if (counter % batchSize == 0) executeBatch();
    }

    public static void executeBatch() throws SQLException {
        preparedStatement.executeBatch();
        connection.commit();
    }

    public static void printVoterCounts() throws SQLException {
        long start = System.currentTimeMillis();
        int counter = 0;
        String sql = "select name,birthDate,vote_num from (select name,count,birthDate," +
                "count(count) as vote_num from voter_count group by name,birthDate order " +
                "by vote_num Desc) as result where vote_num > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        StringBuilder result = new StringBuilder();
        while (rs.next()) {
            result.append("\t")
                    .append((counter++))
                    .append(" - ")
                    .append(rs.getString("name"))
                    .append(" ")
                    .append(rs.getString("birthDate"))
                    .append(" ")
                    .append(rs.getInt("vote_num"))
                    .append("\n");
        }
        System.out.println(result.toString().isBlank() ? "Cannot Find Any Duplicates" : result.toString());
        System.out.printf("%.3f sec.%n", (double) (System.currentTimeMillis() - start) / 1000);
        rs.close();
    }

    public static void customSelect(String name) throws SQLException {
        long start = System.currentTimeMillis();
        String sql = "SELECT name, birthDate FROM voter_count WHERE name ='" + name + "'";

        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        StringBuilder result = new StringBuilder();
        while (rs.next()) {
            result.append("\t")
                    .append(rs.getString("name"))
                    .append(" ")
                    .append(rs.getString("birthDate"))
                    .append("\n");
        }
        System.out.println(result.toString().isBlank() ? "No such element" : result.toString());
        System.out.printf("%.3f sec.%n", (double) (System.currentTimeMillis() - start) / 1000);
        rs.close();

    }

    static void connectionClose() throws SQLException {
        connection.close();
    }
}