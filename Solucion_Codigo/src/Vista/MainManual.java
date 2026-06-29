package Vista;

import Controlador.ControladorRPG;
import Modelo.Personajes.*;
import Modelo.Objetos.Problema1_Arma;
import Modelo.Objetos.Problema1_Armadura;
import Modelo.Estados.Problema1_EstadoCongelado;
import Modelo.Estados.Problema1_EstadoEnvenenado;
import Modelo.Estados.Problema1_EstadoAumentarFuerza;
import Modelo.Habilidades.*;

public class MainManual {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO SIMULACIÓN DE JUEGO RPG ===");
        
        // 1. Creacion e instancion del Controlador
        ControladorRPG controlador = new ControladorRPG();

        // 2. Creación de Personajes
        Problema1_Guerrero guerrero = new Problema1_Guerrero("Aragorn", 120, 1, 30, 15);
        Problema1_Mago mago = new Problema1_Mago("Gandalf", 80, 1, 50, 20);
        Problema1_Arquero arquero = new Problema1_Arquero("Legolas", 95, 1, 40, 18);

        controlador.registrarPersonaje(guerrero);
        controlador.registrarPersonaje(mago);
        controlador.registrarPersonaje(arquero);
        
        Problema1_IHabilidadEspecial golpeCritico = new Problema1_GolpeCritico();
        Problema1_IHabilidadEspecial bolaDeFuego = new Problema1_BolaDeFuego();
        Problema1_IHabilidadEspecial rafagaVeloz = new Problema1_RafagaVeloz();
        
        System.out.println("\n--- ESTADO INICIAL DE LOS PERSONAJES ---");
        System.out.print(controlador.obtenerEstadoGrupo());
        
        // 3. Creación y Equipamiento de Objetos (Sistema de Inventario)
        System.out.println("\n--- FASE DE EQUIPAMIENTO ---");
        Problema1_Arma espadaComun = new Problema1_Arma("Espada de Hierro", 10);
        Problema1_Armadura armaduraPlacas = new Problema1_Armadura("Armadura de Placas", 5);
        Problema1_Arma bastonMagico = new Problema1_Arma("Bastón de Sabio", 12);
        
        guerrero.agregarAlInventario(espadaComun);
        guerrero.agregarAlInventario(armaduraPlacas);
        mago.agregarAlInventario(bastonMagico);
        
        System.out.print(guerrero.equipar(espadaComun));    // Ahora el daño de Aragorn sumará +10
        System.out.print(guerrero.equipar(armaduraPlacas)); // Ahora Aragorn mitigará 5 puntos de cada golpe
        System.out.print(mago.equipar(bastonMagico));   // Ahora el daño de Gandalf sumará +12
        
        // 4. Simulación de Combate por Turnos
        System.out.println("\n----¡EMPIEZA EL COMBATE!️---");

        // --- TURNO 1 ---
        System.out.println("\n[TURNO 1]");
        System.out.print(controlador.procesarAtaqueNormal(guerrero, mago));
        System.out.print(controlador.procesarHabilidadEspecial(mago, guerrero, bolaDeFuego));
        System.out.print(mago.agregarEstado(new Problema1_EstadoCongelado(1)));
        System.out.print(controlador.procesarAtaqueNormal(arquero, mago));

        // --- TURNO 2 ---
        System.out.println("\n[TURNO 2]");
        System.out.print(guerrero.agregarEstado(new Problema1_EstadoAumentarFuerza(2)));
        System.out.print(controlador.procesarAtaqueNormal(guerrero, arquero));
        System.out.print(controlador.procesarAtaqueNormal(mago, guerrero));

        // --- TURNO 3 ---
        System.out.println("\n[TURNO 3]");
        System.out.print(guerrero.agregarEstado(new Problema1_EstadoEnvenenado(2)));
        System.out.print(controlador.procesarAtaqueNormal(guerrero, mago));
        
        // Prueba de Cooldown / Estado de muerte controlado por el cerebro
        System.out.println("\nProbando control de Cooldown y Muerte del Mago");
        System.out.print(controlador.procesarHabilidadEspecial(mago, guerrero, bolaDeFuego));
        
        // 5. Mostrar Estado Final
        System.out.println("\n---FIN DE LA SIMULACION ---");
        System.out.print(controlador.obtenerEstadoGrupo());
    }
}