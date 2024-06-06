/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.programacaodesktop.pdvdesktop.APIs;

import br.unipar.programacaodesktop.pdvdesktop.DTO.ClienteDto;
import br.unipar.programacaodesktop.pdvdesktop.Util;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author felip
 */
public class Service {
     private static String UrlWebService = "http://127.0.0.1/Api";
    private static int sucesso = 200;
    
    public static ClienteDto buscaCliente(String path) throws Exception{
        String urlChamada = UrlWebService + path;
        try{
        URL url = new URL(urlChamada);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        if(conexao.getResponseCode() != sucesso){
             throw new RuntimeException("Erro ao conectar: "+conexao.getResponseCode()+" "+conexao.getResponseMessage());
        }
            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = Util.converteJsontoString(resposta);
            Gson gson = new Gson();
            ClienteDto dto = gson.fromJson(json, ClienteDto.class);
            return dto;
        }
        catch(IOException | RuntimeException ex){
            throw new Exception("Erro ao retornar cliente"+ex.getMessage()+"\n"+ex.getStackTrace());
        }
    }
}
