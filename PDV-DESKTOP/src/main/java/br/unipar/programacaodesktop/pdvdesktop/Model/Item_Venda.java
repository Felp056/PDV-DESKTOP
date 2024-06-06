/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.programacaodesktop.pdvdesktop.Model;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Item_Venda {
     private Integer id;

    private int quantidade;

    private Double valor_unitario;

    private Double valor_total;

    private Venda venda;

    private Produto produto;
}
