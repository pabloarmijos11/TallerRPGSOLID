package Modelo.Estados;
import Modelo.Problema1_Personaje;

public class Problema1_EstadoAumentarFuerza implements Problema1_IEstadoAlterado{
    private int bonoDaño = 15;
    private int turnosRestantes;
    
    public Problema1_EstadoAumentarFuerza(int turnos){
        this.turnosRestantes = turnos;
    }

    @Override
    public String getNombre() {
        return "Aumentar Fuerza";
    }

    @Override
    public int getTurnosRestantes() {
        return turnosRestantes;
    }

    @Override
    public void reducirTurnos() {
        this.turnosRestantes--;
    }

    @Override
    public void aplicarEfecto(Problema1_Personaje afectado) {
    }
    
    @Override
    public int getBonoDaño() {
        return bonoDaño;
    }
}