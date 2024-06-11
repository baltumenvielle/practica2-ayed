package tp3.ejercicio1;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GeneralTree<T>{

	private T data;
	private List<GeneralTree<T>> children = new LinkedList<GeneralTree<T>>(); 

	public GeneralTree() {
		
	}
	public GeneralTree(T data) {
		this.data = data;
	}

	public GeneralTree(T data, List<GeneralTree<T>> children) {
		this(data);
		this.children = children;
	}	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<GeneralTree<T>> getChildren() {
		return this.children;
	}
	
	public void setChildren(List<GeneralTree<T>> children) {
		if (children != null)
			this.children = children;
	}
	
	public void addChild(GeneralTree<T> child) {
		this.getChildren().add(child);
	}

	public boolean isLeaf() {
		return !this.hasChildren();
	}
	
	public boolean hasChildren() {
		return !this.children.isEmpty();
	}
	
	public boolean isEmpty() {
		return this.data == null && !this.hasChildren();
	}

	public void removeChild(GeneralTree<T> child) {
		if (this.hasChildren())
			children.remove(child);
	}
	
	public List<Integer> numerosImparesMayoresQuePreOrden (Integer n) {
		List<Integer> lista = new LinkedList<Integer>();
		if (!this.isEmpty()) {
			this.helperPreOrden(lista, n);
		}
		return lista;
	}
	
	private void helperPreOrden(List<Integer> lista, Integer n) {
		List<GeneralTree<T>> children = this.getChildren();
		int num = (int) this.getData();
		
		if (num> n && num % 2 != 0)  lista.add(num);
		
		if (this.hasChildren()) {
			for (GeneralTree<T> child: children) {
				child.helperPreOrden(lista, n);
			}
		}
	}
	
	public List<Integer> numerosImparesMayoresQueInOrden (Integer n) {
		List<Integer> lista = new LinkedList<Integer>();
		if (!this.isEmpty()) {
			this.helperInOrden(lista, n);
		}
		return lista;
	}
	
	private void helperInOrden(List<Integer> lista, Integer n) {
		List<GeneralTree<T>> children = this.getChildren();
		if (this.hasChildren()) {
			children.get(0).helperInOrden(lista, n);
		}
		
		int num = (int) this.getData();
		if (num > n && num % 2 != 0) lista.add(num);
		
		for (int i=1;i<children.size();i++) {
			children.get(i).helperInOrden(lista, n);
		}
	}
	
	public List<Integer> numerosImparesMayoresQuePostOrden (Integer n) {
		List<Integer> lista = new LinkedList<Integer>();
		if (!this.isEmpty()) {
			this.helperPostOrden(lista, n);
		}
		return lista;
	}
	
	private void helperPostOrden(List<Integer> lista, Integer n) {
		List<GeneralTree<T>> children = this.getChildren();
		if (this.hasChildren()) {
			for (GeneralTree<T> child: children) {
				child.helperPostOrden(lista, n);
			}
		}
		int num = (int) this.getData();
		if (num> n && num % 2 != 0)  lista.add(num);
	}
	
	public List<Integer> numerosImparesMayoresQuePorNiveles(Integer n) {
		List<Integer> lista = new LinkedList<Integer>();
		Queue<GeneralTree<T>> cola = new LinkedList<GeneralTree<T>>();
		
		if (!this.isEmpty()) {
			cola.add(this);
			cola.add(null);
			
			while (!cola.isEmpty()) {
				GeneralTree<T> nodo = cola.remove();
				if (nodo != null) {
					int num = (int) nodo.getData();
					if (num % 2 != 0 && num > n) lista.add(num);
					
					List<GeneralTree<T>> children = nodo.getChildren();
					for (GeneralTree<T> child: children) {
						cola.add(child);
					}
				}
				else if (!cola.isEmpty()) {
					cola.add(null);
				}
			}
		}
		return lista;
	}
	
	public int altura() {	 
		if (this.isEmpty()) return 0;
		if (this.isLeaf()) return 0;
		int maxAltura = 0;
		if (this.hasChildren()) {
			for (GeneralTree<T> child: this.getChildren()) {
				maxAltura = Math.max(maxAltura, child.altura());
			}
		}
		return maxAltura + 1;
	}
	
	public int nivel(T dato){
		Queue<GeneralTree<T>> cola = new LinkedList<GeneralTree<T>>();
		if (!this.isEmpty()) {
			int nivel = 0;
			
			cola.add(this);
			cola.add(null);
			
			while (!cola.isEmpty()) {
				GeneralTree<T> nodo = cola.remove();
				if (nodo != null) {
					if (nodo.getData() == dato) {
						return nivel;
					}
					
					List<GeneralTree<T>> children = nodo.getChildren();
					for (GeneralTree<T> child: children) {
						cola.add(child);
					}
				}
				else if (!cola.isEmpty()) {
					cola.add(null);
					nivel++;
				}
			}
		}
		return -1;
	  }

	public int ancho(){
		Queue<GeneralTree<T>> cola = new LinkedList<GeneralTree<T>>();
		int anchoMax = 0;
		if (!this.isEmpty()) {
			cola.add(this);
			cola.add(null);
			
			int ancho = 0;
			
			while (!cola.isEmpty()) {
				GeneralTree<T> nodo = cola.remove();
				if (nodo != null) {
					List<GeneralTree<T>> children = nodo.getChildren();
					ancho = children.size();
					for (GeneralTree<T> child: children) {
						cola.add(child);
					}
				}
				else if (!cola.isEmpty()) {
					cola.add(null);
					anchoMax = Math.max(anchoMax, ancho);
				}
			}
		}
		return anchoMax;
	}
	
	public boolean esAncestro(T a, T b) {
		if (!this.isEmpty()) {
			GeneralTree<T> nodo = this.encontrarDato(a);
			if (nodo != null) {
				nodo = nodo.encontrarDato(b);
				return nodo != null;
			}
		}
		return false;
	}
	
	private GeneralTree<T> encontrarDato(T dato) {
		if (this.getData() == dato) return this;
		if (this.hasChildren()) {
			List<GeneralTree<T>> children = this.getChildren();
			for (GeneralTree<T> child: children) {
				child.encontrarDato(dato);
			}
		}
		return null;
	}
}