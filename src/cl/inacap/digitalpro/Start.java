package cl.inacap.digitalpro;
import java.io.FileWriter;
import java.util.Scanner;
import org.json.simple.JSONObject;

import cl.inacap.digitalpro.model.Producto;

public class Start {
	static JSONObject obj = new JSONObject(); //Este objeto permite almacenar un dato en el archivo Json.
	static Scanner sc = new Scanner(System.in);
	static String compruebaLetras[] = {"A", "B", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	static String compruebaNumeros[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	static Producto lista[] = new Producto[1000000];
	static String codigo = "";
	static int numero = 0;
	static int cantidadProductos = 0;
	static String almacenaCodigos[] = new String[1000000];
	static int indiceCodigo;
	static int sumaValor = 0;
	static int cuentaValor = 0;
	static boolean preguntaJson = true;
	static String rutaJson = "";
	
	//Menu de navegación.
	public static boolean menu() { 
		boolean continuar = true;
		System.out.println("1. Ingresar producto");
		System.out.println("2. Buscar producto");
		System.out.println("3. Mostrar productos");
		System.out.println("0. Salir");
		switch (sc.nextLine().trim()) {
		case "1":
			ingresarProducto();
			break;
		case "2":
			buscarProducto();
			break;
		case "3":
			mostrarProductos();
			break;
		case "0":
			continuar = false;
			break;
		default:
			System.out.println("Opcion incorrecta");
			break;
		}
		return continuar;
	}
	
	//Pide leer los datos necesarios para crear el objeto.
	@SuppressWarnings("unchecked")
	public static void ingresarProducto() { 

		//Proceso de ingreso y validación del código del producto.
		while (true) {
			boolean salir = false;
			
			//Se verifica que se ingrese un codigo con 7 caracteres exactos.
			while (true) {
				System.out.println("Ingrese el código del producto porfavor, ejemplo: AA-9999");
				codigo = sc.nextLine().trim();
				if (codigo.length() != 7) {
					System.out.println("Su codigo no es valido, ingreselo nuevamente");
				}
				else {
					break;
				}
			}

			int contador = 0; //Este contador tiene que ser igual a 7 para que se valide el codigo del producto (Se salga del bucle principal).
			for (int i = 0; i < 7; i++) { //Revisa las posisiones del string llamado Codigo.
		
				for (int j = 0; j < compruebaLetras.length; j++) { //Este bucle for anidado solo sirve para comprobar las primeras 2 letras del string llamado Codigo.
					if (i == 0) { //Comprueba la posicion 0 del string llamado Codigo. 
						if (compruebaLetras[j].equals(Character.toString(codigo.charAt(0)))) { //Si el indice del array es igual a la posición del string el contador aumtenta.
							contador++;
						}
					}
					if (i == 1) {
						if (compruebaLetras[j].equals(Character.toString(codigo.charAt(1)))) { //Si el indice del array es igual a la posición del string el contador aumtenta.
							contador++;
						}
					}
				}
				if (i == 2) {
					if ("-".equals(Character.toString(codigo.charAt(2)))) { //Si la posición 2 del string es igual a "-" aumenta el contador (Tiene q estar afuera del bucle j porq o si no suma las 27 veces.)
						contador++;
					}	
				}
				if (i == 3) {
					for(int k = 0; k < 10; k++) {
						if (compruebaNumeros[k].equals(Character.toString(codigo.charAt(3)))) {
							contador++;
						}
					}
					
				}
				if (i == 4) {
					for (int k = 0; k < 10; k++) {
						if (compruebaNumeros[k].equals(Character.toString(codigo.charAt(4)))) {
							contador++;
						}
					}
				}
				if (i == 5) {
					for (int k = 0; k < 10; k++) {
						if (compruebaNumeros[k].equals(Character.toString(codigo.charAt(5)))) {
							contador++;
						}
					}
				}
				if (i == 6) {
					for (int k = 0; k < 10; k++) {
						if (compruebaNumeros[k].equals(Character.toString(codigo.charAt(6)))) {
							contador++;
						}
					}
				}
			}
			
			if (contador < 7) {
				System.out.println("Su codigo no es valido, ingreselo nuevamente");
			}
			
			if (contador == 7 && almacenaCodigos[0] == null) { //Si el código es valido y no hay codigo en ese Array se ingresa y se sale del bucle.
				almacenaCodigos[0] = codigo; //Almacena el codigo en el indice [0] de la lista.
				indiceCodigo = 0;
				break;
			}
			
			for (int k = 0; k < almacenaCodigos.length; k++) {
				if (contador == 7 && codigo.equals(almacenaCodigos[k]) == true) { //Compara si el codigo está repetido.
					System.out.println("Tu codigo está repetido vuelve a ingresarlo");
					salir = false;
					break;
				}
				else {
					salir = true;
				}
			}
			
			if (salir == true && contador == 7) { //Si esta condición se cumple se sale del bucle.
				indiceCodigo++;
				almacenaCodigos[indiceCodigo] = codigo; //Almacena el codigo en la lista.
				break;
			}
		}

		//Proceso de asignación del nombre para el producto.
		System.out.println("Ingrese el nombre del producto");
		String nombre = sc.nextLine().trim();
		
		//Proceso de validación del nombre del producto.
		while (nombre.length() < 5 || nombre.length() > 20) { 
			if (nombre.length() < 5) {
				System.out.println("El nombre del producto es demasiado corto, ingreselo nuevamente");
				nombre = sc.nextLine().trim();
			}
			else {
				System.out.println("El nombre del producto es demasiado largo, ingreselo nuevamente");
				nombre = sc.nextLine().trim();
			}
		}
		
		//Proceso de asignación de la categoria del producto.
		System.out.println("Escriba la categoria del producto (Computadores; Tablets; Smartphones; Accesorios Generales; Red)");
		String categoria = sc.nextLine().toLowerCase().trim();
		
		//Validación de la categoria del producto.
		//Repite mientras todo sea igual a false, (con que una condición sea igual a true el flujo de ejecución se sale del bucle).
		while (categoria.equalsIgnoreCase("Computadores") == false &&
			categoria.equalsIgnoreCase("Tablets") == false &&
			categoria.equalsIgnoreCase("Smartphones") == false &&
			categoria.equalsIgnoreCase("Accesorios Generales") == false &&
			categoria.equalsIgnoreCase("Red") == false) {
			
			System.out.println("Escribiste una categoria que no existe, escribela nuevamente");
			categoria = sc.nextLine().toLowerCase().trim();
		}
		
		//Proceso de asignación del valor del producto y de validación (aprueba de errores mediante una excepción general).
		int valor = 0;
		boolean validado = true;
		while (validado) {
			try {
				if (valor > 1000 && valor <= 999999) {
					break;
				}
				System.out.println("Ingrese el valor del producto");
				valor = Integer.parseInt(sc.nextLine().trim());
				while (valor < 1000 || valor >= 999999) { //Con que una condición se cumpla el bucle se vuelve a repetir.

					if (valor < 1000) {
						System.out.println("El valor del producto es demasiado bajo, ingreselo nuevamente");
						valor = Integer.parseInt(sc.nextLine().trim());	
					}
					if (valor >= 999999) {
						System.out.println("El valor del producto es demasiado alto, ingreselo nuevamente");
						valor = Integer.parseInt(sc.nextLine().trim());	
					}
				}
				sumaValor = sumaValor + valor; //Almacena la suma de todos los valores ingresados.
				cuentaValor++; //Cuenta la cantidad de valores ingresados.
				
			} catch (Exception ex) {
				System.out.println("Debe ser un numero válido, ingrese el valor del producto nuevamente");
				valor = -1;
			}
		}
		
		
		Producto Producto = new Producto(codigo, nombre, categoria, valor); //Proceso de la instanciación del Objeto utilizando el método constructor.
			
		for (int i = 0; i < lista.length; i++) { //Llena la lista con los objetos instanciados.
			if (lista[i] == null) {
				numero++;
				lista[i] = Producto; //Agregando el producto a el Array.
				obj.put(codigo, lista[i].toString()); //El objeto del Array es agregado al Json.
				break; //Este break es necesario para que solo llene un indice.
			}
		}
		
		cantidadProductos++;
		
			while (preguntaJson == true) {
				System.out.println("Ingrese la ruta donde desea guardar su archivo json Ejemplo: C:\\Users\\Usuario\\Desktop");
				rutaJson = sc.nextLine().trim(); //Pide ingresar la ruta donde se guardará el archivo json (Solo pedirá la ruta una vez).
				
				try (FileWriter file = new FileWriter(rutaJson + "\\Productos.json")){ //Lo que esta en las comillas como se llamará el archivo Json.
					file.write(obj.toString());
					file.flush();
					preguntaJson = false;
					break;
				} catch (Exception e) {
					System.out.println("La ruta especificada no existe ó no posee permisos de escritura, ingrese una nueva ruta");
				}	
			}
		}

	//Busca los productos en el archivo Json.
	public static void buscarProducto() {
		System.out.println("Ingrese el código del producto que desea buscar");
		String buscarCodigo = sc.nextLine().trim();
		if (obj.get(buscarCodigo) != null) {
			System.out.println(obj.get(buscarCodigo));
		}
		else {
			System.out.println("Su producto no existe");
		}
	}
	
	//Muestra todos los objetos almacenados en el archivo Json e imprime la cantidad de productos agregados.
	public static void mostrarProductos(){
		double promedio = sumaValor / cuentaValor;
		System.out.println(obj); //Imprimiendo el archivo Json.
		System.out.println("Hay " + cantidadProductos + " productos existentes en el sistema");
		System.out.println("El valor promedio de todos los productos ingresados es: " + promedio);
	}
	
	public static void main(String[] args) {
		while (menu()); //Repite el menu hasta que el valor que retorna deje de ser true.
	}
}
