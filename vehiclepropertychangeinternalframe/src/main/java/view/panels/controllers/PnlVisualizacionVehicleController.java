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
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import models.VehicleTableModel;
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
    private VehicleTableModel tblVehicleTableModel;
    private List<Vehicle> vehicles;
    private String[] HEADERS = new String[]{"StockNumber", "Year", "Make", "Model", "Style",
        "Vin", "Exterior color", "Interior color", "Miles", "Price", "Transmission", "Engine", "Image", "Status", "Id"};
    
    private TableRowSorter<VehicleTableModel> tblRowSorterVehicle;

    public PnlVisualizacionVehicleController(PnlVisualizacionVehicle pnlVisualizacionVehicle)
    {
        this.pnlVisualizacionVehicle = pnlVisualizacionVehicle;
        initComponent();
    }
    
    public VehicleTableModel getTblViewModel() {
        return tblVehicleTableModel;
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
        RowFilter<VehicleTableModel, Object> rf = null;
        rf = RowFilter.regexFilter(pnlVisualizacionVehicle.getTxtFinder().getText(), 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        tblRowSorterVehicle.setRowFilter(rf);
    }
    
    private void loadTable() throws IOException
    {
        vehicles = jvdao.getAll().stream().collect(Collectors.toList());
        tblVehicleTableModel = new VehicleTableModel(vehicles, HEADERS);
        tblRowSorterVehicle = new TableRowSorter<>(tblVehicleTableModel);
        
        pnlVisualizacionVehicle.getTblVisualizacionVehicle().setModel(tblVehicleTableModel);
        pnlVisualizacionVehicle.getTblVisualizacionVehicle().setRowSorter(tblRowSorterVehicle);
    }
}
