package Modelo.Objetos;
import Modelo.Problema1_Objeto;
import java.util.ArrayList;

public class Problema1_Inventario {
    private ArrayList<Problema1_Objeto> objetos;
    private Problema1_Arma armaEquipada;
    private Problema1_Armadura armaduraEquipada;
    
    public Problema1_Inventario(){
        this.objetos = new ArrayList<>();
    }
    
    public void agregarObjeto(Problema1_Objeto objeto){
        objetos.add(objeto);
    }
    
    public String equipar(String nombrePersonaje, Problema1_Objeto objeto){
        if (objeto instanceof Problema1_Arma) {
            this.armaEquipada = (Problema1_Arma) objeto;
            return String.format("%s se ha equipado el arma: %s\n", 
                             nombrePersonaje, objeto.getNombre());
        } else if (objeto instanceof Problema1_Armadura) {
            this.armaduraEquipada = (Problema1_Armadura) objeto;
            return String.format("%s se ha equipado la armadura: %s\n", 
                             nombrePersonaje, objeto.getNombre());
        }
        return "";
    }
    
    public int obtenerValorArma(){
        return (armaEquipada != null) ? armaEquipada.getValor() : 0;
    }
    
    public int obtenerValorArmadura(){
        return (armaduraEquipada != null) ? armaduraEquipada.getValor() : 0;
    }
}