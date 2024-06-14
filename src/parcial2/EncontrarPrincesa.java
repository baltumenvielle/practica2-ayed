package parcial2;

import java.util.LinkedList;
import java.util.List;

import tp3.ejercicio1.GeneralTree;

public class EncontrarPrincesa {
	
	public static List<Personaje> caminoAccesible(GeneralTree<Personaje> a) {
		List<Personaje> caminoSeguro = new LinkedList<Personaje>(), caminoActual = new LinkedList<Personaje>();
		caminoHelper(a, caminoSeguro, caminoActual);
		return caminoSeguro;
	}
	
	private static void caminoHelper(GeneralTree<Personaje> a, List<Personaje> caminoSeguro, List<Personaje> caminoActual) {
		caminoActual.add(a.getData());
		if (a.getData().isPrincesa()) {
			caminoSeguro.addAll(caminoActual);
			return;
		}
		List<GeneralTree<Personaje>> children = a.getChildren();
		for (GeneralTree<Personaje> child: children) {
			if (!child.getData().isDragon()) caminoHelper(child, caminoSeguro, caminoActual);
		}
		caminoActual.remove(caminoActual.size()-1);
		return;
	}
	
	public static void main(String[] args) {
		Personaje p0 = new Personaje("COYOTE", "Animal");
		Personaje p1 = new Personaje("SIMBA", "Animal");
		Personaje p2 = new Personaje("MUSHU", "Dragón");
		Personaje p3 = new Personaje("TIMON", "Animal");
		Personaje p4 = new Personaje("ELLIOT", "Dragón");
		Personaje p5 = new Personaje("Bella", "Princesa");
		Personaje p6 = new Personaje("ZAZU", "Animal");
		Personaje p7 = new Personaje("RAFIKI", "Animal");
		Personaje p8 = new Personaje("MULAN", "Princesa");
		Personaje p9 = new Personaje("FROZEN", "Princesa");
		Personaje p10 = new Personaje("NALA", "Animal");

		// SIMBA
		GeneralTree<Personaje> a1 = new GeneralTree<>(p1);

		// MUSHU
		GeneralTree<Personaje> a31 = new GeneralTree<>(p3);
		GeneralTree<Personaje> a32 = new GeneralTree<>(p5);
		GeneralTree<Personaje> a33 = new GeneralTree<>(p4);
		List<GeneralTree<Personaje>> hijosMushu = new LinkedList<>();
		hijosMushu.add(a31);
		hijosMushu.add(a32);
		hijosMushu.add(a33);
		GeneralTree<Personaje> a2 = new GeneralTree<>(p2, hijosMushu);

		// ZAZU
		GeneralTree<Personaje> a61 = new GeneralTree<>(p7);
		GeneralTree<Personaje> a62 = new GeneralTree<>(p8);
		List<GeneralTree<Personaje>> hijosZazu = new LinkedList<>();
		hijosZazu.add(a61);
		hijosZazu.add(a62);
		GeneralTree<Personaje> a3 = new GeneralTree<>(p6, hijosZazu);

		// MULAN
		GeneralTree<Personaje> a81 = new GeneralTree<>(p9);
		GeneralTree<Personaje> a82 = new GeneralTree<>(p10);
		List<GeneralTree<Personaje>> hijosMulan = new LinkedList<>();
		hijosMulan.add(a81);
		hijosMulan.add(a82);
		// COYOTE
		List<GeneralTree<Personaje>> hijosCoyote = new LinkedList<>();
		hijosCoyote.add(a1);
		hijosCoyote.add(a2);
		hijosCoyote.add(a3);
		GeneralTree<Personaje> arbol = new GeneralTree<>(p0, hijosCoyote);

		List<Personaje> result = EncontrarPrincesa.caminoAccesible(arbol);
		for (Personaje per : result)
			System.out.println(per.getNombre() + ' ' + per.getTipo());
		}
}
