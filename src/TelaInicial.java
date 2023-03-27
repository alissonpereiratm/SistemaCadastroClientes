
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import Repository.ClienteRepository;
import model.Cliente;

public class TelaInicial extends JFrame implements ActionListener {

    int editar;

    TelaPesquisa telaPesquisa = new TelaPesquisa();
    JFrame frame = new JFrame("Cadastro de Cliente");
    JPanel painel = new JPanel();
    JLabel lTitulo = new JLabel();

    JTextArea tCodigo = new JTextArea();
    JLabel lNome = new JLabel();
    JTextArea tNome = new JTextArea();
    JLabel lEstado = new JLabel();
    JTextArea tEstado = new JTextArea();
    JLabel lCidade = new JLabel();
    JTextArea tCidade = new JTextArea();
    JLabel lBairro = new JLabel();
    JTextArea tBairro = new JTextArea();
    JLabel lLogradouro = new JLabel();
    JTextArea tLogradouro = new JTextArea();
    JLabel lComplemento = new JLabel();
    JTextArea tComplemento = new JTextArea();
    JButton bSalvar = new JButton();
    JButton bNovo = new JButton();
    JButton bApagar = new JButton();
    JButton bConsultar = new JButton();
    JButton bEditar = new JButton();
    Cliente cliente = new Cliente();
    ClienteRepository repository = new ClienteRepository();

    JTable tabela = new JTable();
    DefaultTableModel model = new DefaultTableModel();

    public void telaCadastro() {
        frame.setSize(700, 600);
        painel.setSize(700, 400);
        painel.setLayout(null);

        lTitulo.setText("Cadastro de Cliente");
        lTitulo.setBounds(300, 30, 250, 20);

        lNome.setText("Nome:");
        lNome.setBounds(10, 100, 50, 20);
        tNome.setBounds(100, 100, 550, 20);

        lEstado.setText("Estado:");
        lEstado.setBounds(10, 130, 50, 20);
        tEstado.setBounds(100, 130, 400, 20);

        lCidade.setText("Cidade:");
        lCidade.setBounds(10, 160, 50, 20);
        tCidade.setBounds(100, 160, 550, 20);

        lBairro.setText("Bairro:");
        lBairro.setBounds(10, 190, 50, 20);
        tBairro.setBounds(100, 190, 550, 20);

        lLogradouro.setText("Logradouro:");
        lLogradouro.setBounds(10, 220, 80, 20);
        tLogradouro.setBounds(100, 220, 550, 20);

        lComplemento.setText("Complemento:");
        lComplemento.setBounds(10, 250, 90, 20);
        tComplemento.setBounds(100, 250, 550, 20);

        bConsultar.setText("Consultar");
        bConsultar.setBounds(550, 130, 100, 21);
        bConsultar.addActionListener(new consultaAction());

        bNovo.setText("Novo");
        bNovo.setBounds(190, 300, 80, 30);

        bSalvar.setText("Salvar");
        bSalvar.setBounds(290, 300, 80, 30);
        bSalvar.addActionListener(this);

        bEditar.setText("Editar");
        bEditar.setBounds(490, 300, 80, 30);
        bEditar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.getSelectedRow();

                try {
                    cliente = repository.buscaID((int) tabela.getValueAt(linhaSelecionada, 0));
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }
                new consultaAction().actionPerformed(e);
                tBairro.setText(cliente.getBairro());
                tCidade.setText(cliente.getCidade());
                tCodigo.setText(Integer.toString(cliente.getCodigo()));
                tComplemento.setText(cliente.getComplemento());
                tEstado.setText(cliente.getEstado());
                tLogradouro.setText(cliente.getLogradouro());
                tNome.setText(cliente.getNome());
                editar = 1;
            }

        });

        bApagar.setText("Apagar");
        bApagar.setBounds(390, 300, 80, 30);
        // bApagar.addActionListener(new apagaAction());
        bApagar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tabela.getSelectedRow();
                repository.apagar2((int) tabela.getValueAt(linhaSelecionada, 0));
                new consultaAction().actionPerformed(e);
            }

        });

        frame.setVisible(true);
        frame.add(painel);
        painel.add(lTitulo);

        tabela.setBounds(10, 350, 660, 200);

        painel.add(tCodigo);
        painel.add(lNome);
        painel.add(tNome);
        painel.add(lEstado);
        painel.add(tEstado);
        painel.add(lCidade);
        painel.add(tCidade);
        painel.add(lBairro);
        painel.add(tBairro);
        painel.add(lLogradouro);
        painel.add(tLogradouro);
        painel.add(lComplemento);
        painel.add(tComplemento);
        painel.add(bNovo);
        painel.add(bSalvar);
        painel.add(bApagar);
        painel.add(bConsultar);
        painel.add(bEditar);
        painel.add(tabela);

        new consultaAction().actionPerformed(null);
    }

    public class consultaAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            while (tabela.getModel().getRowCount() > 0) {
                ((DefaultTableModel) tabela.getModel()).setRowCount(0);
                ((DefaultTableModel) tabela.getModel()).setColumnCount(0);
            }

            ArrayList<Cliente> clientes = new ArrayList<>();
            try {
                clientes = repository.consulta();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
            model.addColumn("Código");
            model.addColumn("Nome");
            model.addColumn("Estado");
            model.addColumn("Cidade");
            model.addColumn("Bairro");
            model.addColumn("Logradouro");
            model.addColumn("Complemento");
            model.addRow(new Object[] { "Código", "Nome", "Estado", "Cidade", "Bairro", "Logradouro", "Complemento" });

            for (Cliente cliente : clientes) {
                model.addRow(new Object[] { cliente.getCodigo(), cliente.getNome(), cliente.getEstado(),
                        cliente.getCidade(), cliente.getBairro(), cliente.getLogradouro(), cliente.getComplemento() });
            }
            tabela.setModel(model);

        }

    }

    public class apagaAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            frame.dispose();
            telaPesquisa.telaPesquisa();

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("NUMERO EDITAR" + editar);
        if (editar == 1) {
            cliente.setNome(tNome.getText());
            cliente.setEstado(tEstado.getText());
            cliente.setCidade(tCidade.getText());
            cliente.setBairro(tBairro.getText());
            cliente.setLogradouro(tLogradouro.getText());
            cliente.setComplemento(tComplemento.getText());
            repository.editar(cliente);
            tBairro.setText(null);
            tCidade.setText(null);
            tCodigo.setText(null);
            tComplemento.setText(null);
            tEstado.setText(null);
            tLogradouro.setText(null);
            tNome.setText(null);
            editar = 0;
            new consultaAction().actionPerformed(e);
        } else {
            cliente.setNome(tNome.getText());
            cliente.setEstado(tEstado.getText());
            cliente.setCidade(tCidade.getText());
            cliente.setBairro(tBairro.getText());
            cliente.setLogradouro(tLogradouro.getText());
            cliente.setComplemento(tComplemento.getText());

            repository.inserir(cliente);
            try {
                repository.consulta();
            } catch (SQLException e1) {
                System.out.println("NAO FOI POSSIVEL CONSULTAR NO BD");
                e1.printStackTrace();
            }
            tBairro.setText(null);
            tCidade.setText(null);
            tCodigo.setText(null);
            tComplemento.setText(null);
            tEstado.setText(null);
            tLogradouro.setText(null);
            tNome.setText(null);
            new consultaAction().actionPerformed(e);

        }
    }
}
