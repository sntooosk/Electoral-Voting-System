package br.com.urnaetec.model.bean;

/**
 * Classe
 *
 * @author Juliano
 * @version 1.1
 */
public class Cargo {

    /**
     * Método responsável pela criaçao da Chapa
     *
     *
     */
    private int id_cargo;
    private String nome;

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //@Override
    public String toString() {
        return getNome();
    }

}
