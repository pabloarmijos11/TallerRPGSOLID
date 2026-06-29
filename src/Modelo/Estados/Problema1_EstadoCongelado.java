package Modelo.Estados;

import Modelo.Problema1_Personaje;

public class Problema1_EstadoCongelado implements Problema1_IEstadoAlterado{
    private int turnosRestantes;
    
    public Problema1_EstadoCongelado(int turnos){
        this.turnosRestantes = turnos;
    }

    @Override
    public String getNombre() {
        return "Congelado";
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
        afectado.marcarComoCongelado();
        afectado.registrarReporteEstado(String.format("[Debuff] %s está congelado "
                                                         + "y pierde su turno...\n", 
                                                       afectado.getNombre()));
    }
}