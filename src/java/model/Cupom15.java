/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author thiagosilva
 */
public class Cupom15  implements Cupom{
    public  int obterDesconto(){
        return 15;
    }
    
    public String obterCupom(){
        return "Cupom15";
    }
}
