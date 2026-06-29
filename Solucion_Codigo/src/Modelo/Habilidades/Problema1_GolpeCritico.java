package Modelo.Habilidades;
import Modelo.Problema1_Personaje;

public class Problema1_GolpeCritico implements Problema1_IHabilidadEspecial {

    @Override
    public String ejecutarHabilidad(Problema1_Personaje atacante, Problema1_Personaje objetivo) {
        int dañoEspecial = atacante.getAtaque()* 2;
        objetivo.defender(dañoEspecial);
        return String.format("¡%s usa GOLPE CRÍTICO causando %d de daño a %s!\n", 
                         atacante.getNombre(), dañoEspecial, objetivo.getNombre());
    }

    @Override
    public int getCostoEnergia() {return 15;}

    @Override
    public int getCooldown() {return 2;}

    @Override
    public String getNombreHabilidad() {return "Golpe Critico";}
}