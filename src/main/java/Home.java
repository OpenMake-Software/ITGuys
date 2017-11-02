

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/* new for XML parsing */
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;
/* end new */

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection m_conn;
	
	private int version = 2;

    /**
     * Default constructor. 
     */
    public Home() {
        // TODO Auto-generated constructor stub
    }
    
    public void ShowPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		// Class.forName("oracle.jdbc.OracleDriver");
    		response.getWriter().append("About to call Class.forName\n");
    		Class.forName("org.postgresql.Driver");
    		response.getWriter().append("Done that\n");
    		List<Person> people = new ArrayList<Person>();
    		String basedir = System.getProperty("catalina.base");
    		response.getWriter().append("basedir="+basedir+"\n");
    		String configdir = basedir + "/webapps";
    		XPath xpath = XPathFactory.newInstance().newXPath();
    		InputSource inputSource = new InputSource(configdir+"/config.xml");
    		String env = xpath.evaluate("/ITGuys/environment", inputSource);
    		String jdbc = xpath.evaluate("/ITGuys/jdbc",inputSource);
		String buildno = xpath.evaluate("/ITGuys/buildno",inputSource);
    		response.getWriter().append("env="+env+" jdbc="+jdbc+" buildno="+buildno+"\n");
    		m_conn = DriverManager.getConnection("jdbc:"+jdbc,"postgres","postgres");
    		Statement st = m_conn.createStatement();
    		ResultSet rs = null;
    		switch(version) {
    		case 1:
    			rs = st.executeQuery("SELECT first_name,surname FROM itguys ORDER BY surname");
    			break;
    		case 2:
    			rs = st.executeQuery("SELECT first_name,surname,dob FROM itguys ORDER BY surname");
    			break;
    		case 3:
    			rs = st.executeQuery("SELECT first_name,surname,dob,achievement FROM itguys ORDER BY surname");
    			break;
    		}

    	    while (rs.next()) {
    	    	Person p = null;
    	    	switch(version) {
    	    	case 1:
    	    		 p = new Person(rs.getString(1),rs.getString(2));
    	    		 break;
    	    	case 2:
    	    		p = new Person(rs.getString(1),rs.getString(2),rs.getDate(3));
    	    		break;
    	    	case 3:
    	    		p = new Person(rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4));
    	    		break;
    	    	}
    	    	people.add(p);
    	    }
    	    rs.close();
    	    rs = st.executeQuery("SELECT version FROM demo_version");
    	    rs.next();
    	    String szVersion = rs.getString(1);
    	    rs.close();
    	    st.close();
    	    request.setAttribute("people",people);
    	    request.setAttribute("env",env);
    	    request.setAttribute("version",version);
	    request.setAttribute("buildno",buildno);
    	    request.getRequestDispatcher("/WEB-INF/Home"+version+".jsp").forward(request, response);
    	} catch(SQLException e) {
			response.getWriter().append("SQLException+<BR>"+e.getMessage());
    		e.printStackTrace();
    	} 
    	catch (ClassNotFoundException e) {
    		response.getWriter().append("ClassNotFoundException");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			response.getWriter().append("IO Exception: "+e.getMessage());
			e.printStackTrace();
		}
    	catch(XPathExpressionException e) {
		response.getWriter().append("XPath Expression Exception: "+e.getMessage());
    		e.printStackTrace();
		}
	catch(Exception e) {
		response.getWriter().append("Generic Exception caught: "+e.getMessage());
	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Start from: ").append(request.getContextPath());
		response.getWriter().append("\n");
		ShowPage(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}