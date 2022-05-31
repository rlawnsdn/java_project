import java.awt.Color;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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
					bgui.bframe.setSystemMsg("Your Turn!", Color.green);
					bgui.bframe.optionbuttons1.setVisible(true);
					bgui.bframe.optionbuttons2.setVisible(false);
					this.myturn = true;
					em.start();
				}
				else if (str.equals("GameStart 2")) {
					gameStarted = true;
					cboard.playsWhite = false;
					cboard.setClickable(false);
					bgui.startBoardGUI(cboard, false);
					bgui.bframe.setSystemMsg("Opponent's Turn,\nPlease Wait...", Color.white);
					bgui.bframe.optionbuttons1.setVisible(false);
					bgui.bframe.optionbuttons2.setVisible(false);
					this.myturn = false;
					em.start();
				}
			}
			
			while (true) {
				
				turncheck = cboard.turn;
				String str = "";
				System.out.println("turn " + turncheck + ", myturn:" + myturn);
				
				if (!myturn) {
					str = dis.readUTF();
					System.out.println("Received... [" + str + "]");	
				}
				
				if (str.startsWith("Moves:")) {
					char[] c = str.substring(6).toCharArray();
					cboard.movepiece(c[0]-48, c[1]-48, c[2]-48, c[3]-48, c[4]);
					cboard.updateMovableForAllPieces();
					cboard.turn++;
					cboard.piecemoveblock = false;
					
					bgui.updateBoardGUI(cboard.sq);
					bgui.bframe.setSystemMsg("Your Turn!", Color.green);
					bgui.bframe.optionbuttons1.setVisible(true);
					bgui.bframe.optionbuttons2.setVisible(false);
					myturn ^= true;
					continue;
				}
				else if (str.startsWith("Spcmd:")) {
					char c = str.charAt(6);
					switch (c-48) {
					case 0: bgui.bframe.setSystemMsg("A draw has been suggested.\nWill you accept it?", Color.yellow); break;
					case 1:	bgui.bframe.setSystemMsg("The opponent has surrendered.\nYOU WIN!", Color.cyan); break;
					case 2:	bgui.bframe.setSystemMsg("The opponent accepted a draw.\nGame Ends.", Color.green); break;
					case 3: bgui.bframe.setSystemMsg("Suggestion rejected.\nYour Turn!", Color.green); break;
					}
					bgui.bframe.optionbuttons1.setVisible(c-48 == 3);
					bgui.bframe.optionbuttons2.setVisible(c-48 == 0);
					cboard.turn++;
					cboard.piecemoveblock = (c-48 < 3);
					
					if (c-48 == 1 || c-48 == 2) break;
					
					myturn ^= true;
					continue;
				}
				
				while (cboard.turn == turncheck) {
					PlayerClient.sleep(50);
				}
			
				cboard.setClickable(false);
				bgui.bframe.optionbuttons1.setVisible(false);
				bgui.bframe.optionbuttons2.setVisible(false);
				
				if (cboard.commandType == -1) {
					send(soc, "Moves:" + cboard.x1 + cboard.y1 + cboard.x2 + cboard.y2 + cboard.preferredPromotion);	
					System.out.println("sended... [Moves:" + cboard.x1 + cboard.y1 + cboard.x2 + cboard.y2 + cboard.preferredPromotion + "]");
					bgui.bframe.setSystemMsg("Opponent's Turn,\nPlease Wait...", Color.white);
				}
				else {
					send(soc, "Spcmd:" + cboard.commandType);
					System.out.println("sended... [Spcmd:" + cboard.commandType + "]");
					
					switch (cboard.commandType) {
					case 0: bgui.bframe.setSystemMsg("A draw has been suggested.\nWait for the opponent.", Color.yellow); break;
					case 1:	bgui.bframe.setSystemMsg("You have surrendered.\nYOU LOSE!", Color.red); break;
					case 2:	bgui.bframe.setSystemMsg("You accepted a draw.\nGame Ends.", Color.green); break;
					case 3: bgui.bframe.setSystemMsg("Suggestion rejected.\nOpponent's Turn.", Color.white); break;
					}
				}
				bgui.updateBoardGUI(cboard.sq);
				
				if (cboard.commandType == 1 || cboard.commandType == 2) break;
				myturn ^= true;
			}

			dis.close();
			soc.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bgui.bframe.setSystemMsg("The server has shut down.\nGame Ends.", Color.magenta);
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
				EmoManager.sleep(50);
				String str = dis.readUTF();
				bgui.updateEmo(false, str.toCharArray()[0] - 48);
				send(soc, Integer.toString(bgui.bframe.player1.emoidx));
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bgui.bframe.setSystemMsg("The server has shut down.\nGame Ends.", Color.magenta);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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