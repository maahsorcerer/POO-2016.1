/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import lib.Helpers;
import model.Estoque;
import model.Produto;
import view.ViewCaixa;

/**
 *
 * @author maico
 */
public class ControllerAdicionarProdutoCaixa implements ActionListener {

    private final JTextField campoCodigo;
    private final JTextField campoQuantidade;
    private final DefaultTableModel produtos;
    private final JTextField campoTotal;
    private final ViewCaixa viewCaixa;

    public ControllerAdicionarProdutoCaixa(
            JTextField campoCodigo,
            JTextField campoQuantidade,
            DefaultTableModel produtos,
            JTextField campoTotal,
            ViewCaixa viewCaixa) {
        this.campoCodigo = campoCodigo;
        this.campoQuantidade = campoQuantidade;
        this.produtos = produtos;
        this.campoTotal = campoTotal;
        this.viewCaixa = viewCaixa;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Produto produto = Estoque.getInstancia().getProdutoByCodigo(campoCodigo.getText());
        if (produto != null) {
            float quantidade = Helpers.toFloat(campoQuantidade.getText());
            float subtotal = quantidade*produto.getValor();
            
            produtos.addRow(
                    new Object[]{
                        produtos.getRowCount()+1,
                        produto.getCodigo(),
                        produto.getNome(),
                        Helpers.toMoney(produto.getValor()),
                        Helpers.toMoney(quantidade),
                        produto.getTipoQuantidade(),
                        Helpers.toMoney(subtotal)
                    });
            
            float total = Helpers.toFloat(campoTotal.getText());
            total += subtotal;
            campoTotal.setText(Helpers.toMoney(total));
        }
        
        viewCaixa.limparInputs();
    }

}