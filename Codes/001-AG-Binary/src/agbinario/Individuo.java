/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agbinario;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando
 */
public class Individuo implements Comparable<Individuo>{
    
    private ArrayList<Integer> cromossomos; //genotipo
    private ArrayList<Double> variaveis; //fenotipo
    private ArrayList<Integer> decod; // binario para inteiro
    Double funcaoObjetivo;
    
    int precisao;
    double min;
    double max;

    int nvar;

    public Individuo(int precisao, double min, double max, int nvar) {
        this.precisao = precisao;
        this.min = min;
        this.max = max;
        this.nvar = nvar;
    }
        
    public int getNvar() {
        return nvar;
    }

    public void setNvar(int nvar) {
        this.nvar = nvar;
    }       
    
    public ArrayList<Integer> getCromossomos() {
        return cromossomos;
    }

    public void setCromossomos(ArrayList<Integer> cromossomos) {
        this.cromossomos = cromossomos;
    }

    public ArrayList<Double> getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(ArrayList<Double> variaveis) {
        this.variaveis = variaveis;
    }

    public ArrayList<Integer> getDecod() {
        return decod;
    }

    public void setDecod(ArrayList<Integer> decod) {
        this.decod = decod;
    }

    public Double getFuncaoObjetivo() {
        return funcaoObjetivo;
    }

    public void setFuncaoObjetivo(Double funcaoObjetivo) {
        this.funcaoObjetivo = funcaoObjetivo;
    }

    public int getPrecisao() {
        return precisao;
    }

    public void setPrecisao(int precisao) {
        this.precisao = precisao;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
    
    public void criar(){
        
        this.cromossomos = new ArrayList<>();
        
        Random rnd = new Random();
        
        for(int i = 0; i < this.getNvar(); i++) {
            for(int j = 0; j < this.getPrecisao(); j++) {
                this.cromossomos.add(rnd.nextInt(2));
            }
        }
        
    }
    
    public void decodificar() {
        
        this.decod = new ArrayList<>();
        this.variaveis = new ArrayList<>();
        
        int valor;
        double real;
        
        for(int i = 0; i < this.getNvar(); i++) {
            valor = 0;
            for(int j = 0; j < this.getPrecisao(); j++) {
                valor += Math.pow(2, this.getPrecisao() - j - 1) 
                        * this.cromossomos.get(i * 
                                this.getPrecisao()+j);                
            }
            
            this.decod.add(valor);
            real = valor;
            real = real * ((this.getMax() - this.getMin()) 
                    / (Math.pow(2, this.getPrecisao()) - 1.0) ) 
                    + this.getMin();
            this.variaveis.add(real);
            
        }
        
    }
    
    // Clone

    @Override
    public String toString() {
        return "Individuo{" + "cromossomos=" + cromossomos + ", variaveis=" + variaveis + ", decod=" + decod + ", funcaoObjetivo=" + funcaoObjetivo + '}';
    }

    @Override
    public int compareTo(Individuo o) {
        return this.getFuncaoObjetivo()
                .compareTo(o.getFuncaoObjetivo());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Individuo individuo = null;
        //super.clone();
        individuo =
                new Individuo(this.getPrecisao(), 
                        this.getMin(),
                        this.getMax(),
                        this.getNvar());
        individuo.setCromossomos(new ArrayList<>(this.getCromossomos()));
        individuo.setVariaveis(this.getVariaveis());
        individuo.setDecod(this.getDecod());
        individuo.setFuncaoObjetivo(this.getFuncaoObjetivo());
        return individuo;        
    }
    
    
    
    
}
