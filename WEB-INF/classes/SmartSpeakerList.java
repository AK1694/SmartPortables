import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SmartSpeakerList")

public class SmartSpeakerList extends HttpServlet {

	/* Games Page Displays all the Games and their Information in Game Speed */

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Games type whether it is electronicArts or activision or takeTwoInteractive */
		HashMap<String,SmartSpeaker> allsmartspeakers = new HashMap<String,SmartSpeaker> ();


		/* Checks the Tablets type whether it is microsft or sony or nintendo */
		try{
		     allsmartspeakers = MySqlDataStoreUtilities.getSmartSpeakers();
		}
		catch(Exception e)
		{
			
		}

	/* Checks the Laptops type whether it is microsft or apple or samsung */

		String name = null;
		String CategoryName = request.getParameter("maker");
		HashMap<String, SmartSpeaker> hm = new HashMap<String, SmartSpeaker>();

		if (CategoryName == null)
		{
			hm.putAll(allsmartspeakers);
			name = "";
		}
		else
		{
			if(CategoryName.equals("sonos"))
			{
				for(Map.Entry<String,SmartSpeaker> entry : allsmartspeakers.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Sonos"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name ="Sonos";
			}
			else if (CategoryName.equals("amazon"))
			{
				for(Map.Entry<String,SmartSpeaker> entry : allsmartspeakers.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Amazon"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
				name = "Amazon";
			}
			else if (CategoryName.equals("google"))
			{
				for(Map.Entry<String,SmartSpeaker> entry : allsmartspeakers.entrySet())
				{
				  if(entry.getValue().getRetailer().equals("Google"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				name = "Google";
			}

	    }

		/* Header, Left Navigation Bar are Printed.

		All the tablets and tablet information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>" + name + " SmartSpeakers</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1;
		int size = hm.size();
		for (Map.Entry<String, SmartSpeaker> entry : hm.entrySet()) {
			SmartSpeaker SmartSpeaker = entry.getValue();
			if (i % 3 == 1)
				pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>" + SmartSpeaker.getName() + "</h3>");
			pw.print("<strong>" + SmartSpeaker.getPrice() + "$</strong><ul>");
			pw.print("<li id='item'><img src='images/smartspeakers/"
					+ SmartSpeaker.getImage() + "' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='smartspeakers'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='smartspeakers'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+SmartSpeaker.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='smartspeakers'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if (i % 3 == 0 || i == size)
				pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
