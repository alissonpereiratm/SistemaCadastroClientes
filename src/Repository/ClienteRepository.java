package Repository;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;

public class ClienteRepository {

    public void inserir(Cliente cliente) {
        System.out.println(cliente.getNome());
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {
            String sql = "INSERT INTO cliente (id,nome,estado,cidade,bairro,logradouro,complemento)"
                    + "VALUES(NEXTVAL('CLIENTE_SEQ'),?,?,?,?,?,?)";
            PreparedStatement stm;
            stm = conn.prepareStatement(sql);
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getEstado());
            stm.setString(3, cliente.getCidade());
            stm.setString(4, cliente.getBairro());
            stm.setString(5, cliente.getLogradouro());
            stm.setString(6, cliente.getComplemento());
            stm.execute();

        } catch (Exception e) {

        } finally {
            conexao.desconectar(conn);
        }
    }

    public ArrayList<Cliente> consulta() throws SQLException {

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        ArrayList<Cliente> clientes = new ArrayList<>();
        String dadosBD = "Select * From cliente";
        Statement stm = conn.createStatement();
        ResultSet resultado = stm.executeQuery(dadosBD);

        try {

            while (resultado.next()) {

                Cliente cliente = new Cliente();
                cliente.setCodigo(Integer.parseInt(resultado.getString("id")));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEstado(resultado.getString("estado"));
                cliente.setCidade(resultado.getString("cidade"));
                cliente.setBairro(resultado.getString("bairro"));
                cliente.setLogradouro(resultado.getString("logradouro"));
                cliente.setComplemento(resultado.getString("complemento"));

                clientes.add(cliente);
            }

        } catch (Exception e) {
            System.out.println("Não conseguiu consultar a tabela Funcionário");
        } finally {
            conexao.desconectar(conn);
        }

        return clientes;

    }

    public void apagar(String nome) throws SQLException {

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        try {

            String sql = "delete from cliente where nome=?";
            PreparedStatement stm;

            stm = conn.prepareStatement(sql);

            stm.setString(1, nome);

            stm.execute();

        } catch (Exception e) {
            System.out.println("Não conseguiu EXCLUIR o Cliente");
        } finally {
            conexao.desconectar(conn);
        }

    }


    // método para a telaPesquisa(No momento sem utilização)
    public void apagar2(int id) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {
            String sql = "delete from cliente where id=?";

            PreparedStatement stm;
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.execute();

        } catch (Exception e) {
            System.out.println("NAO EXCLUIU");
        } finally {

            conexao.desconectar(conn);
        }
    }

    public void editar(Cliente cliente) {
        System.out.println("NOME   " + cliente.getNome());
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {
            String sql = "update cliente set nome=?,estado=?,cidade=?,bairro=?,logradouro=?,complemento=? WHERE id=?";

            PreparedStatement stm;
            stm = conn.prepareStatement(sql);

            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getEstado());
            stm.setString(3, cliente.getCidade());
            stm.setString(4, cliente.getBairro());
            stm.setString(5, cliente.getLogradouro());
            stm.setString(6, cliente.getComplemento());
            stm.setInt(7, cliente.getCodigo());

            stm.execute();

        } catch (Exception e) {
            System.out.println("NAO EDITOU");
        } finally {

            conexao.desconectar(conn);
        }

    }

    public Cliente buscaID(int id) throws SQLException {
        System.out.println("CHEGOU NA BUSCA");
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        Cliente cliente = new Cliente();
        String dadosBD = "Select * From cliente where id=" + id;
        Statement stm = conn.createStatement();
        ResultSet resultado = stm.executeQuery(dadosBD);

        try {

            while (resultado.next()) {
                cliente.setCodigo(Integer.parseInt(resultado.getString("id")));
                cliente.setNome(resultado.getString("nome"));
                cliente.setEstado(resultado.getString("estado"));
                cliente.setCidade(resultado.getString("cidade"));
                cliente.setBairro(resultado.getString("bairro"));
                cliente.setLogradouro(resultado.getString("logradouro"));
                cliente.setComplemento(resultado.getString("complemento"));

            }

        } catch (Exception e) {
            System.out.println("Não conseguiu consultar a tabela Funcionário");
        } finally {
            conexao.desconectar(conn);
        }

        return cliente;

    }

}
