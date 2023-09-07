package modelo;

public class ProductoMenu implements Producto{
	private String nombre;
	private int precioBase;
	
	

	public ProductoMenu(String nombre, int precioBase) {
		super();
		this.nombre = nombre;
		this.precioBase = precioBase;
	}

	@Override
	public int getPrecio() {
		return precioBase;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String generarTextoFactura() {
		return "producto: %s"+ this.nombre+ "      "+ this.precioBase+"\n";
	}
	@Override
	public String toString() {
		
		return this.nombre + ": $" + this.getPrecio();
	}

}
