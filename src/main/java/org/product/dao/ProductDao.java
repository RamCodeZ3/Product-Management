package org.product.dao;
import org.product.config.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.product.models.ProductModel;

public class ProductDao {

    public List<ProductModel> getProducts(){
        var products = new ArrayList<ProductModel>();

        try(Connection connection = ConnectionDB.connection();
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


    public ProductModel getProductById(int id){

        try(Connection connection = ConnectionDB.connection();
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


    public void addProduct(String name, int price, int count, String code, String supplier){
        String sql = "INSERT INTO products (name, price, count, code, supplier) VALUES (?, ?, ?, ?, ?)";

        try(Connection connection = ConnectionDB.connection();
            PreparedStatement pstmt = connection.prepareStatement(sql)
        ){
            pstmt.setString(1, name);
            pstmt.setInt(2, price);
            pstmt.setInt(3, count);
            pstmt.setString(4, code);
            pstmt.setString(5, supplier);
            pstmt.executeUpdate();

            System.out.println("Se creo un nuevo producto con exito");

        }catch (Exception e){
            throw new RuntimeException("Hubo un error Creando el producto.",e);
        }
    }

    public void updateProductById(ProductModel product){
        String sql = "UPDATE products SET name = ?, price = ?, count = ?, code = ?, supplier = ? WHERE id = ?";
        try(Connection connection = ConnectionDB.connection();
            PreparedStatement pstmt = connection.prepareStatement(sql)
        ){
            pstmt.setString(1, product.name);
            pstmt.setInt(2, product.price);
            pstmt.setInt(3, product.count);
            pstmt.setString(4, product.code);
            pstmt.setString(5, product.supplier);
            pstmt.setInt(6, product.id);
            pstmt.executeUpdate();

            System.out.println("Se actualizo el producto con exito");

        }catch (Exception e){
            throw new RuntimeException("Hubo un error actualizando el producto.", e);
        }
    }


    public void deleteProductById(int id){
        String sql = "DELETE FROM products WHERE id = ?";
        try(Connection connection = ConnectionDB.connection();
            PreparedStatement pstmt = connection.prepareStatement(sql)
        ){

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Se elimino el producto con exito");

        }catch (Exception e){
            throw new RuntimeException("Hubo un error eliminando el producto.", e);
        }
    }
}
