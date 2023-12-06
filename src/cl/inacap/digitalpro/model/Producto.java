package cl.inacap.digitalpro.model;

public class Producto {
	private String codigoDeProducto;
	private String nombre;
	private String categoria;
	private int valor;
	
	//Método constructor.
	public Producto(String Codigo, String Nombre, String Categoria, int Valor){
		this.codigoDeProducto = Codigo;
		this.nombre = Nombre;
		this.categoria = Categoria;
		this.valor = Valor;
	}
	
	public String toString() {
		String valorString = Integer.toString(this.valor);
		return "Codigo: " + this.codigoDeProducto + " Nombre: " + nombre + " Categoria: " + categoria + " Valor: " + valorString;
	}
	
	public String getCodigoDeProducto(){
		return "El codigo del producto es: " + this.codigoDeProducto;
	}
	
	public String getNombre(){
		return "El nombre del producto es: " + this.nombre;
	}
	
	public String getCategoria(){
		return "La categoria del producto es: " + this.categoria;
	}
	
	public String getValor(){
		return "El valor del producto es: " + this.valor;
	}
}
