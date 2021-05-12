/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author UZIEL
 */
public class Propietario
{
    private String make;
    private String model;
    private String owner;
    private String ein;

    public Propietario()
    {
    }

    public Propietario(String make, String model, String owner, String ein)
    {
        this.make = make;
        this.model = model;
        this.owner = owner;
        this.ein = ein;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getEin()
    {
        return ein;
    }

    public void setEin(String ein)
    {
        this.ein = ein;
    }

    @Override
    public String toString()
    {
        return "Propietario{" + "make=" + make + ", model=" + model + ", owner=" + owner + ", ein=" + ein + '}';
    }
}
