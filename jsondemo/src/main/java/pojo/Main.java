/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UZIEL
 */
public class Main
{
    public static void main(String[] args)
    {
        Jason j = new Jason();
        j.jsonR();
    }
}

class Jason
{

    public Jason()
    {
    }
    
    public void jsonR()
    {
        List<Propietario> propietarios;
        Gson gson = new Gson();
        JsonReader jreader = new JsonReader(new BufferedReader(
        new InputStreamReader(getClass().getResourceAsStream("/jsons/propietario.json"))));
        
        Type listType = new TypeToken<ArrayList<Propietario>>(){}.getType();
        
        propietarios = gson.fromJson(jreader, listType);
        
        int i = 1;
        for(Propietario p: propietarios)
            System.out.println(p.toString() + i++);
    }
}
