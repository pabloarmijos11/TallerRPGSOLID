package Modelo.Personajes;
import Modelo.Problema1_Personaje;

public class Problema1_Guerrero extends Problema1_Personaje {

    private int fuerzaCuerpoACuerpo;

    public Problema1_Guerrero(String nombre, int vida, int nivel, int energiaMaxima, int fuerzaCuerpoACuerpo) {
        super(nombre, vida, nivel, energiaMaxima);
        this.fuerzaCuerpoACuerpo = fuerzaCuerpoACuerpo;
    }

    @Override
    public int getAtaque() {
        return (fuerzaCuerpoACuerpo + (nivel * 5)) + obtenerDañoArma() + obtenerBonoDaño();
    }

    @Override
    public String atacar(Problema1_Personaje objetivo) {
        objetivo.defender(this.getAtaque());
        return String.format("%s realiza un ataque normal con su espada a %s\n", 
                         nombre, objetivo.getNombre());
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Guerrero] Fuerza: %d", 
                                            fuerzaCuerpoACuerpo);
    }
}