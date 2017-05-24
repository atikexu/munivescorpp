/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.nasqa.values.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



public class ChoiceBean implements Serializable {
	 	private int value;
	    private String descripcion;

	  

	    /**
	     * @return the descripcion
	     */
	    public String getDescripcion() {
	        return descripcion;
	    }

	    /**
	     * @param descripcion the descripcion to set
	     */
	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }

	    /**
	     * @return the value
	     */
	    public int getValue() {
	        return value;
	    }

	    /**
	     * @param value the value to set
	     */
	    public void setValue(int value) {
	        this.value = value;
	    }
    
}
