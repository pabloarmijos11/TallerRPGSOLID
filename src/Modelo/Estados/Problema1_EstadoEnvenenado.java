package Modelo.Estados;
import Modelo.Problema1_Personaje;

public class Problema1_EstadoEnvenenado implements Problema1_IEstadoAlterado{
    private int dañoVeneno = 10;
    private int turnosRestantes;
    
    public Problema1_EstadoEnvenenado(int turnos){
        this.turnosRestantes = turnos;
    }

    @Override
    public String getNombre() {
        return "Envenenado";
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
        afectado.recibirDañoEstado(dañoVeneno);
        afectado.registrarReporteEstado(String.format("[Debuff] Veneno causa %d de daño a %s - Vida Actual: %d\n",
                                                  dañoVeneno, afectado.getNombre(), afectado.getVida()));
    }
}