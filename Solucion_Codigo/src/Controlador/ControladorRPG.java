package Controlador;
import Modelo.Problema1_Personaje;
import Modelo.Habilidades.Problema1_IHabilidadEspecial;
import java.util.ArrayList;

public class ControladorRPG {
    private ArrayList<Problema1_Personaje> personajes;

    public ControladorRPG() {
        this.personajes = new ArrayList<>();
    }
    
    public void registrarPersonaje(Problema1_Personaje personaje){
        personajes.add(personaje);
    }
    
    public String procesarAtaqueNormal(Problema1_Personaje atacante, Problema1_Personaje objetivo){
        if(!atacante.estaVivo())
            return String.format("%s esta muerto y no puede atacar\n", atacante.getNombre());
        
        boolean puedeAtacar = atacante.actualizarEstado();
        String reporteEstados = atacante.obtenerReporteEstados();
        
        if(!atacante.estaVivo())
            return String.format("%s ha sucumbido ante los estados alterados\n", atacante.getNombre());
        
        if(puedeAtacar){
            String resultadoAtaque = atacante.atacar(objetivo);
            atacante.pasarTurno();
            return reporteEstados + resultadoAtaque;
        }else{
            atacante.pasarTurno();
            return String.format("%s %s no pudo hacer nada este turno\n", reporteEstados, atacante.getNombre());
        }
    }
    
    public String procesarHabilidadEspecial(Problema1_Personaje atacante, Problema1_Personaje objetivo, Problema1_IHabilidadEspecial habilidad){
        if(!atacante.estaVivo())
            return String.format("%s esta muerto y no puede usar su habilidad\n", atacante.getNombre());

        boolean puedeAtacar = atacante.actualizarEstado();        
        String reporteEstados = atacante.obtenerReporteEstados();
        
        if(puedeAtacar){
            String resultadoEspecial = atacante.usarHabilidadEspecial(objetivo, habilidad);
            atacante.pasarTurno();
            return reporteEstados + resultadoEspecial;
        }else{
            atacante.pasarTurno();
            return String.format("%s %s no pudo usar su ataque especial %s por restricciones de estado\n", 
                             reporteEstados, atacante.getNombre(), habilidad.getNombreHabilidad());
        }
    }
    
    public String obtenerEstadoGrupo(){
        String estado = "";
        for (int i = 0; i < personajes.size(); i++)
            estado += String.format("%s\n", personajes.get(i).toString());
        return estado;
    }
}