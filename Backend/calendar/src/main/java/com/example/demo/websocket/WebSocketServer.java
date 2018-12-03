package com.example.demo.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Sebastian Kazun
 *
 */
@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {
	
	// Store all socket session and their corresponding username.
    private static Map<Session, String> sessionUsernameMap = new HashMap<>();
    private static Map<String, Session> usernameSessionMap = new HashMap<>();
    
    private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    
    /**
     * Creates a new session for users to join upon startup
     * 
     * @param session 
     * the created session for users to join
     * @param username 
     * the name of the user joining the chat
     * @throws IOException 
     * .
     */
    @OnOpen
    public void onOpen(
    	      Session session, 
    	      @PathParam("username") String username) throws IOException 
    {
        logger.info("Entered into Open");
        
        sessionUsernameMap.put(session, username);
        usernameSessionMap.put(username, session);
        
        String message="User:" + username + " has Joined the Chat";
        	broadcast(message);
		
    }
    
    /**
     * sends message to all other connected chats or to a particular user
     * @param session
     * the created session for users to join
     * @param message
     * the message that will be sent to other users
     * @throws IOException
     * .
     */
    @OnMessage
    public void onMessage(Session session, String message) throws IOException 
    {
        // Handle new messages
    	logger.info("Entered into Message: Got Message:"+message);
    	String username = sessionUsernameMap.get(session);
    	
    	if (message.startsWith("@")) // Direct message to a user using the format "@username <message>"
    	{
    		String destUsername = message.split(" ")[0].substring(1);
    		sendMessageToPArticularUser(destUsername, "[DM] " + username + ": " + message);
    		sendMessageToPArticularUser(username, "[DM] " + username + ": " + message);
    	}
    	else // Message to whole chat
    	{
	    	broadcast(username + ": " + message);
    	}
    }
    
    /**
     * Handles closing the connection of the chat
     * @param session
     * the created session for users to join
     * @throws IOException
     * .
     */
    @OnClose
    public void onClose(Session session) throws IOException
    {
    	logger.info("Entered into Close");
    	
    	String username = sessionUsernameMap.get(session);
    	sessionUsernameMap.remove(session);
    	usernameSessionMap.remove(username);
        
    	String message= username + " disconnected";
        broadcast(message);
    }
    
    /**
     * An error handler that alerts when an error has occured
     * @param session
     * the created session for users to join
     * @param throwable
     * .
     */
    @OnError
    public void onError(Session session, Throwable throwable) 
    {
        // Do error handling here
    	logger.info("Entered into Error");
    }
    
    /**
     * handles sending of a message to a particular user
     * @param username
     * the name of the user to send the message to
     * @param message
     * the message that will be sent to this particular user
     */
	private void sendMessageToPArticularUser(String username, String message) 
    {	
    	try {
    		usernameSessionMap.get(username).getBasicRemote().sendText(message);
        } catch (IOException e) {
        	logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
        }
    }
    
	/**
	 * Sends the message to ALL users that are connected to the chat session
	 * @param message
	 * the message that will be sent to all the other users
	 * @throws IOException
	 * .
	 */
    private static void broadcast(String message) 
    	      throws IOException 
    {	  
    	sessionUsernameMap.forEach((session, username) -> {
    		synchronized (session) {
	            try {
	                session.getBasicRemote().sendText(message);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
}
