
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Programme {

	static int[][] M = new int[3][3];
	public static void main(String[] args) 
	{		
		
		(new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try 
				{
					ServerSocket ss = new ServerSocket(1234);
					System.out.println("wait for connection player 1 ...");
					Socket s1 = ss.accept();
					BufferedReader in1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
					PrintWriter out1 = new PrintWriter(s1.getOutputStream(),true);			
					out1.println("X");
					
					System.out.println("wait for connection player 2 ...");
					Socket s2 = ss.accept();
					BufferedReader in2 = new BufferedReader(new InputStreamReader(s2.getInputStream()));
					PrintWriter out2 = new PrintWriter(s2.getOutputStream(),true);
					out2.println("O");
					
					out1.println("play");
					out2.println("wait");
					
					boolean role ;
					int test=-1;
					

					while(test==-1)
					{
						// player1 => "x,y"
						String coord = in1.readLine();
						System.out.println(coord+" #################################################");
						String[] infos = coord.split(","); // => array{'x','y'}     
						int i = Integer.parseInt(infos[0]); // => 'x' => x
						int j = Integer.parseInt(infos[1]); // => 'y' => y
						M[i][j] = 1 ;
						
						//Test Game Over
						test = gameOver();
						System.out.println(test);
						out1.println(test);
						out2.println(test);
						out2.println(coord);
						
						if(test!=-1)
						{
							break ;
						}
						
						
						out2.println("play");
						out1.println("wait");
						
						// player2 => "x,y"
						coord = in2.readLine();
						System.out.println(coord+" #################################################");
						infos = coord.split(","); // => array{'x','y'}     
						i = Integer.parseInt(infos[0]); // => 'x' => x
						j = Integer.parseInt(infos[1]); // => 'y' => y
						M[i][j] = -1 ;
						
						
						//Test Game Over
						test = gameOver();
						out2.println(test);
						out1.println(test);
						out1.println(coord);
						
						if(test!=-1)
							break ;
						
						out1.println("play");
						out2.println("wait");			
					}
								
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
				
			}
		})).start();
		

	}
	
	public static int gameOver() // 1 = player1 ; 2 = Player2, 0 = draw, -1 
	{
		int sl=0,sc=0,sd=0,sdi=0;
		boolean testFin = true;
		for(int i=0;i<3;i++)
		{
			sl = 0; sc = 0;
			for(int j=0;j<3;j++)
			{
				sl+= M[i][j];
				sc+= M[j][i];
				if(i==j)
					sd+=M[i][j];
				if(i+j==2)
					sdi += M[i][j];
				if(M[i][j]==0)
					testFin=false ;
			}
			if(sl==3 || sc==3)
				return 1 ;
			if(sl==-3 || sc==-3)
				return 2 ;
		}
		if(sd==3 || sdi==3)
			return 1 ;
		if(sd==-3 || sdi==-3)
			return 2;
		
		if(testFin)
			return 0;
		else
			return -1 ;
	}

}
