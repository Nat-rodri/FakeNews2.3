package estructuraTP;

import javax.swing.JFrame;
import Pantallas.Menu;


public class PantallaP {

		public static void main(String[] args) {
			JFrame marco = new JFrame();
			marco.setVisible(true);
			marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			marco.setBounds(0, 0, 840, 540);
			Menu tabla = new Menu(marco);
			marco.setContentPane(tabla);
			marco.validate();
			}
		}

