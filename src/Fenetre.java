import javax.swing.JFrame;


public class Fenetre extends JFrame{
	
	public Fenetre(){
		this.setTitle("TOURNOI");
		this.setSize(1200,720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.NORMAL);
		this.setResizable(true);
		this.setVisible(true);
	}

}
