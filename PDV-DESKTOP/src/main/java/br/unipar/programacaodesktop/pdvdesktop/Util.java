/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.programacaodesktop.pdvdesktop;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author felip
 */
public class Util {
    public static String converteJsontoString(BufferedReader bufferReade) throws IOException{
        String resposta = "";
        String jsonString = "";
        while((resposta = bufferReade.readLine()) != null){
            jsonString += resposta;
        }
        return jsonString;
    }
}
