/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dao.implementation;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author UZIEL
 */
public abstract class RandomTemplate
{
    private File fileHead;
    private File fileData;
    private CustomRandom customRandom;

    public RandomTemplate(File fileHead, File fileData)
    {
        this.fileHead = fileHead;
        this.fileData = fileData;
    }

    protected CustomRandom getCustomRandom() throws IOException
    {
        if(customRandom == null)
            customRandom = new CustomRandom(fileHead, fileData);

        return customRandom;
    }
    
    protected void close() throws IOException
    {
        customRandom.close();
        customRandom = null;
    }
}
