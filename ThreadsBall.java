import java.awt.geom.*;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class ThreadsBall {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame marco=new MarcoRebote();
		
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		marco.setVisible(true);

	}

}

class PelotaHilos implements Runnable{

	public PelotaHilos(Pelota p, Component c){
		this.p = p;
		this.c = c;
	}

	public void run(){
		while(!Thread.interrupted()){
				
			try{
				Thread.sleep(4);
			}catch(InterruptedException e){
				//e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			

			this.p.mueve_pelota(this.c.getBounds());
			
			this.c.paint(this.c.getGraphics());
			
		}
	}

	private Pelota p;
	private Component c;



}


//Movimiento de la pelota-----------------------------------------------------------------------------------------

class Pelota{
	
	// Mueve la pelota invirtiendo posici�n si choca con l�mites
	
	public void mueve_pelota(Rectangle2D limites){
		
		x+=dx;
		
		y+=dy;
		
		if(x<limites.getMinX()){
			
			x=limites.getMinX();
			
			dx=-dx;
		}
		
		if(x + TAMX>=limites.getMaxX()){
			
			x=limites.getMaxX() - TAMX;
			
			dx=-dx;
		}
		
		if(y<limites.getMinY()){
			
			y=limites.getMinY();
			
			dy=-dy;
		}
		
		if(y + TAMY>=limites.getMaxY()){
			
			y=limites.getMaxY()-TAMY;
			
			dy=-dy;
			
		}
		
	}
	
	//Forma de la pelota en su posici�n inicial
	
	public Ellipse2D getShape(){
		
		return new Ellipse2D.Double(x,y,TAMX,TAMY);
		
	}	
	
	private static final int TAMX=15;
	
	private static final int TAMY=15;
	
	private double x=0;
	
	private double y=0;
	
	private double dx=1;
	
	private double dy=1;
	
	
}

// L�mina que dibuja las pelotas----------------------------------------------------------------------


class LaminaPelota extends JPanel{
	
	//A�adimos pelota a la l�mina
	
	public void add(Pelota b){
		
		pelotas.add(b);
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;
		
		for(Pelota b: pelotas){
			
			g2.fill(b.getShape());
		}
		
	}
	
	private ArrayList<Pelota> pelotas=new ArrayList<Pelota>();
}


//Marco con l�mina y botones------------------------------------------------------------------------------

class MarcoRebote extends JFrame{
	
	public MarcoRebote(){
		
		setBounds(600,300,400,350);
		
		setTitle ("Rebotes");
		
		lamina=new LaminaPelota();
		
		add(lamina, BorderLayout.CENTER);
		
		JPanel laminaBotones=new JPanel();
		
		b1 = new JButton("Hilo 1");

		b1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				comienza_el_juego(e);
			}

		});

		laminaBotones.add(b1);


		b2 = new JButton("Hilo 2");

		b2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				comienza_el_juego(e);
			}

		});

		laminaBotones.add(b2);

		b3 = new JButton("Hilo 3");

		b3.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				comienza_el_juego(e);
			}

		});

		laminaBotones.add(b3);

		d1 = new JButton("Detener Hilo 1");

		d1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				parar_hilo(e);
			}

		});

		laminaBotones.add(d1);

		d2 = new JButton("Detener Hilo 2");

		d2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				parar_hilo(e);
			}

		});

		laminaBotones.add(d2);

		d3 = new JButton("Detener Hilo 3");

		d3.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				parar_hilo(e);
			}

		});

		laminaBotones.add(d3);
		add(laminaBotones, BorderLayout.SOUTH);
	}
	
	
	//A�ade pelota y la bota 1000 veces
	
	public void comienza_el_juego (ActionEvent e){
		
					
			Pelota pelota=new Pelota();
			
			lamina.add(pelota);
			
			Runnable r = new PelotaHilos(pelota, lamina);

			if(e.getSource().equals(b1)){
				t1 = new Thread(r);

				t1.start();
			}else if(e.getSource().equals(b2)){
				t2 = new Thread(r);

				t2.start();
			}else if(e.getSource().equals(b3)){
				t3 = new Thread(r);

				t3.start();
			}


			
			
		
		
	}
	
	private LaminaPelota lamina;
	
	

	public void parar_hilo(ActionEvent e){
		//t.stop();
		if(e.getSource().equals(d1)){
			t1.interrupt();
		}else if(e.getSource().equals(d2)){
			t2.interrupt();
		}else if(e.getSource().equals(d3)){
			t3.interrupt();
		}
	}

	Thread t1, t2, t3;

	JButton b1, b2, b3, d1, d2, d3;

}
