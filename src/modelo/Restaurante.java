package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.File;



//import java.util.HashMap;



public class Restaurante {
	private String nombre;
	
	private static ArrayList<Ingrediente> ingredientes;
	
	private static HashMap<Integer,Pedido> pedidos;
	
	private static Pedido pedidoEnCurso;
	
	private static ArrayList<Combo> combos;
	
	private static ArrayList<ProductoMenu> menuBase;

	// ************************************************************************
	// Constructores
	// ************************************************************************


	
	
	public void IniciarPedido(String nombreCliente, String direccionCliente) 
	{
		
	}
	
	public static void CerrarYGuardarPedido() throws IOException 
	{
		Pedido.cerrar();
	}
	
	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************
	
	public String getNombre() {
		return nombre;
	}

	public ArrayList<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public HashMap<Integer, Pedido> getPedidos() {
		return pedidos;
	}

	public static Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
	}

	public static ArrayList<Combo> getCombos() {
		return combos;
	}

	public static ArrayList<ProductoMenu> getMenuBase() {
		return menuBase;
	}
	
	public static  void cargarInformacionRestaurante(File archivoIngredientes, File archivoMenu, File archivoCombos) throws IOException 
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
		
	}
	

	private static void cargarIngredientes(File archivoIngredientes) throws IOException 
	{
		Restaurante.ingredientes = new ArrayList< Ingrediente>();
		try (BufferedReader br = new BufferedReader(new FileReader(archivoIngredientes))) {
			String linea ;	
			while ((linea = br.readLine()) != null)
			{
				String[] partes = linea.split(";");
				String nombreIngrediente = partes[0];
				int precio = Integer.parseInt(partes[1]);
				Ingrediente elIngrediente = new Ingrediente(nombreIngrediente, precio);
				if (!ingredientes.contains(elIngrediente)) 
				{
					ingredientes.add(elIngrediente);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	
	}
	
	private static void cargarMenu(File archivoMenu) throws IOException 
	{
		Restaurante.menuBase = new ArrayList<ProductoMenu>();
		try (BufferedReader br = new BufferedReader(new FileReader(archivoMenu))) {
			String linea = br.readLine();	
			while (linea != null)
			{
				String[] partes = linea.split(";");
				String nombreMenu = partes[0]; 
				int precioBase = Integer.parseInt(partes[1]);
				ProductoMenu menu = new ProductoMenu(nombreMenu,precioBase);
				if (!menuBase.contains(menu)) 
				{
					menuBase.add(menu);
				}
				
			linea = br.readLine(); 
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void cargarCombos(File archivoCombos) throws IOException 
	{
		Restaurante.combos = new ArrayList<Combo>();
		try (BufferedReader br = new BufferedReader(new FileReader(archivoCombos))) {
			String linea = br.readLine();	
			while (linea != null)
			{
				ArrayList<ProductoMenu> itemsCombos = new ArrayList<ProductoMenu>();
				String[] partes = linea.split(";");
				String nombreCombo = partes[0]; 
				String descuento1 =partes[1];
				String descuento2 = descuento1.replace("%","");
				double descuento3 = Double.parseDouble(descuento2);
				double descuento4 = (descuento3/100);
				for(ProductoMenu producto: menuBase) 
				{
					if(producto.getNombre().equals(partes[2])) 
					{
						itemsCombos.add(producto);
					}
					else if(producto.getNombre().equals(partes[3])) 
					{
						itemsCombos.add(producto);
					}
					else if(producto.getNombre().equals(partes[4])) 
					{
						itemsCombos.add(producto);
					}
				}	
					Restaurante.combos.add(new Combo(nombreCombo,descuento4,itemsCombos));
			linea = br.readLine(); 
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
	public static void iniciarPedido(String nombreCliente, String direccionCliente) 
	{
		pedidoEnCurso = new Pedido(direccionCliente, direccionCliente);
	}
	

	
}
