import java.io.*;
import java.net.Socket;
import java.util.Scanner;

class PlayerClient extends Thread {
	
	boolean myturn;
	String username;
	InputManager im;
	
	boolean gameStarted;
	ChessBoard cboard;
	BoardGUI bgui;
	
	int turncheck = 0;
	
	PlayerClient() {
		this.im = new InputManager();
		
		this.cboard = new ChessBoard();
		gameStarted = false;
	}
	
	public void run() {
		
		Scanner scn = new Scanner(System.in);
		try {
			Socket soc = new Socket("localhost", 5000);
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			while (!gameStarted) {
				String str = dis.readUTF();
				System.out.println(str);
				if (str.equals("GameStart 1")) {
					gameStarted = true;
					cboard.setClickable(true);
					bgui = new BoardGUI(cboard, true);
					this.myturn = true;
					//im.setTurn(true);
					//im.start();
				}
				else if (str.equals("GameStart 2")) {
					gameStarted = true;
					cboard.setClickable(false);
					bgui = new BoardGUI(cboard, false);
					this.myturn = false;
					//im.setTurn(false);
					//im.start();
				}
			}
			
			while (gameStarted) {
				
				turncheck = cboard.turn;
				String str = "";
				System.out.println("turn " + turncheck + ", myturn:" + myturn);
				
				if (!myturn) {
					str = dis.readUTF();
					System.out.println(str);	
				}
				
				if (str.equals("GameEnds")) {
					gameStarted = false;
					break;
				}
				else if (str.startsWith("Moves:")) {
					char[] c = str.substring(6).toCharArray();
					cboard.movepiece(c[0]-48, c[1]-48, c[2]-48, c[3]-48);
					cboard.turn++;
					cboard.setClickable(true);
					bgui.updateBoardGUI(cboard.sq);
					myturn ^= true;
					continue;
				}
				
				while (cboard.turn == turncheck) {
					
					PlayerClient.sleep(50);
				}
				
				send(soc, "Moves:" + cboard.x1 + cboard.y1 + cboard.x2 + cboard.y2);
				System.out.println("sended... Moves:" + cboard.x1 + cboard.y1 + cboard.x2 + cboard.y2);
				//cboard.movepiece(cboard.x1, cboard.y1, cboard.x2, cboard.y2);
				bgui.updateBoardGUI(cboard.sq);
				myturn ^= true;
			}

			//dis.close();
			//soc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public static void send(Socket socket, String s) throws IOException {

        byte[] data = toByteArray(s);
        OutputStream os = socket.getOutputStream();
        os.write(data);
        os.flush();
    }

    public static byte[] toByteArray(Object obj)
    {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            oos.close();
            bos.close();
            bytes = bos.toByteArray();
        }
        catch (IOException ex) {
            //TODO: Handle the exception
        }
        return bytes;
    }
}

class InputManager extends Thread {
	
	boolean myturn;
	int timer;
	
	InputManager() {
		this.myturn = false;
		this.timer = 0;
	}
	
	public void run() {
		
		while (myturn && timer > 0) {
			try {
				InputManager.sleep(1000);
				setTimerDelta(-1);
				System.out.println("Time left in seconds..." + timer);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	void setTurn(boolean t) {
		this.myturn = true;
		if (t) this.timer = 30;
	}
	
	void setTimerDelta(int delta) {
		this.timer += delta;
	}
}

public class ClientManager {
	
	public static void main(String[] args) {
		
		PlayerClient pc = new PlayerClient();
		
		pc.start(); 
	}
}