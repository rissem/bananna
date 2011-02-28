package com.moozuck;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class AddGameServlet extends HttpServlet{
	private static final long serialVersionUID = -3243388140814608188L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		if (req.getParameter("name") == null || req.getParameter("password") == null){
			System.err.println("name and password parameters are required");
			return;
		}
		
		DB db = getDb();
		DBCollection coll = db.getCollection("games");
		BasicDBObject game = new BasicDBObject();
		game.put("name", req.getParameter("name"));
		game.put("password", req.getParameter("password"));
		coll.insert(game);
		System.out.println("added game");		
	}
	
	private DB getDb(){
		try {
			Mongo m = new Mongo();
			return m.getDB("banana");
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
