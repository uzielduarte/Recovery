/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import panels.PnlTemperatura;

/**
 *
 * @author UZIEL
 */
public class PnlTemperaturaController
{
    private PnlTemperatura temperatura;
    private final String TEMPERATURAS [] = new String[]{"Celsius", "Fahreneit"};
    
    private DefaultComboBoxModel cmbDe;
    private DefaultComboBoxModel cmbA;

    public PnlTemperaturaController(PnlTemperatura temperatura)
    {
        this.temperatura = temperatura;
        initComponent();
    }
    
    private void initComponent()
    {
        cmbDe = new DefaultComboBoxModel(TEMPERATURAS);
        cmbA = new DefaultComboBoxModel(TEMPERATURAS);
        
        temperatura.getCmbDe().setModel(cmbDe);
        temperatura.getCmbA().setModel(cmbA);
        
        temperatura.getBtnConvertir().addActionListener((e) ->
        {
            btnConvertirActionPerformed(e);
        });
        temperatura.getBtnLimpiar().addActionListener((e) ->
        {
            btnLimpiarActionPerformed(e);
        });
    }
    
    private void btnConvertirActionPerformed(ActionEvent e)
    {
        int cmbDeIndex = temperatura.getCmbDe().getSelectedIndex();
        int cmbAIndex = temperatura.getCmbA().getSelectedIndex();
        double valorTemperatura = Double.parseDouble(temperatura.getTxtTemperatura().getText());
        
        double resultado = convertidorTemperature(valorTemperatura, cmbDeIndex, cmbAIndex);
        
        temperatura.getTxtResultado().setText(resultado + "");
    }
    
    private void btnLimpiarActionPerformed(ActionEvent e)
    {
        temperatura.getCmbDe().setModel(new DefaultComboBoxModel(TEMPERATURAS));
        temperatura.getCmbA().setModel(new DefaultComboBoxModel(TEMPERATURAS));
        temperatura.getTxtTemperatura().setText("");
        temperatura.getTxtResultado().setText("");
    }
    
    private double celciusToFahrenheit(double value)
    {        
        return (((double)9/5*value) + 32);
    }
    
    private double fahrenheitToCelcius(double value)
    {
        return ((double)5/9 *(value - 32));
    }
    
    private double convertidorTemperature(double value, int from, int to)
    {
        switch(from)
        {
            case 0:
                switch(to)
                {
                    case 0:
                        return value;                        
                    case 1:
                        return celciusToFahrenheit(value);
                }
            case 1:
                switch(to)
                {
                    case 0:
                        return fahrenheitToCelcius(value);
                    case 1:
                        return value;
                }
        }
        return value;
    }
}
