package br.com.urnaetec.model.bean;

/**
 * Classe
 *
 * @author Juliano
 * @version 1.1
 */
public class Partido {

    /**
     * Método responsável pela criaçao do Partido
     *
     *
     */
    private int id_partido;
    private String nome;

    public int getId_partido() {
        return id_partido;
    }

    public void setId_partido(int id_partido) {
        this.id_partido = id_partido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome();
    }

}
