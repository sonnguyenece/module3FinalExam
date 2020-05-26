package dao;

import model.Product;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

public interface ProductIDAO {
        public void insertProduct(Product product) throws SQLDataException, SQLException;

        public Product selectProduct(int id);

        public List<Product> selectAllProduct();

        public boolean deleteProduct(int id) throws SQLException;

        public void updateProduct(Product product) throws SQLException;

        // Ex.procedures
        public Product getProductByID(int id) throws SQLException;


//
//        public List<Product> orderByName();
//
//        public List<Product> orderByNameSearch(String key);
//
//        public void addProductTransaction(Product Product, int[] permision);

    }

