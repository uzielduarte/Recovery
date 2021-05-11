/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panels.controllers;

import backend.dao.implementation.JsonVehicleDaoImpl;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import pojo.Vehicle;
import view.panels.PnlVisualizacionVehicle;

/**
 *
 * @author UZIEL
 */
public class PnlVisualizacionVehicleController
{
    private PnlVisualizacionVehicle pnlVisualizacionVehicle;
    private JsonVehicleDaoImpl jvdao;
    private DefaultTableModel defaultTableModelVehicle;
    private List<Vehicle> vehicles;
    private String[] HEADERS = new String[]{"StockNumber", "Year", "Make", "Model", "Style",
        "Vin", "Exterior color", "Interior color", "Miles", "Price", "Transmission", "Engine", "Image", "Status"};
    
    private TableRowSorter<DefaultTableModel> tblRowSorterVehicle;

    public PnlVisualizacionVehicleController(PnlVisualizacionVehicle pnlVisualizacionVehicle)
    {
        this.pnlVisualizacionVehicle = pnlVisualizacionVehicle;
        initComponent();
    }
    
    private void initComponent()
    {
        jvdao = new JsonVehicleDaoImpl();
        try
        {
            loadTable();
            pnlVisualizacionVehicle.getTxtFinder().addKeyListener(new KeyAdapter()
            {
                @Override
                public void keyTyped(KeyEvent e)
                {
                    txtFinderKeyTyped(e);
                }
                
            });
        } catch (IOException ex)
        {
            Logger.getLogger(PnlVisualizacionVehicleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void txtFinderKeyTyped(KeyEvent e)
    {
        RowFilter<DefaultTableModel, Object> rf = null;
        rf = RowFilter.regexFilter(pnlVisualizacionVehicle.getTxtFinder().getText(), 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
        tblRowSorterVehicle.setRowFilter(rf);
    }
    
    private void loadTable() throws IOException
    {
        defaultTableModelVehicle = new DefaultTableModel(getData(), HEADERS);
        tblRowSorterVehicle = new TableRowSorter<>(defaultTableModelVehicle);
        
        pnlVisualizacionVehicle.getTblVisualizacionVehicle().setModel(defaultTableModelVehicle);
        pnlVisualizacionVehicle.getTblVisualizacionVehicle().setRowSorter(tblRowSorterVehicle);
    }
    
    private Object[][] getData() throws IOException
    {
        vehicles = jvdao.getAll().stream().collect(Collectors.toList());
        Object data [][] = new Object[vehicles.size()][vehicles.get(0).asArray().length];
        
        IntStream.range(0, vehicles.size()).forEach(i -> {
            data[i] = vehicles.get(i).asArray();
        });
        
        return data;
    }
}
