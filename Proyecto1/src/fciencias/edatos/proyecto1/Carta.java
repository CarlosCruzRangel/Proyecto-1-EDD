package fciencias.edatos.proyecto1;

/**
* Solterona, juego de cartas.
* @author Juan Carlos Zenteno Pompa, Carlos Cruz Rangel
* @version 1.0 noviembre 2021.
* @since Estructuras de datos 2022-1. Proyecto 1
*/

/**
 * ---------------------------------------------------------------------
 * Clase Carta para juego Solterona 
 * ---------------------------------------------------------------------
 */

public class Carta {
	private String palo;
	private int valorDeCara;

	/**
  * ---------------------------------------------------------------------
  * Metodo Getter para cambiar valores de Palo y el valor de la cara
  * ---------------------------------------------------------------------
  */
 
	public String getPalo() {
		return palo;
	}

	public int getValorDeCara() {
		return valor;
	}


 
 /**
  * ---------------------------------------------------------------------
  * Metodo Setter para obtener valores de Palo y el valor de la cara
  * ---------------------------------------------------------------------
  */
 

	public void setPalo(String s) {
		this.palo = p;
	}

	public void setValorDeCara(int v) {
		this.valor = v;
	}


	public Card(){
		palo = null;
		valor = -1;
	}
}