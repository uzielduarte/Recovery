/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import backend.dao.implementation.JsonVehicleDaoImpl;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;
import pojo.Vehicle;

/**
 *
 * @author UZIEL
 */
public class VehicleTableModel extends AbstractTableModel implements PropertyChangeListener        
{

    private List<Vehicle> data;
    private final String COLUMNAMES [];
    private JsonVehicleDaoImpl jvdao;

    public VehicleTableModel(List<Vehicle> data, String[] COLUMNAMES)
    {
        this.data = data;
        this.COLUMNAMES = COLUMNAMES;
    }
    
    public void add(Vehicle v)
    {
        boolean isAdded = false;
        for(Vehicle ve: data)
        {
            if(ve.getStockNumber()== v.getStockNumber())
                isAdded = true;
            break;
        }
        if(!isAdded)
            this.data.add(v);
    }
    
    public void removeRow(int row)
    {
        data.remove(row);
    }

    @Override
    public String getColumnName(int column)
    {
        return COLUMNAMES[column];
    }
    
    @Override
    public int getRowCount()
    {
        return data == null? 0: data.size();
    }

    @Override
    public int getColumnCount()
    {
        return COLUMNAMES == null? 0: COLUMNAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return data == null? null: data.isEmpty()? null: data.get(rowIndex).asArray()[columnIndex];
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        jvdao = new JsonVehicleDaoImpl();
        try
        {
            data = jvdao.getAll().stream().collect(Collectors.toList());
        } catch (IOException ex)
        {
            Logger.getLogger(VehicleTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireTableDataChanged();
    }
    
}
