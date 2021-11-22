package view;

import controller.AlunoController;
import model.Aluno;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoFormList {
    private JPanel panelMain;
    private JButton salvarBtn;
    private JButton cancelarBtn;
    private JTextField nomeTxt;
    private JTextField dt_nascimentoTxt;
    private JTextField matriculaTxt;
    private JButton buscarBtn;
    private JTable alunoTb;
    private JPanel panalTb;
    private JPanel panelBtn;
    private JTextField idTxt;
    private JButton deletarBtn;


    public AlunoFormList() {

        listar();

        salvarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    AlunoController alunoController = new AlunoController();
                    Aluno aluno = new Aluno();

                    aluno.setNome(nomeTxt.getText());
                    aluno.setDt_nascimento(dt_nascimentoTxt.getText());
                    aluno.setMatricula(matriculaTxt.getText());

                    if (idTxt.getText().isEmpty()) {
                        alunoController.salvar(aluno);

                    } else {
                        aluno.setId(Integer.parseInt(idTxt.getText()));
                        alunoController.atualizar(aluno);
                    }

                    System.out.println("Registro inserido com sucesso!");
                    limpar();
                    listar();
                    salvarBtn.setText("Salvar");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if (!nomeTxt.getText().isEmpty()
                            || !matriculaTxt.getText().isEmpty()
                            || !dt_nascimentoTxt.getText().isEmpty()) {

                        AlunoController alunoController = new AlunoController();
                        Aluno aluno = new Aluno();

                        aluno.setNome(nomeTxt.getText());
                        aluno.setDt_nascimento(dt_nascimentoTxt.getText());
                        aluno.setMatricula(matriculaTxt.getText());

                        ResultSet rs = alunoController.buscar(aluno);

                        alunoTb.setModel(DbUtils.resultSetToTableModel(rs));

                        setNomeColuna(new String[]{"ID", "Matricula", "Nome", "Data Nascimento"});
                    } else {
                        JOptionPane.showMessageDialog(null, "Informe algum registro no formulário!");

                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        alunoTb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                idTxt.setText(alunoTb.getValueAt(alunoTb.getSelectedRow(), 0).toString());
                matriculaTxt.setText(alunoTb.getValueAt(alunoTb.getSelectedRow(), 1).toString());
                nomeTxt.setText(alunoTb.getValueAt(alunoTb.getSelectedRow(), 2).toString());
                dt_nascimentoTxt.setText(alunoTb.getValueAt(alunoTb.getSelectedRow(), 3).toString());

                salvarBtn.setText("Editar");
            }
        });
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpar();
                listar();
                salvarBtn.setText("Salvar");
            }
        });
        deletarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Deseja confirmar a remoção do registro?");

                if (i == JOptionPane.YES_OPTION && !idTxt.getText().isEmpty()) {
                    try {

                        AlunoController alunoController = new AlunoController();

                        int id = Integer.parseInt(alunoTb.getValueAt(alunoTb.getSelectedRow(), 0).toString());

                        alunoController.deletar(id);
                        listar();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um registro pra ser deletado!");
                }
            }
        });
    }

    public void limpar() {
        idTxt.setText("");
        nomeTxt.setText("");
        dt_nascimentoTxt.setText("");
        matriculaTxt.setText("");
    }

    public void listar() {
        AlunoController alunoController = new AlunoController();
        ResultSet rs = alunoController.listar();

        alunoTb.setModel(DbUtils.resultSetToTableModel(rs));
        //alunoTb.setTableHeader(null);
        setNomeColuna(new String[]{"ID", "Matricula", "Nome", "Data Nascimento"});
    }

    public void setNomeColuna(String[] colunas) {
        for (int i = 0; i < colunas.length; i++) {
            alunoTb.getColumnModel().getColumn(i).setHeaderValue(colunas[i]);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TelaAlunoFormList");
        frame.setContentPane(new AlunoFormList().panelMain);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
