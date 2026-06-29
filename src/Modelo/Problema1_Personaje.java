package Modelo;

import Modelo.Estados.Problema1_IEstadoAlterado;
import Modelo.Habilidades.Problema1_IHabilidadEspecial;
import Modelo.Objetos.Problema1_Inventario;
import Modelo.Problema1_Objeto;
import java.util.ArrayList;

public abstract class Problema1_Personaje {

    protected String nombre, reporteEstados;
    protected int vida;
    protected int nivel;
    protected int energia;
    protected int energiaMaxima;
    protected int cooldownEspecial;
    protected boolean puedeAtacar;
    protected Problema1_Inventario inventario;
    protected ArrayList<Problema1_IEstadoAlterado> estados;

    public Problema1_Personaje(String nombre, int vida, int nivel, int energiaMaxima) {
        this.nombre = nombre;
        this.reporteEstados = "";
        this.vida = vida;
        this.nivel = nivel;
        this.energiaMaxima = energiaMaxima;
        this.energia = energiaMaxima;
        this.cooldownEspecial = 0;
        this.inventario = new Problema1_Inventario();
        this.estados = new ArrayList<>();
    }

    public String agregarEstado(Problema1_IEstadoAlterado nuevoEstado) {
        estados.add(nuevoEstado);
        return String.format("¡%s ahora tiene el estado %s por %d turno/s!\n", 
                         nombre, nuevoEstado.getNombre(), nuevoEstado.getTurnosRestantes());
    }
    
    public boolean estaVivo(){
        return vida > 0;
    }
    
    public boolean actualizarEstado() {
        if(!estaVivo()) return false;
        
        this.puedeAtacar = false;
        this.reporteEstados = "";
        
        ArrayList<Problema1_IEstadoAlterado> estadosVigentes = new ArrayList<>();
        for (int i = 0; i < estados.size(); i++) {
            Problema1_IEstadoAlterado est = estados.get(i);
            est.aplicarEfecto(this);
            est.reducirTurnos();
            if(est.getTurnosRestantes() > 0) estadosVigentes.add(est);
        }
        estados = estadosVigentes;
        return !puedeAtacar;
    }
    
    public String obtenerReporteEstados(){
        return reporteEstados;
    }

    public void recibirDañoEstado(int daño) {
        modificarVidaDirecta(-daño);
    }

    public void marcarComoCongelado() {
        this.puedeAtacar = true;
    }

    public void registrarReporteEstado(String linea) {
        this.reporteEstados += linea;
    }
    
    public void modificarVidaDirecta(int cantidad){
        this.vida = Math.max(0, this.vida + cantidad);
    }
    
    public int obtenerBonoDaño() {
        int bono = 0;
        for (int i = 0; i < estados.size(); i++) 
            bono += estados.get(i).getBonoDaño();
        return bono;
    }

    public void agregarAlInventario(Problema1_Objeto obj) {
        inventario.agregarObjeto(obj);
    }
    
    public String equipar(Problema1_Objeto obj) {
        return inventario.equipar(nombre, obj);
    }
    
    public int obtenerDañoArma(){
        return inventario.obtenerValorArma();
    }

    public int defender(int daño) {
        int dañoFinal = Math.max(0, daño - inventario.obtenerValorArmadura());
        modificarVidaDirecta(-dañoFinal);
        return dañoFinal;
    }

    public void pasarTurno() {
        if (this.cooldownEspecial > 0) this.cooldownEspecial--;
    }

    public String usarHabilidadEspecial(Problema1_Personaje objetivo, Problema1_IHabilidadEspecial habilidad) {
        if (this.cooldownEspecial > 0) {
            return String.format("%s no puede usar su habilidad especial todavía. (Enfriamiento: %d turnos)\n", 
                             nombre, cooldownEspecial);
        }

        if (this.energia < habilidad.getCostoEnergia()) {
            return String.format("¡%s no tiene suficiente energia! Requiere %d pero tiene %d\n", 
                             nombre, habilidad.getCostoEnergia(), energia);
        }

        this.energia -= habilidad.getCostoEnergia();
        this.cooldownEspecial = habilidad.getCooldown();
        return habilidad.ejecutarHabilidad(this, objetivo);
    }
    
    public abstract int getAtaque();

    public abstract String atacar(Problema1_Personaje objetivo);

    public String getNombre() {
        return nombre;
    }

    public int getEnergia() {
        return energia;
    }

    public int getCooldownEspecial() {
        return cooldownEspecial;
    }

    public int getVida() {
        return vida;
    }

    public int getNivel() {
        return nivel;
    }

    public void subirNivel() {
        this.nivel++;
        this.vida += 20;
    }

    @Override
    public String toString() {
        return String.format("Personaje: %s | Nivel: %d | Vida: %d | Energía: %d/%d", 
                         nombre, nivel, vida, energia, energiaMaxima);
    }
}