package consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import modelo.Combo;
import modelo.ProductoMenu;
import modelo.Restaurante;

public class Aplicacion {
	private Restaurante restaurante;

	private void ejecutarAplicacion() throws IOException 
	{
		 System.out.println("Bienvenido a P Burguers\n");
		 ejecutarCargarDatos();
		 boolean continuar = true;
			while (continuar) 
			{
				try 
				{
					mostrarMenu();
					
					int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
					if(opcion_seleccionada== 1) 
					{
						ejecutarMostrarMenu();
					}
					
					if(opcion_seleccionada == 2) 
					{
						ejecutarIniciarNuevoPedido();
					}
					if(opcion_seleccionada==3) 
					{
						ejecutarAgregarElemento();
					}
					if(opcion_seleccionada==4) 
					{
						ejecutarCerrarPedido();
					}
					if(opcion_seleccionada==6) 
					{
						continuar = false;
					}
					
				}
				
				catch (NumberFormatException e)
				{
					System.out.println("Debe seleccionar uno de los números de las opciones.");
				}
			}
			
	}
	
	private void ejecutarIniciarNuevoPedido()
	{
		if(Restaurante.getPedidoEnCurso()== null) 
		{
			String nombreCliente = input("Ingrese el nombre del cliente");
			String direccion= input("Ingrese la direccion del cliente");
			if (nombreCliente.equals("") || direccion.equals(""))
			{
	        System.out.println("El dato ingresado no es valido");
			}
			else 
			{
				Restaurante.iniciarPedido(nombreCliente, direccion);
			}
		}
		else System.out.println("Actualmente hay un pedido en proceso :)");
	}
	
	private void ejecutarCargarDatos() throws IOException 
	{
		System.out.println("Cargando datos del restaurante\n");
		File archivo1 = new File ("./data/ingredientes.txt");
		File archivo2 = new File("./data/menu.txt");
		File archivo3 = new File("./data/combos.txt");
		
		Restaurante.cargarInformacionRestaurante(archivo1,archivo2,archivo3);
		System.out.println("Se han cargado los datos del restaurante");
	}


	private String input(String mensaje) 
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public void ejecutarCerrarPedido() throws IOException 
	{
		Restaurante.CerrarYGuardarPedido();
	}
	
	public void ejecutarAgregarElemento() 
	{
		int opcion_seleccionada = Integer.parseInt(input("Seleccione opcion 1 para agregar un combo o 2 para agregar un menu"));
		if(opcion_seleccionada==1) 
		{
			int opcion_seleccionada2 = Integer.parseInt(input("Seleccione el numero del combo: "))-1;
			Combo combo = Restaurante.getCombos().get(opcion_seleccionada2);
			Restaurante.getPedidoEnCurso().addProducto(combo);
		}
		else if(opcion_seleccionada==2)
		{
			int opcion_seleccionada2 = Integer.parseInt(input("Seleccione el numero del producto del menu: "))-1;
			ProductoMenu menu = Restaurante.getMenuBase().get(opcion_seleccionada2);
			Restaurante.getPedidoEnCurso().addProducto(menu);
		}
	}

	public  void ejecutarMostrarMenu() 
	{
		System.out.println("Combos disponobles:");
		System.out.println("=".repeat(50));
		for(int index=0; index< Restaurante.getCombos().size(); index++) 
		{
			System.out.println((index+1)+". "+Restaurante.getCombos().get(index).toString());
		}
		System.out.println("Menu base:");
		System.out.println("=".repeat(50));
		for(int index=0; index< Restaurante.getMenuBase().size(); index++) 
		{
			System.out.println((index+1)+". "+Restaurante.getMenuBase().get(index).toString());
		}
		System.out.println("=".repeat(50));	
	}
	
	public void mostrarMenu() 
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar menú");
		System.out.println("2. Iniciar un nuevo pedido");
		System.out.println("3. Agregar un elemento a un pedido ");
		System.out.println("4. Cerrar pedido y guardar factura");
		System.out.println("5. Consultar la informacion de un pedido (ID)");
		System.out.println("6. Cerrar la aplicacion");
	}
	
	
	
	public static void main(String[] args) throws IOException 
	{
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}



}
