package Modelo.Habilidades;
import Modelo.Problema1_Personaje;

public interface Problema1_IHabilidadEspecial {
    String ejecutarHabilidad(Problema1_Personaje atacante, Problema1_Personaje objetivo);
    int getCostoEnergia();
    int getCooldown();
    String getNombreHabilidad();
}