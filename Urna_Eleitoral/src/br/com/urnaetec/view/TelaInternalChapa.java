/*
 * The MIT License
 *
 * Copyright 2023 Juliano cassimiro dos Santos
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.com.urnaetec.view;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import br.com.urnaetec.factory.ModuloConexao;

/**
 * Tela JFrame para Cadastro Chapa
 *
 * @author Juliano
 * @version 1.10
 */
public class TelaInternalChapa extends javax.swing.JInternalFrame {

    /**
     * TelaInternalChapa
     *
     *
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaInternalChapa() {
        initComponents();
        conexao = ModuloConexao.conectar(); // Conecta ao banco de dados utilizando a classe ModuloConexao
        CarregaTabela();
    }

    /**
     * Método responsável por Limpar Campos
     *
     *
     */
    private void limparCampos() {
        txtCodigo.setText(null);
        txtNome.setText(null);
    }

    /**
     * Método responsável por Carregar Tabela
     *
     *
     */
    private void CarregaTabela() {
        String sql = "select * from tb03_chapa";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                DefaultTableModel tab = (DefaultTableModel) tbDados.getModel();
                tab.addRow(new Object[]{rs.getInt("tb03_cod_chapa"), rs.getString("tb03_descricao")});
                tbDados.setModel(tab);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }

    }

    /**
     * Método responsável pela criaçao da Chapa
     *
     *
     */
    private void createChapa() {

        String sql = "insert into tb03_chapa(tb03_cod_chapa,tb03_descricao) values(?,?)";

        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtCodigo.getText()); // Obtém o texto do campo txtUsuId e define como valor para o parâmetro 1 da consulta
            pst.setString(2, txtNome.getText());
            int create = pst.executeUpdate();
            if (create > 0) {
                JOptionPane.showMessageDialog(null, "Chapa Cadastrada"); // Exibe uma mensagem de sucesso ao cadastrar o usuário

                DefaultTableModel tab = (DefaultTableModel) tbDados.getModel();
                tab.addRow(new Object[]{txtCodigo.getText(), txtNome.getText()});
                tbDados.setModel(tab);

            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
        // Limpa os campos após o cadastro
        limparCampos();

    }

    /**
     * Método responsável por deletar Chapa
     *
     *
     */
    private void deleteChapa() {
        String Chapa = txtNome.getText();

        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover a chapa" + Chapa);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tb03_chapa where tb03_cod_chapa =? ";
            try {
                pst = conexao.prepareStatement(sql);

                pst.setString(1, txtCodigo.getText());// Obtém o texto do campo txtUsuId e define como valor para o parâmetro 1 da consulta
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "chapa apagada com sucesso");

                    txtCodigo.setText(null);
                    txtNome.setText(null);

                }

            } catch (HeadlessException | SQLException e) {
                System.out.println(e); // Exibe uma mensagem de erro caso ocorra uma exceção
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbDados = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle(" X - Cadastro Partido");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Chapa"
            }
        ));
        tbDados.setEnabled(false);
        tbDados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDados);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 161, 520, 160));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/pesquisar.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(626, 32, 41, 43));

        btnSalvar.setBackground(new java.awt.Color(242, 242, 242));
        btnSalvar.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/create.png"))); // NOI18N
        btnSalvar.setBorder(null);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 114, 81));

        btnExcluir.setBackground(new java.awt.Color(242, 242, 242));
        btnExcluir.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/delete.png"))); // NOI18N
        btnExcluir.setBorder(null);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 114, 81));

        txtCodigo.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 32, 76, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setText("Código:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 36, 96, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Nome:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 88, 96, -1));

        txtNome.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 80, 226, 29));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/pesquisar.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 32, -1, 30));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 520, 110));

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        createChapa();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        deleteChapa();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void tbDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDadosMouseClicked
        // TODO add your handling code here:
        int linha = tbDados.getSelectedRow();
        txtCodigo.setText(String.valueOf(tbDados.getValueAt(linha, 0)));
        txtNome.setText(String.valueOf(tbDados.getValueAt(linha, 1)));
    }//GEN-LAST:event_tbDadosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbDados;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
