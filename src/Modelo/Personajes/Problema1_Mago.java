package Modelo.Personajes;
import Modelo.Problema1_Personaje;

public class Problema1_Mago extends Problema1_Personaje {

    private int poderMagico;

    public Problema1_Mago(String nombre, int vida, int nivel, int energiaMaxima, int poderMagico) {
        super(nombre, vida, nivel, energiaMaxima);
        this.poderMagico = poderMagico;
    }

    @Override
    public int getAtaque() {
        return (poderMagico * nivel) + obtenerDañoArma() + obtenerBonoDaño();
    }

    @Override
    public String atacar(Problema1_Personaje objetivo) {
        objetivo.defender(this.getAtaque());
        return String.format("%s lanza un ataque básico de energía mágica a %s\n", 
                         nombre, objetivo.getNombre());
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Mago] Poder Magico: %d", 
                                            poderMagico);
    }
}