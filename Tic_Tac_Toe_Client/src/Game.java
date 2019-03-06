import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game extends JFrame implements MouseListener   {

	private JPanel contentPane;
	String signe ="", msg=" " ,coord;
	JLabel lbl_signe;
	JButton b_0_0,b_0_1,b_0_2,b_1_0,b_1_1,b_1_2,b_2_0,b_2_1,b_2_2,b;
	Socket socket;
	BufferedReader in;
	PrintWriter pw;
	HashMap componentMap ;
	int res = -1 ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public Game() throws UnknownHostException, IOException 
	{
		socket = new Socket("127.0.0.1",1234);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw = new PrintWriter(socket.getOutputStream(),true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		b_0_0 = new JButton("");
		b_0_0.setName("0,0");
		b_0_0.setBounds(37, 28, 78, 77);
		contentPane.add(b_0_0);
		b_0_0.addMouseListener(this);
		
		b_0_1 = new JButton("");
		b_0_1.setName("0,1");
		b_0_1.setBounds(141, 28, 78, 77);
		contentPane.add(b_0_1);
		b_0_1.addMouseListener(this);
		
		b_0_2 = new JButton("");
		b_0_2.setName("0,2");
		b_0_2.setBounds(245, 28, 78, 77);
		contentPane.add(b_0_2);
		b_0_2.addMouseListener(this);
		
		b_1_0 = new JButton("");
		b_1_0.setName("1,0");
		b_1_0.setBounds(37, 133, 78, 77);
		contentPane.add(b_1_0);
		b_1_0.addMouseListener(this);
		
		b_1_1 = new JButton("");
		b_1_1.setName("1,1");
		b_1_1.setBounds(141, 133, 78, 77);
		contentPane.add(b_1_1);
		b_1_1.addMouseListener(this);
		
		b_1_2 = new JButton("");
		b_1_2.setName("1,2");
		b_1_2.setBounds(245, 133, 78, 77);
		contentPane.add(b_1_2);
		b_1_2.addMouseListener(this);
		
		b_2_0 = new JButton("");
		b_2_0.setName("2,0");
		b_2_0.setBounds(37, 238, 78, 77);
		contentPane.add(b_2_0);
		b_2_0.addMouseListener(this);
		
		b_2_1 = new JButton("");
		b_2_1.setName("2,1");
		b_2_1.setBounds(141, 238, 78, 77);
		contentPane.add(b_2_1);
		b_2_1.addMouseListener(this);
		
		b_2_2 = new JButton("");
		b_2_2.setName("2,2");
		b_2_2.setBounds(245, 238, 78, 77);
		contentPane.add(b_2_2);
		b_2_2.addMouseListener(this);
		
		lbl_signe = new JLabel("");
		lbl_signe.setBounds(424, 28, 115, 33);
		contentPane.add(lbl_signe);
		
		
		
		
		(new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try 
				{
					
					signe = in.readLine();
					lbl_signe.setText(signe);
					msg = in.readLine();
					System.out.println(msg);
					if(msg.equals("wait"))
					{
						pause();

					}
					else {
						play();
					}
					
					
					
					
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

					
					while(res==-1)
					{
					
						if(!msg.equals("play"))
						{

							try {
								
								res= Integer.parseInt(in.readLine());
								coord = in.readLine();

								
								 JButton btn = (JButton) findComponentByName(contentPane,coord);
								 System.out.println(btn.getName());

								 if (signe.equals("X")) {
									 btn.setText("O");
								}
								 else if(signe.equals("O"))
								 {
									 btn.setText("X");
								 }
								 else{
									 System.out.println("nono");
								 }
								 
								 switch (res) {
										case 1:
											javax.swing.JOptionPane.showMessageDialog(null,"player1 a win"); 
											break;
										case 2:
											javax.swing.JOptionPane.showMessageDialog(null,"player2 a win");
											break;
										case 0:
											javax.swing.JOptionPane.showMessageDialog(null,"drow");
											break;
		
										default:
											break;
								}
								
								System.out.println("coord boucle "+coord+"  4444444444444");
								msg = in.readLine();
								System.out.println("msg boucle "+msg+"  555555555555");
								play();
							} catch (NumberFormatException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						
					}
							
					
				}
			})).start();
		
		
		
		
		
		
		
	}
	
	public void pause()
	{
		// enabled all the buttons 
		b_0_0.setEnabled(false);
		b_0_1.setEnabled(false);
		b_0_2.setEnabled(false);
		b_1_0.setEnabled(false);
		b_1_1.setEnabled(false);
		b_1_2.setEnabled(false);
		b_2_0.setEnabled(false);
		b_2_1.setEnabled(false);
		b_2_2.setEnabled(false);		
	}
	
	public void play()
	{
		b_0_0.setEnabled(true);
		b_0_1.setEnabled(true);
		b_0_2.setEnabled(true);
		b_1_0.setEnabled(true);
		b_1_1.setEnabled(true);
		b_1_2.setEnabled(true);
		b_2_0.setEnabled(true);
		b_2_1.setEnabled(true);
		b_2_2.setEnabled(true);		
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		
		b = (JButton) e.getSource();
		if (b.getText().isEmpty()) {
			b.setText(signe);
			String action =b.getName();
			//  ###############  a new Thread #########################
			(new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					if(msg.equals("play"))
					{
						//set the action to server
						pw.println(action);
						try {
							// read the result
							res= Integer.parseInt(in.readLine());
							System.out.println(res);
							//get the msg from serve
							msg = in.readLine();
							System.out.println(msg);
						} catch (NumberFormatException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// Enabled the buttons
						pause();
					}
				}
				//#############  start the thread ################
			})).start();
		}
		
		
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	 public Component findComponentByName(Container container, String componentName) {
		  for (Component component: container.getComponents()) {
		    if (componentName.equals(component.getName())) {
		      return component;
		    }
		  }
		  return null;
		}
}
