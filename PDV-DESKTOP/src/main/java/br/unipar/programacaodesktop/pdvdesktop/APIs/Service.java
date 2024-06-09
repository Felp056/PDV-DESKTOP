/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.programacaodesktop.pdvdesktop.APIs;

import br.unipar.programacaodesktop.pdvdesktop.Model.Cliente;
import br.unipar.programacaodesktop.pdvdesktop.Model.Produto;
import br.unipar.programacaodesktop.pdvdesktop.Model.Venda;
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
     private static String UrlWebService = "http://localhost:8080/Api";
    private static int sucesso = 200;
    
    public static Cliente buscaCliente(Integer id) throws Exception{
        String urlChamada = UrlWebService +  "cliente/getbyid?id="+id;
        try{
        URL url = new URL(urlChamada);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        if(conexao.getResponseCode() != sucesso){
             throw new RuntimeException("Erro ao conectar: "+conexao.getResponseCode()+" "+conexao.getResponseMessage());
        }
            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = Util.converteJsontoString(resposta);
            Gson gson = new Gson();
            Cliente dto = gson.fromJson(json, Cliente.class);
            return dto;
        }
        catch(IOException | RuntimeException ex){
            throw new Exception("Erro ao retornar cliente"+ex.getMessage()+"\n"+ex.getStackTrace());
        }
    }
    public static Produto buscaProduto(Integer id) throws Exception{
        String urlChamada = UrlWebService + "/api/produto/getById?id="+id;
        try{
        URL url = new URL(urlChamada);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        if(conexao.getResponseCode() != sucesso){
             throw new RuntimeException("Erro ao conectar: "+conexao.getResponseCode()+" "+conexao.getResponseMessage());
        }
            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = Util.converteJsontoString(resposta);
            Gson gson = new Gson();
            Produto dto = gson.fromJson(json, Produto.class);
            return dto;
        }
        catch(IOException | RuntimeException ex){
            throw new Exception("Erro ao retornar cliente"+ex.getMessage()+"\n"+ex.getStackTrace());
        }
    }
    public static Venda buscaVendas(Integer id) throws Exception{
        String urlChamada = UrlWebService + "/api/vendas/"+id;
        try{
        URL url = new URL(urlChamada);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        if(conexao.getResponseCode() != sucesso){
             throw new RuntimeException("Erro ao conectar: "+conexao.getResponseCode()+" "+conexao.getResponseMessage());
        }
            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String json = Util.converteJsontoString(resposta);
            Gson gson = new Gson();
            Venda dto = gson.fromJson(json, Venda.class);
            return dto;
        }
        catch(IOException | RuntimeException ex){
            throw new Exception("Erro ao retornar cliente"+ex.getMessage()+"\n"+ex.getStackTrace());
        }
    }
}
