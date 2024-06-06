/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.programacaodesktop.pdvdesktop.DTO;

import br.unipar.programacaodesktop.pdvdesktop.Model.*;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class VendaDto {
      private Integer id;

    private String observacoes;
    private Date data;
    private double total;
    private ClienteDto cliente;
    private List<Item_VendaDto> item_venda;
}
