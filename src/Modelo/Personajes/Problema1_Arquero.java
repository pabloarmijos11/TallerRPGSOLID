package Modelo.Personajes;
import Modelo.Problema1_Personaje;

public class Problema1_Arquero extends Problema1_Personaje {

    private int precision;

    public Problema1_Arquero(String nombre, int vida, int nivel, int energiaMaxima, int precision) {
        super(nombre, vida, nivel, energiaMaxima);
        this.precision = precision;
    }

    @Override
    public int getAtaque() {
        return (precision + (nivel * 4)) + obtenerDañoArma() + obtenerBonoDaño();
    }

    @Override
    public String atacar(Problema1_Personaje objetivo) {
        objetivo.defender(this.getAtaque());
        return String.format("%s dispara una flecha certera a %s\n", 
                         nombre, objetivo.getNombre());
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Arquero] Precision: %d", 
                                            precision);
    }
}