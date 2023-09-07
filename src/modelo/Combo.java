package modelo;

import java.util.ArrayList;
import java.util.List;

public class Combo implements Producto {
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo;

	
	public Combo(String nombreCombo,double descuento, ArrayList<ProductoMenu> itemsCombos) {
		this.descuento = descuento;
		this.nombreCombo = nombreCombo;
		this.itemsCombo = itemsCombos;
	}

	public double getDescuento() {
		return descuento;
	}

	public String getNombreCombo() {
		return nombreCombo;
	}

	public List<ProductoMenu> getItemsCombo() {
		return itemsCombo;
	}

	@Override
	public int getPrecio() {
		int precioTotal=0;
		for(ProductoMenu producto: itemsCombo) 
			
		{	 
			precioTotal+= producto.getPrecio();
		}
		return (int) (precioTotal*(1-descuento)) ;
	}

	@Override
	public String getNombre() {
		return nombreCombo;
	}

	@Override
	public String generarTextoFactura() {
		return null;
	}
	
	@Override
	public String toString() {
		
		return this.nombreCombo + ": $" + this.getPrecio();
	}

}
