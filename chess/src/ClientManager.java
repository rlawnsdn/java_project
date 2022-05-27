import java.io.*;
import java.net.Socket;

class PlayerClient extends Thread {
	
	boolean myturn;
	String username;
	EmoManager em;
	
	boolean gameStarted;
	ChessBoard cboard;
	BoardGUI bgui;
	
	int turncheck = 0;
	
	PlayerClient() {		
		gameStarted = false;
		this.cboard = new ChessBoard();
		this.bgui = new BoardGUI();
		
		this.em = new EmoManager(bgui);
	}
	
	public void run() {

		try {
			Socket soc = new Socket("localhost", 5000);
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			while (!gameStarted) {
				String str = dis.readUTF();
				System.out.println(str);
				
				if (str.equals("GameStart 1")) {
					gameStarted = true;
					cboard.playsWhite = true;
					cboard.setClickable(true);
					bgui.startBoardGUI(cboard, true);
					this.myturn = true;
					em.start();
				}
				else if (str.equals("GameStart 2")) {
					gameStarted = true;
					cboard.playsWhite = false;
					cboard.setClickable(false);
					bgui.startBoardGUI(cboard, false);
					this.myturn = false;
					em.start();
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
					cboard.movepiece(c[0]-48, c[1]-48, c[2]-48, c[3]-48, c[4]);
					cboard.updateMovableForAllPieces();
					cboard.turn++;
					cboard.setClickable(true);
					bgui.updateBoardGUI(cboard.sq);
					myturn ^= true;
					continue;
				}
				
				while (cboard.turn == turncheck) {
					
					PlayerClient.sleep(50);
				}
				
				send(soc, "Moves:" + cboard.x1 + cboard.y1 + cboard.x2 + cboard.y2 + cboard.preferredPromotion);
				System.out.println("sended... Moves:" + cboard.x1 + cboard.y1 + cboard.x2 + cboard.y2 + cboard.preferredPromotion);
				cboard.setClickable(false);
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

class EmoManager extends Thread {

	BoardGUI bgui;
	
	EmoManager(BoardGUI bgui) {
		this.bgui = bgui;
	}
	
	public void run() {
		
		try {
			Socket soc = new Socket("localhost", 5000);
			DataInputStream dis = new DataInputStream(soc.getInputStream());
			while (true) {
				try {
					EmoManager.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String str = dis.readUTF();
				bgui.updateEmo(false, str.toCharArray()[0] - 48);
				send(soc, Integer.toString(bgui.bframe.player1.emoidx));
			}
		} catch (IOException e) {
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

public class ClientManager {
	
	public static void main(String[] args) {
		
		PlayerClient pc = new PlayerClient();
		
		pc.start(); 
	}
}