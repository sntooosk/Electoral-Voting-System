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
import java.awt.Image;
import java.io.FileInputStream;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import br.com.urnaetec.factory.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * Tela JFrame para Cadastro Candidatos
 *
 * @author Juliano
 * @version 1.10
 */
public class TelaInternalCandidato extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    // instanciar objeto para fluxo de bytes
    private FileInputStream fis;

    //Variavel global para armazenar o tamanho da imagem
    private int tamanho;

    /**
     * Creates new form TelaUsuario
     */
    public TelaInternalCandidato() {
        initComponents();
        /**
         * Método responsável pela criaçao da Conexao
         *
         *
         */
        conexao = ModuloConexao.conectar(); // Conecta ao banco de dados utilizando a classe ModuloConexao
    }

    /**
     * Método responsável pela criaçao do Candidato
     *
     *
     */
    private void createCandidato() {
        String sql = "insert into tb02_candidatos(tb02_cod_cad,tb02_cod_chapa,tb02_nome,tb02_foto) values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtCadCod.getText()); // Obtém o texto do campo txtUsuId e define como valor para o parâmetro 1 da consulta
            pst.setString(2, cboCadChapa.getSelectedItem().toString());
            pst.setString(3, txtCadNome.getText()); // Obtém o texto do campo txtUsuNome e define como valor para o parâmetro 2 da consulta
            pst.setBlob(4, fis, tamanho);

            if ((txtCadCod.getText().isEmpty()) || (txtCadNome.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "preencha todos os campos"); // Exibe uma mensagem se algum campo estiver vazio
            } else {

                // estrutura de decisao
                int create = pst.executeUpdate();
                if (create > 0) {
                    JOptionPane.showMessageDialog(null, "Candidato Cadastrado"); // Exibe uma mensagem de sucesso ao cadastrar o usuário

                    // Limpa os campos após o cadastro
                    txtCadCod.setText(null);
                    txtCadNome.setText(null);
                    lblFoto.setIcon(null);

                }
            }
        } catch (SQLException e) {
            System.out.println(e); // Exibe uma mensagem de erro caso ocorra uma exceção
        }
    }

    /**
     * Método atualizar Candidato
     *
     *
     */
    private void updateCandidato() {

        String Candidato = txtCadNome.getText();

        String sql = "update tb02_candidatos set tb02_nome=?,tb02_foto=?,tb02_cod_chapa =? where tb02_cod_cad=?";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtCadNome.getText());
            pst.setBlob(2, fis, tamanho);
            pst.setString(3, cboCadChapa.getSelectedItem().toString());
            pst.setString(4, txtCadCod.getText());

            if ((txtCadCod.getText().isEmpty()) || (txtCadNome.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "preencha todos os campos"); // Exibe uma mensagem se algum campo estiver vazio
            } else {
                // estrutura de decisao
                int create = pst.executeUpdate();
                if (create > 0) {
                    JOptionPane.showMessageDialog(null, "Candidato " + Candidato + "Alterado com sucesso"); // Exibe uma mensagem de sucesso ao cadastrar o usuário

                    // Limpa os campos após o cadastro
                    txtCadNome.setText(null);

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
    private void deleteCandidato() {
        String Candidato = txtCadNome.getText();

        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o Candidato" + Candidato);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tb02_candidatos where tb02_cod_cad = ?";
            try {
                pst = conexao.prepareStatement(sql);

                pst.setString(1, txtCadCod.getText());// Obtém o texto do campo txtUsuId e define como valor para o parâmetro 1 da consulta
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Candidato apagado com sucesso");

                    // Limpa os campos após o remover
                    txtCadNome.setText(null);

                }

            } catch (HeadlessException | SQLException e) {
                System.out.println(e); // Exibe uma mensagem de erro caso ocorra uma exceção
            }
        }
    }

    /**
     * Método responsável pra Setar e Subir foto
     *
     *
     */
    private void carregarFoto() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Selecionar Arquivo");
        jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens(*.PNG,*.JPG,*.JPEG)", "png", "jpg", "jpeg"));
        int resultado = jfc.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                fis = new FileInputStream(jfc.getSelectedFile());
                tamanho = (int) jfc.getSelectedFile().length();
                Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
                lblFoto.setIcon(new ImageIcon(foto));
                lblFoto.updateUI();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void setarChapa() {
    String sql = "SELECT * FROM tb03_chapa";

    try {
        pst = conexao.prepareStatement(sql);
        rs = pst.executeQuery();
        
        // Limpa a lista antes de adicionar novos itens
        cboCadChapa.removeAllItems();
        
        // Adiciona um item padrão antes dos itens recuperados do banco de dados
        cboCadChapa.addItem("Selecione a Chapa");
        
        while (rs.next()) {
            // Obtém o código da chapa como uma String e adiciona ao componente de seleção
            String codigoChapa = Integer.toString(rs.getInt("tb03_cod_chapa"));
            cboCadChapa.addItem(codigoChapa);
        }
    } catch (SQLException ex) {
        System.out.println("Erro: " + ex);
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

        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboCadChapa = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnUptade = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblFoto = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCadNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCadCod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnAddFoto = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("X - Cadastro Candidato");
        setToolTipText("");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setText("Foto:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 172, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Chapa");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 235, 67, 24));

        cboCadChapa.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        cboCadChapa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", " " }));
        cboCadChapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCadChapaActionPerformed(evt);
            }
        });
        getContentPane().add(cboCadChapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 234, 140, -1));

        btnAdd.setBackground(new java.awt.Color(242, 242, 242));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/create.png"))); // NOI18N
        btnAdd.setToolTipText("Adicionar");
        btnAdd.setBorder(null);
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdd.setPreferredSize(new java.awt.Dimension(80, 80));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 308, -1, -1));

        btnUptade.setBackground(new java.awt.Color(242, 242, 242));
        btnUptade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/update.png"))); // NOI18N
        btnUptade.setToolTipText("Atualizar");
        btnUptade.setBorder(null);
        btnUptade.setEnabled(false);
        btnUptade.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUptade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUptadeActionPerformed(evt);
            }
        });
        getContentPane().add(btnUptade, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, -1, -1));

        btnDelete.setBackground(new java.awt.Color(242, 242, 242));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/delete.png"))); // NOI18N
        btnDelete.setToolTipText("Excluir");
        btnDelete.setBorder(null);
        btnDelete.setEnabled(false);
        btnDelete.setPreferredSize(new java.awt.Dimension(80, 80));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, -1, -1));

        lblFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/camera.png"))); // NOI18N
        lblFoto.setToolTipText("");
        lblFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(lblFoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 42, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setText("Nome:");

        txtCadNome.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel1.setText("Codigo:");

        txtCadCod.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/pesquisar.png"))); // NOI18N

        btnAddFoto.setBackground(new java.awt.Color(242, 242, 242));
        btnAddFoto.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        btnAddFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/create_1.png"))); // NOI18N
        btnAddFoto.setToolTipText("");
        btnAddFoto.setBorder(null);
        btnAddFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCadCod, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddFoto)
                            .addComponent(txtCadNome, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCadCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCadNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAddFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 280, 260));

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void cboCadChapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCadChapaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCadChapaActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // chamando o metodo create
        createCandidato();

    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUptadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUptadeActionPerformed
        // Chamando metodo update
        updateCandidato();
    }//GEN-LAST:event_btnUptadeActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Chamando o metodo Delete
        deleteCandidato();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFotoActionPerformed
        // TODO add your handling code here:
        carregarFoto();
    }//GEN-LAST:event_btnAddFotoActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        setarChapa();
    }//GEN-LAST:event_formInternalFrameActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddFoto;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUptade;
    private javax.swing.JComboBox<String> cboCadChapa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JTextField txtCadCod;
    private javax.swing.JTextField txtCadNome;
    // End of variables declaration//GEN-END:variables
}
