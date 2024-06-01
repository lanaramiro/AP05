package org.example;
import java.sql.SQLException;
import java.util.List;

public class ProdutoService {

    private ControleProduto controleProduto;

    public ProdutoService() throws SQLException {
        controleProduto = new ControleProduto();
    }

    public void addProduto(Produto produto) throws SQLException {
        controleProduto.addProduct(produto);
    }

    public Produto getProduct(int id) throws SQLException {
        return controleProduto.getProduto(id);
    }

    public void updateProduto(Produto produto) throws SQLException {
        controleProduto.updateProduto(produto);
    }

    public void deleteProduto(int id) throws SQLException {
        controleProduto.deleteProduto(id);
    }

    public List<Produto> getAllProdutos() throws SQLException {
        return controleProduto.getAllProdutos();
    }
}
