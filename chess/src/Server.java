import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

public class Server {
	
	private static CommThread[] playerclient = new CommThread[2];
	private static EmoThread[] playeremo = new EmoThread[2];
	private static SimpleDateFormat sdfDate = new SimpleDateFormat("yyy-MM-dd HH:mm:SSS");

	public static String getLog (String msg) {
		return "[" + sdfDate.format(new Date ()) + "] Server thread: " + msg;
	}

	public static void main(String[] args) {
		
		boolean white = true;
		
		ServerSocket ss = null;
		int players = 0;
		
		try {
			ss = new ServerSocket(5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("server is ready");
		
		while (true) {
			try {
				Socket soc = ss.accept();
				System.out.println(Server.getLog("new player connection arrived"));
				CommThread t = new CommThread(soc, players+1);
				playerclient[players++] = t; 

				if (players == 2) { // The server has two player clients, start game.
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		playerclient[0].start();
		playerclient[1].start();
		System.out.println(Server.getLog("2 clients are here"));
		
		players = 0;
		while (true) {
			try {
				Socket soc = ss.accept();
				System.out.println(Server.getLog("new emo connection arrived"));
				EmoThread t = new EmoThread(soc, players+1);
				playeremo[players++] = t;

				if (players == 2) { // The server has two player clients, start game.
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		playeremo[0].start();
		playeremo[1].start();
		
		ServerEmoManager sem = new ServerEmoManager(playeremo);
		sem.start();
		
		while (true) {
			try {
				if (white) 	receiveAndSend(playerclient[0], playerclient[1]);
				else		receiveAndSend(playerclient[1], playerclient[0]);
				System.out.println(Server.getLog("Received from client and sent to another"));
				white ^= true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//ss.close();
	}
	
    public static void receiveAndSend(CommThread comm1, CommThread comm2) throws IOException {
    	
    	Socket socket1 = comm1.soc;
        int maxBufferSize1 = 1024;
        byte[] recvBuffer1 = new byte[maxBufferSize1];
        InputStream is1 = socket1.getInputStream();
        int nReadSize1 = is1.read(recvBuffer1);    

        if (nReadSize1 > 0) {
            String i = toObject(recvBuffer1, String.class);
            System.out.println(i);

			comm2.sendToClient(i);
        }
    }
	
    public static void receive(CommThread comm1, CommThread comm2) throws IOException {
    	
    	Socket socket1 = comm1.soc;
        int maxBufferSize1 = 1024;
        byte[] recvBuffer1 = new byte[maxBufferSize1];
        InputStream is1 = socket1.getInputStream();
        int nReadSize1 = is1.read(recvBuffer1);
    	Socket socket2 = comm2.soc;
    	
        int maxBufferSize2 = 1024;
        byte[] recvBuffer2 = new byte[maxBufferSize2];
        InputStream is2 = socket2.getInputStream();
        int nReadSize2 = is2.read(recvBuffer2);

        if (nReadSize1 > 0) {
            String i = toObject(recvBuffer1, String.class);
            System.out.println(i);

            //if (i.length() < 1) return;
			comm1.sendToClient("I said... " + i);
			comm2.sendToClient("Opponent said... " + i);
        }

        if (nReadSize2 > 0) {
            String i = toObject(recvBuffer2, String.class);
            System.out.println(i);
            
            //if (i.length() < 1) return;
            comm2.sendToClient("I said... " + i);
			comm1.sendToClient("Opponent said... " + i);
        }
    }
    
    public static <T> T toObject (byte[] bytes, Class<T> type)
    {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
        }
        catch (IOException ex) {
            //TODO: Handle the exception
        }
        catch (ClassNotFoundException ex) {
            //TODO: Handle the exception
        }
        return type.cast(obj);
    }
}

class ServerEmoManager extends Thread {
	
	EmoThread[] playeremo;
	
	ServerEmoManager(EmoThread[] emo) {
		this.playeremo = emo;
	}
	
	public void run() {
		
		while (true) {
			try {
				receiveAndSend(playeremo[0], playeremo[1]);
				receiveAndSend(playeremo[1], playeremo[0]);
				System.out.println(Server.getLog("Emo Received from client and sent to another"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
    public static void receiveAndSend(EmoThread emo1, EmoThread emo2) throws IOException {
    	
    	Socket socket1 = emo1.soc;
        int maxBufferSize1 = 1024;
        byte[] recvBuffer1 = new byte[maxBufferSize1];
        InputStream is1 = socket1.getInputStream();
        int nReadSize1 = is1.read(recvBuffer1);    

        if (nReadSize1 > 0) {
            String i = toObject(recvBuffer1, String.class);
			emo2.sendToClient(i);
        }
    }
    
    public static <T> T toObject (byte[] bytes, Class<T> type)
    {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
        }
        catch (IOException ex) {
            //TODO: Handle the exception
        }
        catch (ClassNotFoundException ex) {
            //TODO: Handle the exception
        }
        return type.cast(obj);
    }
}