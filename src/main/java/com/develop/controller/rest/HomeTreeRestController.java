package com.develop.controller.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.model.Node;

import net.sf.json.JSONArray;

@RestController
@RequestMapping(value = "/hometree")
public class HomeTreeRestController {
	
	@RequestMapping("/querytreelist")
	public String exportExcel(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String str =  queryTree();
		 
		
		return str;

	}

	/**
	 *  
	 * 
	 * @return
	 */
	private String queryTree() {
		List<Node> lists = getNodesFromDataBase();
		Node tree = new Node();
		Node n = tree.createTree(lists);

		return JSONArray.fromObject(n.getNodes()).toString();
	}

	 public List<Node> getNodesFromDataBase() {
         List<Node> nodes = new ArrayList<Node>();
         
         nodes.add(new Node("system","0","Title1","glyphicon glyphicon-home", null));
         
         nodes.add(new Node("manager","root","Title2","glyphicon glyphicon-home", "http://www.baidu.com"));
       
         nodes.add(new Node("A01","system","user1","glyphicon glyphicon-stop", "http://www.baidu.com"));
         nodes.add(new Node("B01","A01","user2","glyphicon glyphicon-stop", "http://www.baidu.com"));
      
         nodes.add(new Node("A02","system","admin1","glyphicon glyphicon-stop", "http://www.baidu.com"));
         nodes.add(new Node("B02","A02","admin2","glyphicon glyphicon-stop", "http://www.baidu.com"));
         nodes.add(new Node("B03","A02","admin3","glyphicon glyphicon-stop", "http://www.baidu.com"));
       
         nodes.add(new Node("A03","system","root1","glyphicon glyphicon-stop", "http://www.baidu.com"));

         nodes.add(new Node("C02","manager","root2","glyphicon glyphicon-stop", "http://www.baidu.com"));
         nodes.add(new Node("C03","manager","root3","glyphicon glyphicon-stop", "http://www.baidu.com"));
              
         return nodes;
     }

}
