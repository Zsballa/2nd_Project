public class DatabaseConfig {
    public static final String DB_URL =
            "jdbc:mysql://localhost:3306/dealership?createDatabaseIfNotExist=true&serverTimezone=UTC&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "Test123!";

    private DatabaseConfig() {
    }
}
