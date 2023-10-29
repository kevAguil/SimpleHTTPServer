import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;


public class SimpleHTTPServer {

	public static void main(String[] args) throws Exception{
		
		final ServerSocket server = new ServerSocket(8080);
		System.out.println("Listening for connection on port 8080 ....");
		while(true) {
			
			try(Socket clientSocket = server.accept()){
				handleRequest(clientSocket);						
				
			}
						
		}	

	}
		
	
    public static void handleRequest(Socket clientSocket) throws IOException {
    	
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String requestLine = in.readLine(); // Read the first line of the request
        if (requestLine != null) {
        	
            String[] requestParts = requestLine.split(" ");
            if (requestParts.length >= 2) {
            	
                String method = requestParts[0];
                String path = requestParts[1];

                if (method.equals("GET")) {
                	
                    if (path.equals("/")) {
                    	
                        // Send a simple "Hello, World!" response
                        out.println("HTTP/1.1 200 OK");
                        out.println("Content-Type: text/html; charset=UTF-8");
                        out.println("");
                        out.println("<html><body><h1>This is my first simple http server!</h1></body></html>");
                    } 
                    else {
                    	
                        // Handle other paths here
                        out.println("HTTP/1.1 404 Not Found");
                        
                    }
                    
                }
                
            }
            
        }

        out.close();
        in.close();
        clientSocket.close();
        
    }

}
