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
import br.com.urnaetec.factory.ModuloConexao;

/**
 * Tela JFrame para Cadastro de usuarios
 *
 * @author Juliano
 * @version 1.10
 */
public class TelaInternalUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaInternalUsuario() {
        initComponents();
        conexao = ModuloConexao.conectar(); // Conecta ao banco de dados utilizando a classe ModuloConexao
    }

    /**
     * Método responsável pela criaçao do Usuario
     *
     *
     */
    private void createUsuario() {
        String sql = "insert into tb00_usuarios(tb00_iduser,tb00_usuario,tb00_login,tb00_senha,tb00_perfil) values(?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtUsuId.getText()); // Obtém o texto do campo txtUsuId e define como valor para o parâmetro 1 da consulta
            pst.setString(2, txtUsuNome.getText()); // Obtém o texto do campo txtUsuNome e define como valor para o parâmetro 2 da consulta
            pst.setString(3, txtUsuLogin.getText()); // Obtém o texto do campo txtUsuLogin e define como valor para o parâmetro 4 da consulta
            pst.setString(4, txtUsuSenha.getText()); // Obtém o texto do campo txtUsuSenha e define como valor para o parâmetro 5 da consulta
            pst.setString(5, cboUsuPerfil.getSelectedItem().toString()); // Obtém o item selecionado da combobox cboUsuPerfil e define como valor para o parâmetro 6 da consulta

            if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "preencha todos os campos"); // Exibe uma mensagem se algum campo estiver vazio
            } else {

                // estrutura de decisao
                int create = pst.executeUpdate();
                if (create > 0) {
                    JOptionPane.showMessageDialog(null, "usuario Cadastrado"); // Exibe uma mensagem de sucesso ao cadastrar o usuário

                    // Limpa os campos após o cadastro
                    txtUsuNome.setText(null);

                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            }
        } catch (SQLException e) {
            System.out.println(e); // Exibe uma mensagem de erro caso ocorra uma exceção
        }
    }

    /**
     * Método responsável pra alterar Candidatos *
     *
     */
    private void readUsuario() {
        String sql = "select * from tb00_usuarios where tb00_iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText()); // Obtém o texto do campo txtUsuId e define como valor para o parâmetro 1 da consulta

            // Executa a consulta
            rs = pst.executeQuery();

            if (rs.next()) {
                // Preenche os campos com os dados obtidos da consulta
                txtUsuNome.setText(rs.getString(2));
                txtUsuLogin.setText(rs.getString(3));
                txtUsuSenha.setText(rs.getString(4));
                // Define o valor selecionado na combobox cboUsuPerfil
                cboUsuPerfil.setSelectedItem(rs.getString(5));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");

                // Limpa os campos
                txtUsuNome.setText(null);

                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
                cboUsuPerfil.setSelectedItem(null);
            }
        } catch (SQLException e) {
            System.out.println(e);// Exibe uma mensagem de erro caso ocorra uma exceção
        }
    }

    /**
     * Método responsável por Atualiar Candidato
     *
     *
     */
    private void updateUsuario() {

        String sql = "update tb00_usuarios set tb00_usuario =?,tb00_login=?,tb00_senha=?,tb00_perfil=? where tb00_iduser=?";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuLogin.getText());
            pst.setString(3, txtUsuSenha.getText());
            pst.setString(4, cboUsuPerfil.getSelectedItem().toString());

            pst.setString(5, txtUsuId.getText());

            if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "preencha todos os campos"); // Exibe uma mensagem se algum campo estiver vazio
            } else {
                // estrutura de decisao
                int create = pst.executeUpdate();
                if (create > 0) {
                    JOptionPane.showMessageDialog(null, "usuario Alterado com sucesso"); // Exibe uma mensagem de sucesso ao cadastrar o usuário

                    // Limpa os campos após o cadastro
                    txtUsuNome.setText(null);

                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            }
        } catch (SQLException e) {
            System.out.println(e); // Exibe uma mensagem de erro caso ocorra uma exceção
        }

    }

    /**
     * Método responsável por deletar Candidato
     *
     *
     */
    private void deleteUsuario() {

        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuario");
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tb00_usuarios where tb00_iduser = ?";
            try {
                pst = conexao.prepareStatement(sql);

                pst.setString(1, txtUsuId.getText());// Obtém o texto do campo txtUsuId e define como valor para o parâmetro 1 da consulta
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario apagado com sucesso");

                    // Limpa os campos após o remover
                    txtUsuNome.setText(null);

                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUsuSenha = new javax.swing.JTextField();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtUsuLogin = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnRead = new javax.swing.JButton();
        btnUptade = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("X  - Cadastro Usuario");
        setToolTipText("");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Id:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 24, 40, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setText("Nome:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 78, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Senha:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 194, -1, -1));

        txtUsuId.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txtUsuId, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 21, 106, -1));

        txtUsuNome.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txtUsuNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 75, 445, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Perfil:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(298, 148, 45, 27));

        txtUsuSenha.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txtUsuSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 191, 197, -1));

        cboUsuPerfil.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user", " " }));
        cboUsuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsuPerfilActionPerformed(evt);
            }
        });
        getContentPane().add(cboUsuPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(349, 148, 189, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Login:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 137, 41, 20));

        txtUsuLogin.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        getContentPane().add(txtUsuLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 137, 197, -1));

        btnAdd.setBackground(new java.awt.Color(242, 242, 242));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/create.png"))); // NOI18N
        btnAdd.setToolTipText("Adicionar");
        btnAdd.setBorder(null);
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 284, -1, -1));

        btnRead.setBackground(new java.awt.Color(242, 242, 242));
        btnRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/read.png"))); // NOI18N
        btnRead.setToolTipText("Pesquisar");
        btnRead.setBorder(null);
        btnRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRead.setEnabled(false);
        btnRead.setPreferredSize(new java.awt.Dimension(80, 80));
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });
        getContentPane().add(btnRead, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 284, -1, -1));

        btnUptade.setBackground(new java.awt.Color(242, 242, 242));
        btnUptade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/update.png"))); // NOI18N
        btnUptade.setToolTipText("Atualizar");
        btnUptade.setBorder(null);
        btnUptade.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUptade.setEnabled(false);
        btnUptade.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUptade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUptadeActionPerformed(evt);
            }
        });
        getContentPane().add(btnUptade, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 284, -1, -1));

        btnDelete.setBackground(new java.awt.Color(242, 242, 242));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/delete.png"))); // NOI18N
        btnDelete.setToolTipText("Excluir");
        btnDelete.setBorder(null);
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.setEnabled(false);
        btnDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 284, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 530, 240));

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void cboUsuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsuPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUsuPerfilActionPerformed

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        // Chamando o metodo read 
        readUsuario();
    }//GEN-LAST:event_btnReadActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // chamando o metodo create
        createUsuario();

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUptadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUptadeActionPerformed
        // Chamando metodo update
        updateUsuario();
    }//GEN-LAST:event_btnUptadeActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Chamando o metodo Delete
        deleteUsuario();
    }//GEN-LAST:event_btnDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRead;
    private javax.swing.JButton btnUptade;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
