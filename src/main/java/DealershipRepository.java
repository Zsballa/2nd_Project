import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class DealershipRepository {
    private Connection connection;

    public DealershipRepository() {
        try {
            connection = DriverManager
                    .getConnection(DatabaseConfig.DB_URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void stockList() {
        String sqlQueryNew = "SELECT brand, model,cars.vin,production_year,milage,list_price FROM cars JOIN car_data ON cars.vin = car_data.vin \n" +
                " WHERE cars.new_car = 1 AND cars.car_status = 'Stock'";
        String sqlQueryUsed = "SELECT brand, model,cars.vin,production_year,milage,list_price FROM cars JOIN car_data ON cars.vin = car_data.vin \n" +
                " WHERE cars.new_car = 0 AND cars.car_status = 'Stock'";
        System.out.println("");
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQueryNew);
            System.out.println("Készlet lista - új autók:");
            System.out.println();
            for (int i = 0; i < 2; i++) {
                if (i == 1) {
                    System.out.println();
                    System.out.println("Készlet lista - használt autók:");
                    System.out.println();
                    resultSet = statement.executeQuery(sqlQueryUsed);
                }

                while (resultSet.next()) {
                    System.out.print("brand: " + resultSet.getString("brand") + ", ");
                    System.out.print("model: " + resultSet.getString("model") + ", ");
                    System.out.print("vin: " + resultSet.getString("cars.vin") + ", ");
                    System.out.print("production: " + resultSet.getInt("production_year") + ", ");
                    System.out.print("milage: " + resultSet.getInt("milage") + ", ");
                    System.out.print("price: " + resultSet.getInt("list_price") + ", ");
                    System.out.println();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void orderList() {
        String sqlQuery = "SELECT brand, model,cars.vin,list_price FROM cars JOIN car_data ON cars.vin = car_data.vin \n" +
                " WHERE cars.car_status = 'Ordered'";
        System.out.println();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("Rendelés alatt:");
            System.out.println();
            while (resultSet.next()) {
                System.out.print("brand: " + resultSet.getString("brand") + ", ");
                System.out.print("model: " + resultSet.getString("model") + ", ");
                System.out.print("vin: " + resultSet.getString("cars.vin") + ", ");
                System.out.print("price: " + resultSet.getInt("list_price") + ", ");
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void soldCars() {
        String sqlQuery = "SELECT brand, model,cars.vin,production_year,milage,new_car,sales_price,date_of_sales,name FROM cars " +
                "JOIN car_data ON cars.vin = car_data.vin " +
                "JOIN car_sales ON cars.id = car_sales.car_id " +
                "JOIN sales_person ON car_sales.sales_person_id = sales_person.id;";
        System.out.println();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("Értékesített járművek:");
            System.out.println();
            while (resultSet.next()) {
                System.out.print("brand: " + resultSet.getString("brand") + ", ");
                System.out.print("model: " + resultSet.getString("model") + ", ");
                System.out.print("vin: " + resultSet.getString("cars.vin") + ", ");
                System.out.print("production: " + resultSet.getInt("production_year") + ", ");
                System.out.print("milage: " + resultSet.getInt("milage") + ", ");
                System.out.print("date: " + resultSet.getString("date_of_sales") + ", ");
                System.out.print("price: " + resultSet.getInt("sales_price") + ", ");
                System.out.print("employee: " + resultSet.getString("name") + ", ");
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Salary> salaryCalculator() {
        String sqlQuery = "SELECT base_salary,date,team_comission,nc_sales,SUM(sales_price-list_price)*comission AS monthly_comission, name FROM salary\n" +
                "JOIN sales_person ON salary.sales_person_id = sales_person.id\n" +
                "LEFT JOIN car_sales ON salary.sales_person_id=car_sales.sales_person_id \n" +
                "AND month(salary.date) = month(car_sales.date_of_sales) \n" +
                "AND year(salary.date) = year(car_sales.date_of_sales)\n" +
                "LEFT JOIN cars ON car_sales.car_id = cars.id\n" +
                "GROUP BY date, name";
        System.out.println();
        List<Salary> salaryList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            System.out.println("Fizetések:");
            System.out.println();
            while (resultSet.next()) {
                Salary salary = new Salary();
                salary.setBaseSalary(resultSet.getInt("base_salary"));
                salary.setMonth(resultSet.getString("date"));
                salary.setTeamComission(resultSet.getDouble("team_comission"));
                salary.setComission(resultSet.getDouble("monthly_comission"));
                salary.setSalesPerson(resultSet.getString("name"));
                salary.setNewCar(resultSet.getInt("nc_sales"));
                salaryList.add(salary);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return salaryList;
    }

    public int getTeamComission(boolean newCar, String month) {
        int result = 0;
        try {
            String createCustomer = "SELECT sum(sales_price-list_price) AS team_margin,new_car, date_add(date_of_sales, INTERVAL (1-day(date_of_sales)) DAY) AS sales_month FROM car_sales\n" +
                    "JOIN cars ON car_sales.car_id = cars.id\n" +
                    "WHERE new_car = ? AND date_add(date_of_sales, INTERVAL (1-day(date_of_sales)) DAY) = ? \n" +
                    "GROUP BY new_car, date_add(date_of_sales, INTERVAL (1-day(date_of_sales)) DAY)";
            PreparedStatement preparedStatement = connection.prepareStatement(createCustomer); //Kocsint/Serpa
            // preparedStatement.setString(2, month);
            preparedStatement.setBoolean(1, newCar);
            preparedStatement.setString(2, month);

            ResultSet resultSet = preparedStatement.executeQuery(); //executeQuery
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createNewStock() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Új autó? (i/n)");
        String selectOption = userInput.nextLine();
        if (selectOption.equals("i")) {
            String sqlQueryNew = "SELECT cars.id, brand, model,cars.vin,production_year,milage,list_price FROM cars JOIN car_data ON cars.vin = car_data.vin \n" +
                    " WHERE cars.car_status = 'Ordered'";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sqlQueryNew);
                HashSet<Integer> idList = new HashSet();
                System.out.println("Hanyas számú autó (id) érkezett meg a rendelésből?");
                while (resultSet.next()) {
                    idList.add(resultSet.getInt("cars.id"));
                    NewCar newCar = new NewCar();
                    newCar.setId(resultSet.getInt("cars.id"));
                    newCar.setCarStatus(CarStatus.ORDERED);
                    newCar.setVin(resultSet.getString("cars.vin"));
                    newCar.setMilage(resultSet.getInt("milage"));
                    newCar.setListPrice(resultSet.getInt("list_price"));
                    newCar.setBrand(resultSet.getString("brand"));
                    newCar.setModel(resultSet.getString("model"));
                    newCar.setProductionYear(resultSet.getInt("production_year"));
                }
                System.out.println();
                int selectOption2=-1;
                while (!idList.contains(selectOption2)) {
                    selectOption2= userInput.nextInt();
                }
                int updateId = selectOption2;
                System.out.println("Add meg a km óra állást:");
                selectOption2= userInput.nextInt();
                try {
                    String updateCars = "UPDATE cars SET milage =?, car_status='Stock' WHERE id =?";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateCars);
                    preparedStatement.setInt(1, selectOption2);
                    preparedStatement.setInt(2, updateId);
                    preparedStatement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            UsedCars usedCars = new UsedCars();
            System.out.println(usedCars);
            try {
                String updateCars = "INSERT INTO cars (vin, milage, new_car, list_price, car_status) VALUES (?,?,0,?,'Stock')";
                PreparedStatement preparedStatement = connection.prepareStatement(updateCars);
                preparedStatement.setString(1, usedCars.getVin());
                preparedStatement.setInt(2, usedCars.getMilage());
                preparedStatement.setInt(3,usedCars.getListPrice());
                preparedStatement.executeUpdate();

                updateCars = "INSERT INTO car_data (vin, brand, model, production_year) VALUES (?,?,?,?)";
                preparedStatement = connection.prepareStatement(updateCars);
                preparedStatement.setString(1, usedCars.getVin());
                preparedStatement.setString(2, usedCars.getBrand());
                preparedStatement.setString(3,usedCars.getModel());
                preparedStatement.setInt(4,usedCars.getProductionYear());
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
    public void orderNewCar() {
        NewCar newCar = new NewCar();
        System.out.println(newCar);
    }
    public void updateCarTables(boolean newCar){
        String status = "Ordered";
            Cars carUpdate = new NewCar();

            carUpdate = new UsedCars();
            status = "Stock";


        try {
            String updateCars = "INSERT INTO cars (vin, milage, new_car, list_price, car_status) VALUES (?,?,?,?,'Stock')";
            PreparedStatement preparedStatement = connection.prepareStatement(updateCars);
            preparedStatement.setString(1, carUpdate.getVin());
            preparedStatement.setInt(2,updateCars);
            preparedStatement.setBoolean(3, newCar);
            preparedStatement.setInt(4,usedCars.getListPrice());
            preparedStatement.executeUpdate();

            updateCars = "INSERT INTO car_data (vin, brand, model, production_year) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(updateCars);
            preparedStatement.setString(1, usedCars.getVin());
            preparedStatement.setString(2, usedCars.getBrand());
            preparedStatement.setString(3,usedCars.getModel());
            preparedStatement.setInt(4,usedCars.getProductionYear());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
