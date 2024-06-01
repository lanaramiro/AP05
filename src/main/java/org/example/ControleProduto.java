package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControleProduto {

    private Connection connection;

    public ControleProduto() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        createTable();
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS products (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "type VARCHAR(255)," +
                "description TEXT," +
                "weight DOUBLE," +
                "quantity INT," +
                "unit VARCHAR(255))";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
    }

    public void addProduct(Produto produto) throws SQLException {
        String sql = "INSERT INTO products (type, description, weight, quantity, unit) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, produto.getTipo());
        pstmt.setString(2, produto.getDescricao());
        pstmt.setDouble(3, produto.getPeso());
        pstmt.setInt(4, produto.getQuantidade());
        pstmt.setString(5, produto.getUnidade());
        pstmt.executeUpdate();
    }

    public Produto getProduto(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Produto(
                    rs.getInt("id"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getDouble("peso"),
                    rs.getInt("quantidade"),
                    rs.getString("unidade")
            );
        }
        return null;
    }

    public void updateProduto(Produto produto) throws SQLException {
        String sql = "UPDATE products SET type = ?, description = ?, weight = ?, quantity = ?, unit = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, produto.getTipo());
        pstmt.setString(2, produto.getDescricao());
        pstmt.setDouble(3, produto.getPeso());
        pstmt.setInt(4, produto.getQuantidade());
        pstmt.setString(5, produto.getUnidade());
        pstmt.setInt(6, produto.getId());
        pstmt.executeUpdate();
    }

    public void deleteProduto(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    public List<Produto> getAllProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM products";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            produtos.add(new Produto(
                    rs.getInt("id"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getDouble("peso"),
                    rs.getInt("quantidade"),
                    rs.getString("unidade")
            ));
        }
        return produtos;
    }
}