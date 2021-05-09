/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import panels.PnlCalculadora;

/**
 *
 * @author UZIEL
 */
public class PnlCalculadoraController
{
    private PnlCalculadora calculadora;

    public PnlCalculadoraController(PnlCalculadora calculadora)
    {
        this.calculadora = calculadora;
        initComponent();
    }
    
    private void initComponent()
    {
        calculadora.getBtnSumar().addActionListener((e) ->
        {
            btnSumarActionPerformed(e);
        });
        
        calculadora.getBtnLimpiar().addActionListener((e) ->
        {
            btnLimpiarActionPerformed(e);
        });
    }
    
    private void btnLimpiarActionPerformed(ActionEvent e)
    {
        calculadora.getTxtNumero1().setText("");
        calculadora.getTxtNumero2().setText("");
        calculadora.getTxtResuldato().setText("");
    }
    
    private void btnSumarActionPerformed(ActionEvent e)
    {
        double numero1 = Double.parseDouble(calculadora.getTxtNumero1().getText());
        double numero2 = Double.parseDouble(calculadora.getTxtNumero2().getText());
        
        double resultado = sumar(numero1, numero2);
        
        calculadora.getTxtResuldato().setText(resultado + "");
    }
    
    private double sumar(double a, double b)
    {
        return a + b;
    }
}
