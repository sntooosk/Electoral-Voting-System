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

import br.com.urnaetec.factory.ModuloConexao;
import lib.utils.Som.Reproduzir.modelSom;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Tela JFrame para Urna Eletronica
 *
 * @author Juliano
 * @version 1.10
 */
public class TelaUrna extends javax.swing.JFrame {

    Connection conexao;
    PreparedStatement pst;
    ResultSet rs;
    int nro;

    public TelaUrna() {
        initComponents();
        conexao = ModuloConexao.conectar();
        txt1.setText(" ");
        txt2.setText(" ");
        lbl_nome_candidato.setText("NENHUM");
        lbl_chapa.setText("NENHUM");
        txt1.setEnabled(false);
        txt2.setEnabled(false);
    }

    private void testarNumero() {

        if (" ".equals(txt1.getText())) {
            txt1.setText(String.valueOf(nro));
        } else if (" ".equals(txt2.getText())) {
            txt2.setText(String.valueOf(nro));
        }
        modelSom som = new modelSom();
        som.SomTecla();
        setFields();
    }

// Função para executar o código de busca do usuário
    private void setFields() {
        String Candidato = txt1.getText() + txt2.getText();

        String sql = "SELECT tb02_cod_cad, tb02_nome, tb03_descricao, tb02_foto "
                + "FROM tb02_candidatos "
                + "INNER JOIN tb03_chapa ON tb02_candidatos.tb02_cod_chapa = tb03_chapa.tb03_cod_chapa "
                + "WHERE tb02_cod_cad = ?";

        try {

            pst = conexao.prepareStatement(sql);
            pst.setString(1, Candidato);

            // Executa a consulta
            rs = pst.executeQuery();
            if (rs.next()) {
                lbl_nome_candidato.setText(rs.getString("tb02_nome"));
                lbl_chapa.setText(rs.getString("tb03_descricao"));
                Image img = Toolkit.getDefaultToolkit().createImage(rs.getBytes("tb02_foto"));
                ImageIcon foto = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lbl_foto.getWidth(), lbl_foto.getHeight(), Image.SCALE_DEFAULT));
                lbl_foto.setIcon(foto);
            }
            rs.close();
            pst.close();

        } catch (SQLException e) {

        }
    }

    private void VotoAtualizado() {
        String Candidato = txt1.getText() + txt2.getText();

        String sql = "UPDATE tb02_candidatos SET tb02_votos = tb02_votos + 1 WHERE tb02_cod_cad =?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Candidato);

            int create = pst.executeUpdate();

            if (create > 0) {
                JOptionPane.showMessageDialog(null, "Seu voto foi inserido com sucesso.");
                Fim();
            } else {
                JOptionPane.showMessageDialog(null, "Deseja Votar Nulo");
               VotosNulo();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void VotosNulo() {

  
        String sql = "UPDATE tb04_resultado SET tb04_nulos = tb04_nulos + 1 WHERE tb04_cod_eleicao =1";
        try {
            pst = conexao.prepareStatement(sql);
            
            int create = pst.executeUpdate();
            if (create > 0) {
                JOptionPane.showMessageDialog(null, "Seu voto foi inserido com sucesso.");
                Fim();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    

    private void Fim() {
        // Código para limpar os campos da interface do usuário
        lbl_cargo.setText(" ");
        lbl_nome_candidato.setText(" ");
        lbl_chapa.setText(" ");
        lbl_foto.setIcon(null);
        lbl_num.setText(" ");
        lbl_nome.setText(" ");
        lbl_par.setText(" ");
        lbl_fim.setText("FIM");
        txt1.setVisible(false);
        txt2.setVisible(false);

        modelSom som = new modelSom();

        som.somFim();
    }

    private void limparCampos() {
        // Código para limpar os campos da interface do usuário
        txt1.setText(" ");
        txt2.setText(" ");
        lbl_nome_candidato.setText("NENHUM");
        lbl_chapa.setText("NENHUM");
        lbl_foto.setIcon(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_1 = new javax.swing.JButton();
        btn_2 = new javax.swing.JButton();
        btn_3 = new javax.swing.JButton();
        btn_4 = new javax.swing.JButton();
        btn_5 = new javax.swing.JButton();
        btn_6 = new javax.swing.JButton();
        btn_7 = new javax.swing.JButton();
        btn_8 = new javax.swing.JButton();
        btn_9 = new javax.swing.JButton();
        btn_0 = new javax.swing.JButton();
        btn_branco = new javax.swing.JButton();
        btn_corrige = new javax.swing.JButton();
        btn_confirma = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_nome_partido = new javax.swing.JPanel();
        lbl_num = new javax.swing.JLabel();
        lbl_nome = new javax.swing.JLabel();
        lbl_par = new javax.swing.JLabel();
        lbl_cargo = new javax.swing.JLabel();
        lbl_nome_candidato = new javax.swing.JLabel();
        lbl_chapa = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        lbl_fim = new javax.swing.JLabel();
        lbl_foto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Urna");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        btn_1.setBackground(new java.awt.Color(0, 0, 0));
        btn_1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_1.setForeground(new java.awt.Color(255, 255, 255));
        btn_1.setText("1");
        btn_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_1ActionPerformed(evt);
            }
        });

        btn_2.setBackground(new java.awt.Color(0, 0, 0));
        btn_2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_2.setForeground(new java.awt.Color(255, 255, 255));
        btn_2.setText("2");
        btn_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_2ActionPerformed(evt);
            }
        });

        btn_3.setBackground(new java.awt.Color(0, 0, 0));
        btn_3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_3.setForeground(new java.awt.Color(255, 255, 255));
        btn_3.setText("3");
        btn_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_3ActionPerformed(evt);
            }
        });

        btn_4.setBackground(new java.awt.Color(0, 0, 0));
        btn_4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_4.setForeground(new java.awt.Color(255, 255, 255));
        btn_4.setText("4");
        btn_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_4ActionPerformed(evt);
            }
        });

        btn_5.setBackground(new java.awt.Color(0, 0, 0));
        btn_5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_5.setForeground(new java.awt.Color(255, 255, 255));
        btn_5.setText("5");
        btn_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_5ActionPerformed(evt);
            }
        });

        btn_6.setBackground(new java.awt.Color(0, 0, 0));
        btn_6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_6.setForeground(new java.awt.Color(255, 255, 255));
        btn_6.setText("6");
        btn_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_6ActionPerformed(evt);
            }
        });

        btn_7.setBackground(new java.awt.Color(0, 0, 0));
        btn_7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_7.setForeground(new java.awt.Color(255, 255, 255));
        btn_7.setText("7");
        btn_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_7ActionPerformed(evt);
            }
        });

        btn_8.setBackground(new java.awt.Color(0, 0, 0));
        btn_8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_8.setForeground(new java.awt.Color(255, 255, 255));
        btn_8.setText("8");
        btn_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_8ActionPerformed(evt);
            }
        });

        btn_9.setBackground(new java.awt.Color(0, 0, 0));
        btn_9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_9.setForeground(new java.awt.Color(255, 255, 255));
        btn_9.setText("9");
        btn_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_9ActionPerformed(evt);
            }
        });

        btn_0.setBackground(new java.awt.Color(0, 0, 0));
        btn_0.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btn_0.setForeground(new java.awt.Color(255, 255, 255));
        btn_0.setText("0");
        btn_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_0ActionPerformed(evt);
            }
        });

        btn_branco.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_branco.setText("BRANCO");
        btn_branco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_brancoActionPerformed(evt);
            }
        });

        btn_corrige.setBackground(new java.awt.Color(255, 102, 0));
        btn_corrige.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_corrige.setText("CORRIGE");
        btn_corrige.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_corrigeActionPerformed(evt);
            }
        });

        btn_confirma.setBackground(new java.awt.Color(0, 204, 0));
        btn_confirma.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_confirma.setText("CONFIRMA");
        btn_confirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_confirmaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btn_branco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_corrige, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_confirma, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_0, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_2, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)))
                    .addComponent(btn_1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btn_6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_0, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_branco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_confirma, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(btn_corrige, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(787, 110, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("JUSTIÇA");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ESCOLAR");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lib/utils/img/img.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(787, 0, 334, -1));

        lbl_nome_partido.setBackground(new java.awt.Color(255, 255, 255));

        lbl_num.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbl_num.setText("Número:");

        lbl_nome.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbl_nome.setText("Candidato:");

        lbl_par.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbl_par.setText("Chapa:");

        lbl_cargo.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        lbl_cargo.setText("PRESIDENTE ");
        lbl_cargo.setToolTipText("");

        lbl_nome_candidato.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbl_nome_candidato.setText("Candidato");

        lbl_chapa.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lbl_chapa.setText("Chapa");

        txt1.setEditable(false);
        txt1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txt1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt1FocusLost(evt);
            }
        });

        txt2.setEditable(false);
        txt2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txt2.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                txt2ComponentAdded(evt);
            }
        });
        txt2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt2FocusLost(evt);
            }
        });
        txt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt2ActionPerformed(evt);
            }
        });

        lbl_fim.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        javax.swing.GroupLayout lbl_nome_partidoLayout = new javax.swing.GroupLayout(lbl_nome_partido);
        lbl_nome_partido.setLayout(lbl_nome_partidoLayout);
        lbl_nome_partidoLayout.setHorizontalGroup(
            lbl_nome_partidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_nome_partidoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(lbl_nome_partidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lbl_nome_partidoLayout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(lbl_fim, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lbl_nome_partidoLayout.createSequentialGroup()
                        .addComponent(lbl_num)
                        .addGap(18, 18, 18)
                        .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lbl_nome_partidoLayout.createSequentialGroup()
                        .addComponent(lbl_nome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_nome_candidato))
                    .addGroup(lbl_nome_partidoLayout.createSequentialGroup()
                        .addComponent(lbl_par)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_chapa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(lbl_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(lbl_nome_partidoLayout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(lbl_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lbl_nome_partidoLayout.setVerticalGroup(
            lbl_nome_partidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbl_nome_partidoLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(lbl_cargo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(lbl_nome_partidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_num))
                .addGap(0, 0, 0)
                .addComponent(lbl_fim, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(lbl_nome_partidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nome)
                    .addComponent(lbl_nome_candidato))
                .addGap(58, 58, 58)
                .addGroup(lbl_nome_partidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_par)
                    .addComponent(lbl_chapa))
                .addGap(95, 95, 95))
            .addGroup(lbl_nome_partidoLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(lbl_foto, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 45, Short.MAX_VALUE))
        );

        getContentPane().add(lbl_nome_partido, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, 436));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_1ActionPerformed

        nro = 1;
        testarNumero();

    }//GEN-LAST:event_btn_1ActionPerformed

    private void btn_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_2ActionPerformed

        nro = 2;
        testarNumero();
    }//GEN-LAST:event_btn_2ActionPerformed

    private void btn_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_3ActionPerformed

        nro = 3;
        testarNumero();

    }//GEN-LAST:event_btn_3ActionPerformed

    private void btn_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_4ActionPerformed

        nro = 4;
        testarNumero();

    }//GEN-LAST:event_btn_4ActionPerformed

    private void btn_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_5ActionPerformed

        nro = 5;
        testarNumero();

    }//GEN-LAST:event_btn_5ActionPerformed

    private void btn_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_6ActionPerformed

        nro = 6;
        testarNumero();

    }//GEN-LAST:event_btn_6ActionPerformed

    private void btn_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_7ActionPerformed

        nro = 7;
        testarNumero();

    }//GEN-LAST:event_btn_7ActionPerformed

    private void btn_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_8ActionPerformed

        nro = 8;
        testarNumero();

    }//GEN-LAST:event_btn_8ActionPerformed

    private void btn_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_9ActionPerformed

        nro = 9;
        testarNumero();

    }//GEN-LAST:event_btn_9ActionPerformed

    private void btn_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_0ActionPerformed

        nro = 0;
        testarNumero();

    }//GEN-LAST:event_btn_0ActionPerformed

    private void btn_brancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_brancoActionPerformed

        txt1.setText("0");
        txt2.setText("0");
        lbl_foto.setIcon(null);

    }//GEN-LAST:event_btn_brancoActionPerformed

    private void btn_corrigeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_corrigeActionPerformed

        limparCampos();

    }//GEN-LAST:event_btn_corrigeActionPerformed

    private void btn_confirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_confirmaActionPerformed

        VotoAtualizado();
    }//GEN-LAST:event_btn_confirmaActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void txt1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt1FocusLost

    }//GEN-LAST:event_txt1FocusLost

    private void txt2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt2FocusLost

    }//GEN-LAST:event_txt2FocusLost

    private void txt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt2ActionPerformed

    }//GEN-LAST:event_txt2ActionPerformed

    private void txt2ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_txt2ComponentAdded

    }//GEN-LAST:event_txt2ComponentAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaUrna.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaUrna.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaUrna.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaUrna.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaUrna().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_0;
    private javax.swing.JButton btn_1;
    private javax.swing.JButton btn_2;
    private javax.swing.JButton btn_3;
    private javax.swing.JButton btn_4;
    private javax.swing.JButton btn_5;
    private javax.swing.JButton btn_6;
    private javax.swing.JButton btn_7;
    private javax.swing.JButton btn_8;
    private javax.swing.JButton btn_9;
    private javax.swing.JButton btn_branco;
    private javax.swing.JButton btn_confirma;
    private javax.swing.JButton btn_corrige;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_cargo;
    private javax.swing.JLabel lbl_chapa;
    private javax.swing.JLabel lbl_fim;
    private javax.swing.JLabel lbl_foto;
    private javax.swing.JLabel lbl_nome;
    private javax.swing.JLabel lbl_nome_candidato;
    private javax.swing.JPanel lbl_nome_partido;
    private javax.swing.JLabel lbl_num;
    private javax.swing.JLabel lbl_par;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    // End of variables declaration//GEN-END:variables
}
