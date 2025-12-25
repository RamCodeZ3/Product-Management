package org.product.dao;
import org.product.config.ConnectionDB;
import java.sql.Connection;

public class ProductDao {
    Connection connection = ConnectionDB.connection();
}
