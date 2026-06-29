package Modelo.Habilidades;
import Modelo.Problema1_Personaje;

public class Problema1_RafagaVeloz implements Problema1_IHabilidadEspecial {

    @Override
    public String ejecutarHabilidad(Problema1_Personaje atacante, Problema1_Personaje objetivo) {
        int dañoEspecial = atacante.getAtaque();
        objetivo.defender(dañoEspecial);
        return String.format("¡%s dispara una RÁFAGA VELOZ causando %d de daño a %s!\n", 
                         atacante.getNombre(), dañoEspecial, objetivo.getNombre());
    }

    @Override
    public int getCostoEnergia() {return 10;}

    @Override
    public int getCooldown() {return 1;}

    @Override
    public String getNombreHabilidad() {return "Rafaga Veloz";}
}