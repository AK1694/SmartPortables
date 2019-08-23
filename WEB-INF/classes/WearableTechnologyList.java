import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WearableTechnologyList")

public class WearableTechnologyList extends HttpServlet {

	

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		
		HashMap<String,WearableTechnology> allwearables = new HashMap<String,WearableTechnology> ();


		
		try{
		     allwearables = MySqlDataStoreUtilities.getWearables();
		}
		catch(Exception e)
		{
			
		}
		String name = null;
		String CategoryName = request.getParameter("maker");


		

		HashMap<String, WearableTechnology> hm = new HashMap<String, WearableTechnology>();
		if(CategoryName==null){
			hm.putAll(allwearables);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("fitnesswatches"))
		   {
			 for(Map.Entry<String,WearableTechnology> entry : allwearables.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("FitnessWatches"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "FitnessWatches";
		   }
		   else if(CategoryName.equals("smartwatches"))
		    {
			for(Map.Entry<String,WearableTechnology> entry : allwearables.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("SmartWatches"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "SmartWatches";
			}
			else if(CategoryName.equals("headphones"))
			{
				for(Map.Entry<String,WearableTechnology> entry : allwearables.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Headphones"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Headphones";
			}

			else if(CategoryName.equals("virtualreality"))
			{
				for(Map.Entry<String,WearableTechnology> entry : allwearables.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("VirtualReality"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
					 name = "VirtualReality";
			}
			else if(CategoryName.equals("pettracker"))
			{
				for(Map.Entry<String,WearableTechnology> entry : allwearables.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("PetTracker"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
					 name = "PetTracker";
			}
		}


	

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Wearables</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, WearableTechnology> entry : hm.entrySet())
		{
			WearableTechnology wearable = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+wearable.getName()+"</h3>");
			pw.print("<strong>$"+wearable.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/home/"+wearable.getImage()+"' alt='' /></li>");

			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearabletechnology'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearabletechnology'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+wearable.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearabletechnology'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}
		pw.print("</table></div></div></div>");

		utility.printHtml("Footer.html");

	}
}
