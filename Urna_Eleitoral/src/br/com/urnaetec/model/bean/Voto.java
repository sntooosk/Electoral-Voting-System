package br.com.urnaetec.model.bean;

/**
 * Classe
 *
 * @author Juliano
 * @version 1.1
 */
public class Voto {

    /**
     * Método responsável pela criaçao do Voto
     *
     *
     */
    private int id_votos;
    private String nome;
    private int Total_votos;
    private int numero;
    private int id_cargo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTotal_votos() {
        return Total_votos;
    }

    public void setTotal_votos(int Total_votos) {
        this.Total_votos = Total_votos;
    }

    public int getId_votos() {
        return id_votos;
    }

    public void setId_votos(int id_votos) {
        this.id_votos = id_votos;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

}
