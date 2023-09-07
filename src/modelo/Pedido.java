package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Pedido 
{
	private static int numeroPedidos;
	private static int idPedido;
	private static String nombreCliente;
	private static String direccionCliente;
	private static ArrayList<Producto> itemsPedido;
	
	public Pedido(String nombreCliente, String direccionCliente) {
		int id = new Random().nextInt(9999999 - 1000000 +1 ) + 1000000;
		Pedido.idPedido=id;
		Pedido.nombreCliente = nombreCliente;
		Pedido.direccionCliente = direccionCliente;
		Pedido.itemsPedido = new ArrayList<Producto>();
	}
	public int getNumeroPedidos() {
		return numeroPedidos;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public String getDireccionCliente() {
		return direccionCliente;
	}
	public ArrayList<Producto> getItemsPedido() {
		return itemsPedido;
	}
	public void addProducto(Producto producto) 
	{
		itemsPedido.add(producto);
	}
	public static void cerrar() throws IOException
	{
		BufferedWriter escribir = new BufferedWriter(new FileWriter(String.format("./recibos/%s.txt", idPedido)));
		escribir.write(recibo());
		escribir.close();
	}
	
	public static String recibo() 
	{
		double precio = 0;
		String recibo = "=".repeat(75)+"\n";
		recibo = recibo.concat("CLIENTE"+"    "+ "DIRECCION"+"    "+"ID"+"\n");
		recibo = recibo.concat(nombreCliente + "    "+ direccionCliente +idPedido+"\n");
		recibo= recibo.concat("=".repeat(70)+"\n");
		for(Producto producto: itemsPedido) 
		{
			precio+= producto.getPrecio();
			recibo = recibo.concat(producto.generarTextoFactura());
		}
		recibo= recibo.concat("NETO"+"    " + "$" + precio+"\n");
		recibo= recibo.concat("IMPUESTOS"+"    "+ "19%"+"\n");
		recibo = recibo.concat("TOTAL"+"    "+"$"+ precio*(1.19));
		recibo= recibo.concat("=".repeat(70)+"\n");
		System.out.println(recibo);
		return recibo + "=".repeat(70);
		
	}
	
	
	
}
