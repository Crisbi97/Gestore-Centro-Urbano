package testerC;


import classiC.Strada;

public class TestStrada {
	
	public static void main(String[] args) {
		
		System.out.println("Istanzio un nuova strada");
		Strada l = new Strada(50, 5, 500);
		
		System.out.println("Stampo la strada:");
		System.out.println(l.toString());
		
		System.out.println("Testo i metodi modificatori:");
		System.out.println("sommo 1 ad ogni variabile");
		l.setEfficienza(l.getEfficienza() + 1);
		l.setInvecchiamento(l.getInvecchiamento() + 1);
		l.setBuffValore(l.getBuffValore() + 1);
		
		System.out.println("Testo i metodi di accesso: ");
		System.out.println("coefficiente di efficienza = " + l.getEfficienza());
		System.out.println("coefficiente di invecchiamento = " + l.getInvecchiamento());
		System.out.println("bonus valore = " + l.getBuffValore());
		
		System.out.println("Istanzio un nuova strada");
		Strada l1 = new Strada(100, 10, 1000);
		
		System.out.println("Stampo la strada:");
		System.out.println(l1.toString());
		
		System.out.println("Testo l'equals sulle due strade:");
		System.out.println(l.equals(l1));
		
		System.out.println("Testo il clone sulla prima strada:");
		System.out.println(l.clone());
		
		System.out.println("Eseguo l'equals del clone sulla prima strada:");
		System.out.println(l.equals(l.clone()));
	}

}
