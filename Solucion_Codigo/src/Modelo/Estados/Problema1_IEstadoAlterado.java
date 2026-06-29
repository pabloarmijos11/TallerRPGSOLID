package Modelo.Estados;
import Modelo.Problema1_Personaje;

public interface Problema1_IEstadoAlterado {
    String getNombre();
    int getTurnosRestantes();
    void reducirTurnos();
    void aplicarEfecto(Problema1_Personaje afectado);
    default int getBonoDaño(){return 0;}
}
