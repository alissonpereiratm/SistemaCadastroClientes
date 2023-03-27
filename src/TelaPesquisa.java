
//tela adicional, no momento sem utilização



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Repository.ClienteRepository;

public class TelaPesquisa extends JFrame implements ActionListener {

    JFrame framePesquisa = new JFrame("Pesquisa de Cliente");
    JPanel painelPesquisa = new JPanel();
    JLabel lTituloPesquisa = new JLabel();

    JLabel lNomeP = new JLabel();
    JTextArea tNomeP = new JTextArea();
    JButton bApagarP = new JButton();

    public void telaPesquisa() {
        framePesquisa.setSize(400, 200);
        painelPesquisa.setSize(400, 200);
        painelPesquisa.setLayout(null);

        lTituloPesquisa.setText("Apagar Cliente");
        lTituloPesquisa.setBounds(150, 30, 150, 20);

        framePesquisa.setVisible(true);
        framePesquisa.add(painelPesquisa);
        painelPesquisa.add(lTituloPesquisa);

        lNomeP.setText("Nome");
        lNomeP.setBounds(10, 95, 80, 30);
        tNomeP.setBounds(80, 100, 200, 20);

        bApagarP.setText("Apagar");
        bApagarP.setBounds(300, 100, 80, 21);
       bApagarP.addActionListener(this);
       

        painelPesquisa.add(lNomeP);
        painelPesquisa.add(tNomeP);
        painelPesquisa.add(bApagarP);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TelaInicial tela = new TelaInicial();
        ClienteRepository repository = new ClienteRepository();
        try {

            repository.apagar(tNomeP.getText());
            framePesquisa.dispose();
            tela.telaCadastro();

        } catch (SQLException e1) {

            e1.printStackTrace();
        }
    }
}