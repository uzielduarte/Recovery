/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.panels.controllers;

import backend.dao.implementation.JsonVehicleDaoImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pojo.Vehicle;
import pojo.VehicleSubModel;
import view.panels.PnlVehicle;

/**
 *
 * @author UZIEL
 */
public class PnlVehicleController
{
    private PnlVehicle pnlVehicle;
    private JsonVehicleDaoImpl jvdao;
    private List<VehicleSubModel> vSubModels;
    private Gson gson;
    private DefaultComboBoxModel cmbModelMake;
    private DefaultComboBoxModel cmbModelModel;
    private DefaultComboBoxModel cmbModelEColor;
    private DefaultComboBoxModel cmbModelIColor;
    private DefaultComboBoxModel cmbModelStatus;
    private final String STATUS [] = new String[]{"Active","Mantainance","Not available"};
    private JFileChooser fileChooser;
    
    private PropertyChangeSupport propertySupport;
    private Vehicle vehicle;
    
    private boolean isNew = true;
    private boolean isUpdate = false;
    private int vehicleIdToEdit;
    
    public void setVehicleIdToEdit(int vehicleIdToEdit)
    {
        this.vehicleIdToEdit = vehicleIdToEdit;
    }

    public void setIsNew(boolean isNew)
    {
        this.isNew = isNew;
        isUpdate = false;
    }

    public void setIsUpdate(boolean isUpdate)
    {
        this.isUpdate = isUpdate;
        isNew = false;
    }

    public PnlVehicleController(PnlVehicle pnlVehicle)
    {
        this.pnlVehicle = pnlVehicle;
        initComponent();
    }
    
    public void addPropertySupport(PropertyChangeListener pcl){
        this.addPropertyChangeListener(pcl);
    }
    public void addPropertyChangeListener(PropertyChangeListener pcl)
    {
        propertySupport.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl)
    {
        propertySupport.removePropertyChangeListener(pcl);
    }
    
    private void initComponent()
    {
        jvdao = new JsonVehicleDaoImpl();
        gson = new Gson();
        propertySupport = new PropertyChangeSupport(this);
        
        JsonReader jreader = new JsonReader(new BufferedReader(
        new InputStreamReader(getClass().getResourceAsStream("/jsons/vehicleData.json"))));
        
        Type listType = new TypeToken<ArrayList<VehicleSubModel>>(){}.getType();
        
        vSubModels = gson.fromJson(jreader, listType);
        
        List<String> makes = vSubModels.stream().map(t -> t.getMake()).collect(Collectors.toList());
        List<String> models = vSubModels.stream().map(t -> t.getModel()).collect(Collectors.toList());
        List<String> colors = vSubModels.stream().map(t -> t.getColor()).collect(Collectors.toList());
        
        cmbModelMake = new DefaultComboBoxModel(makes.toArray());
        cmbModelModel = new DefaultComboBoxModel(models.toArray());
        cmbModelIColor = new DefaultComboBoxModel(colors.toArray());
        cmbModelEColor = new DefaultComboBoxModel(colors.toArray());
        cmbModelStatus = new DefaultComboBoxModel(STATUS);
        
        pnlVehicle.getCmbMake().setModel(cmbModelMake);
        pnlVehicle.getCmbModel().setModel(cmbModelModel);
        pnlVehicle.getCmbEColor().setModel(cmbModelEColor);
        pnlVehicle.getCmbIColor().setModel(cmbModelIColor);
        pnlVehicle.getCmbStatus().setModel(cmbModelStatus);
        
        pnlVehicle.getBtnGuardar().addActionListener((e) ->
        {
            btnGuardarActionPerformed(e);
        });
        
        pnlVehicle.getBtnBuscar().addActionListener((e) ->
        {
           btnBuscarActionPerformed(e); 
        });
    }
    
    private void btnGuardarActionPerformed(ActionEvent e)
    {
        int stock, year;
        String make,model, style, vin, eColor, iColor, miles, engine, image, status;
        float price;
        Vehicle.Transmission transmission;
        
        stock = Integer.parseInt(pnlVehicle.getTxtStockNumber().getText());
        year = Integer.parseInt(pnlVehicle.getSpnYear().getModel().getValue().toString());
        make = pnlVehicle.getCmbMake().getSelectedItem().toString();
        model = pnlVehicle.getCmbModel().getSelectedItem().toString();
        style = pnlVehicle.getTxtStyle().getText();
        vin = pnlVehicle.getTxtVIN().getText();
        eColor = pnlVehicle.getCmbEColor().getSelectedItem().toString();
        iColor = pnlVehicle.getCmbIColor().getSelectedItem().toString();
        miles = pnlVehicle.getSpnMiles().getModel().getValue().toString();
        engine = pnlVehicle.getTxtEngine().getText();
        image = pnlVehicle.getTxtImage().getText();
        status = pnlVehicle.getCmbStatus().getSelectedItem().toString();
        price = Float.parseFloat(pnlVehicle.getSpnPrice().getModel().getValue().toString());
        transmission = pnlVehicle.getRbtnAut().isSelected() ? 
                Vehicle.Transmission.AUTOMATIC : Vehicle.Transmission.MANUAL;
        
        Vehicle v = new Vehicle(stock, year, make, model, style, vin, 
                eColor, iColor, miles, price, transmission, engine, image, status);
        
        try
        {
            vehicleValidation(v);
            
            if(isNew)
            {   
                jvdao.create(v);
                propertySupport.firePropertyChange("vehicle", vehicle, v);
                
                JOptionPane.showMessageDialog(null, "Vehicle saved sucessfully.",
                "Saved message",JOptionPane.INFORMATION_MESSAGE);
                
            }
            
            if(isUpdate)
            {
                v.setId(vehicleIdToEdit);
                jvdao.update(v);
                propertySupport.firePropertyChange("vehicle", vehicle, v);
             
            JOptionPane.showMessageDialog(null, "Vehicle updated sucessfully.",
                    "Updating message",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), 
                    "Error Message", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(PnlVehicleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void btnBuscarActionPerformed(ActionEvent e)
    {
        fileChooser = new JFileChooser();
        
        int option = fileChooser.showOpenDialog(null);
        
        if(option == JFileChooser.CANCEL_OPTION)
            return;
        
        File imageFile = fileChooser.getSelectedFile();
        pnlVehicle.getTxtImage().setText(imageFile.getPath());
    }
    private void vehicleValidation(Vehicle v) throws Exception
    {
        if(v.getStockNumber() <=0){
            throw new Exception("StockNumber can not be less or equal to zero.");
        }
        
        if( v.getVin().isEmpty() || v.getVin().isBlank()){
            throw new Exception("Vin number can not be empty or blank.");
        }
        
        if(v.getEngine().isBlank() || v.getEngine().isEmpty()){
            throw new Exception("Engine can not be empty or blank.");
        }
    }
}
