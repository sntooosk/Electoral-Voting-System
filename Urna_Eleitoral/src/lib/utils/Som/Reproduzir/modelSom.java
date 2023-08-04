package lib.utils.Som.Reproduzir;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Juliano
 * @version 1.10
 */
public class modelSom {

    private static final String Fim = ("E:\\Projetos Completos\\Projeto Urna\\Urna_Eleitoral\\src\\lib\\utils\\Som\\urna.wav");
    private static final String Tecla = ("E:\\Projetos Completos\\Projeto Urna\\Urna_Eleitoral\\src\\lib\\utils\\Som\\Tecla.wav");

    public void somFim() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Fim).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }

    public void SomTecla() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(Tecla).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }

}
