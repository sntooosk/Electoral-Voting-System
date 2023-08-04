package br.com.urnaetec.model.bean;

import java.io.InputStream;

/**
 * Classe
 *
 * @author Juliano
 * @version 1.1
 */
public class Candidato {

    /**
     * Método responsável pela criaçao do Candidato
     *
     *
     */
    private String codCad;
    private String codChapa;
    private String nome;
    private InputStream foto;

    public Candidato(String codCad, String codChapa, String nome, InputStream foto) {
        this.codCad = codCad;
        this.codChapa = codChapa;
        this.nome = nome;
        this.foto = foto;
    }

    public Candidato(String codCad, String codChapa, String nome) {
        this.codCad = codCad;
        this.codChapa = codChapa;
        this.nome = nome;
    }

    public String getCodCad() {
        return codCad;
    }

    public void setCodCad(String codCad) {
        this.codCad = codCad;
    }

    public String getCodChapa() {
        return codChapa;
    }

    public void setCodChapa(String codChapa) {
        this.codChapa = codChapa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }
}
