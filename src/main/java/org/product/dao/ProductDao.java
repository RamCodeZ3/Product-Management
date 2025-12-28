package org.product.dao;
import org.product.config.ConnectionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.product.models.ProductModel;

public class ProductDao {

    public static List<ProductModel> getProducts(){
        var products = new ArrayList<ProductModel>();
        try(
                Connection connection = ConnectionDB.connection();
                Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT * FROM products");

            while(rs.next()){
                var product = new ProductModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("count"),
                        rs.getString("code"),
                        rs.getString("supplier")
                );
                products.add(product);
            }

        } catch (Exception e){
            throw new RuntimeException("Hubo un error obtiendo los productos.",e);
        }
        return products;
    }


    public static ProductModel getProduct(int id){
        try(
                Connection connection = ConnectionDB.connection();
                Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT * FROM products WHERE id = " + id);

            if (rs.next()){
                return new ProductModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("count"),
                        rs.getString("code"),
                        rs.getString("supplier")
                );
            } else throw new RuntimeException("Producto no encontrado");

        } catch (Exception e){
            throw new RuntimeException("Hubo un error obtiendo el producto.",e);
        }
    }


    public static void insertProduct(ProductModel product){
        try(
                Connection connection = ConnectionDB.connection();
                Statement statement = connection.createStatement()
        ){
            ResultSet rs = statement.executeQuery("INSERT VALUE  ");

        }catch (Exception e){
            throw new RuntimeException("Hubo un error Creando el producto.",e);
        }
    }
}
