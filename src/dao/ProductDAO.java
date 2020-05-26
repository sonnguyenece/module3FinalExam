package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements ProductIDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo";
    private String jdbcProductname = "root";
    private String jdbcPassword = "123456789";

    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products.products";

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO `products`.`products` (`Name`, `Price`, `Quantity`, `Color`, `Category`)" +
            "VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_PRODUCTS_SQL = "DELETE FROM `products`.`products` WHERE (`Id` = ?);";


    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM products.products WHERE Id=?;";

    private static final String UPDATE_PRODUCT_SQL = "UPDATE `products`.`products` SET `Name` = ?, `Price` = ?," +
            " `Quantity` = ?, `Color` = ?, `Category` = ? WHERE (`Id` = ?);";



//    private static final String ORDER_BY_NAME = "";
//    private static final String ORDER_BY_NAME_SEARCH = "";
//    private static final String SEARCH_PRODUCT = "";



    public ProductDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcProductname, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertProduct(Product product) throws SQLDataException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL);
        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setFloat(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getColor());
        preparedStatement.setString(5, product.getCategory());
        preparedStatement.executeUpdate();
    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Name");
                Float price = rs.getFloat("Price");
                int quantity = rs.getInt("Quantity");
                String color = rs.getString("Color");
                String category = rs.getString("Category");
                product = new Product(id, name, price, quantity,category,color);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                float price = rs.getFloat("Price");
                int quantity = rs.getInt("Quantity");
                String category = rs.getString("Category");
//                String description = rs.getString("Description");
                String color = rs.getString("Color");
                products.add(new Product(id, name, price, quantity,category,color));
            }


        } catch (SQLException e) {
        }
        return products;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);
        statement.setInt(1, id);
        rowDeleted = statement.executeUpdate() > 0;
        return rowDeleted;
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
//        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL);) {
            statement.setString(1, product.getProductName());
            statement.setFloat(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setString(5, product.getCategory());
            statement.setInt(6, product.getProductId());
           statement.executeUpdate();
        }
//        return rowUpdated;
    }

    @Override
    public Product getProductByID(int id) throws SQLException {
        return null;
    }
}
