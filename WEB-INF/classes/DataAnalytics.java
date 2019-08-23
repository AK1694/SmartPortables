import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.*;
import javax.servlet.http.HttpSession;
@WebServlet("/DataAnalytics")

public class DataAnalytics extends HttpServlet {
	static DBCollection myReviews;
	/* Trending Page Displays all the wearables and their Information */

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
				
		
		//check if the user is logged in
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View Reviews");
			response.sendRedirect("Login");
			return;
		}
		
						
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Data Analytics on Review</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table id='bestseller'>");
		pw.print("<form method='post' action='FindReviews'>");
	
     		pw.print("<table id='bestseller'>");
			pw.print("<tr>");
			pw.print("<td> <input type='checkbox' name='queryCheckBox' value='productName'> Select </td>");
                                pw.print("<td> Product Name: </td>");
                                pw.print("<td>");
                                       pw.print("<select name='productName'>");
				       pw.print("<option value='ALL_PRODUCTS'>All Products</option>");
                                       pw.print("<option value='IP67 Waterproof Pedometer Watch'>IP67 Waterproof Pedometer Watch</option>");
                                       pw.print("<option value='wesoo Fitness Tracker K1'>wesoo Fitness Tracker K1</option>");
                                       pw.print("<option value='IP68 Waterproof Pedometer Watch'>IP68 Waterproof Pedometer Watch</option>");
                                       pw.print(" <option value='IP70 Heart Rate Watch'>IP70 Heart Rate Watch</option>");
                                       pw.print("<option value='Coffea Fitness Tracker H7-HR'>Coffea Fitness Tracker H7-HR</option>");
									   
							           pw.print("<option value='Letsfit Fitness Tracker'>Letsfit Fitness Tracker</option>");
										pw.print("<option value='KARSEEN Fitness Tracker'>KARSEEN Fitness Tracker</option>");
										pw.print("<option value='IFitness Tracker for Kids Activity Trackers'>IFitness Tracker for Kids Activity Trackers</option>");
										pw.print("<option value='Kids Fitness/Activity Tracker'>Kids Fitness/Activity Tracker</option>");
										pw.print("<option value='Garmin Digi Camo'>Garmin Digi Camo</option>");
										pw.print("<option value='Garmin fenix 5 Plus'>Garmin fenix 5 Plus</option>");
										pw.print("<option value='Activity Tracker with IP68 Waterproof'>Activity Tracker with IP68 Waterproof</option>");
										pw.print("<option value='Garmin fenix 5 s'>Garmin fenix 5 s</option>");
										pw.print("<option value='Fitness Tracker Swimming Watch SpO2'>Fitness Tracker Swimming Watch SpO2</option>");
										pw.print("<option value='Smart Watch for Women, GOKOO S2'>Smart Watch for Women, GOKOO S2</option>");
										pw.print("<option value='Garmin Hybrid wearable for Men'>Garmin Hybrid wearable for Men</option>");
										pw.print("<option value='Ticwatch S wearable-OLED Display'>Ticwatch S wearable-OLED Display</option>");
										pw.print("<option value='Ticwatch E wearable-Shadow'>Ticwatch E wearable-Shadow</option>");
										pw.print("<option value='Ticwatch E Lemon Smart Watch'>Ticwatch E Lemon Smart Watch</option>");
										pw.print("<option value='MBHB Gear S2 wearable - Dark Gray'>MBHB Gear S2 wearable - Dark Gray</option>");
										pw.print("<option value='Edifier H650'>Edifier H650</option>");
										pw.print("<option value='Edifier H840'>Edifier H840</option>");
										pw.print("<option value='AILIHEN I60 On Ear Headphones'>AILIHEN I60 On Ear Headphones</option>");
										pw.print("<option value='AILIHEN MS300 Headphones'>AILIHEN MS300 Headphones</option>");
										pw.print("<option value='AILIHEN C8 Headphones'>AILIHEN C8 Headphones</option>");
										pw.print("<option value='Elecder i39 Headphones'>Elecder i39 Headphones</option>");
										pw.print("<option value='Elecder i49 Headphones'>Elecder i49 Headphones</option>");
										pw.print("<option value='Elecder i35 Headphones'>Elecder i35 Headphones</option>");
										pw.print("<option value='Edifier H960'>Edifier H960</option>");
										pw.print("<option value='Edifier C540'>Edifier C540</option>");
										pw.print("<option value='DESTEK V4'>DESTEK V4</option>");
										pw.print("<option value='FLASNAKE 3D'>FLASNAKE 3D</option>");
										pw.print("<option value='htc k11 VR_Controller'>htc k11 VR_Controller</option>");
										pw.print("<option value='Pansonite Vr Headset'>Pansonite Vr Headset</option>");
										pw.print("<option value='Pansonite Vr 2.0 Headset'>Pansonite Vr 2.0 Headset</option>");
										pw.print("<option value='DESTEK v6'>DESTEK v6</option>");
										pw.print("<option value='DESTEK v4'>DESTEK v4</option>");
										pw.print("<option value='htc k14 VR_Controller'>htc k14 VR_Controller</option>");
										pw.print("<option value='FLASNAKE 4K vr controller'>FLASNAKE 4K vr controller</option>");
										pw.print("<option value='FLASNAKE 5plus vr controller'>FLASNAKE 5plus vr controller</option>");
										pw.print("<option value='Xingqijia GPS 2G'>Xingqijia GPS 2G</option>");
										pw.print("<option value='Whistle 3'>Whistle 3</option>");
										pw.print("<option value='GBD Bluetooth Locator Pet Tracker'>GBD Bluetooth Locator Pet Tracker</option>");
										pw.print("<option value='GBD 001 Locator Pet Tracker'>GBD 001 Locator Pet Tracker</option>");
										pw.print("<option value='GBD 002 Locator Pet Tracker'>GBD 002 Locator Pet Tracker</option>");
										pw.print("<option value='Whistle 4'>Whistle 4</option>");
										pw.print("<option value='Whistle 5'>Whistle 5</option>");
										pw.print("<option value='Xingqijia GPS 3G'>Xingqijia GPS 3G</option>");
										pw.print("<option value='Xingqijia GPS 4G'>Xingqijia GPS 4G</option>");
										pw.print("<option value='Xingqijia GPS 5G'>Xingqijia GPS 5G</option>");
										pw.print("<option value='Samsung Galaxy S8'>Samsung Galaxy S8</option>");
										pw.print("<option value='iPhone X'>iPhone X</option>");
										pw.print("<option value='iPhone 6s'>iPhone 6s</option>");
										pw.print("<option value='iPhone 6'>iPhone 6</option>");
										pw.print("<option value='iPhone 7'>iPhone 7</option>");
										pw.print("<option value='iPhone 7Plus'>iPhone 7Plus</option>");
										pw.print("<option value='Samsung Galaxy J7'>Samsung Galaxy J7</option>");
										pw.print("<option value='Samsung Galaxy J5'>Samsung Galaxy J5</option>");
										pw.print("<option value='Sony Xperia XZ2'>Sony Xperia XZ2</option>");
										pw.print("<option value='Sony Xperia XA'>Sony Xperia XA</option>");
										pw.print("<option value='ASUS Chromebook C202SA-YS02'>ASUS Chromebook C202SA-YS02</option>");
										pw.print("<option value='MSI GV62 8RD-200'>MSI GV62 8RD-200</option>");
										pw.print("<option value='MSI GV62 8RDX-500'>MSI GV62 8RDX-500</option>");
										pw.print("<option value='MSI GL62M 7RDX-1645'>MSI GL62M 7RDX-1645</option>");
										pw.print("<option value='Apple 15 MacBook Pro'>Apple 15 MacBook Pro</option>");
										pw.print("<option value='Apple MacBook Air MD711LL'>Apple MacBook Air MD711LL</option>");
										pw.print("<option value='Apple MNYH2LL/A 12 MacBook'>Apple MNYH2LL/A 12 MacBook</option>");
										pw.print("<option value='Sony Vaio Vpcea24fm'>Sony Vaio Vpcea24fm</option>");
										pw.print("<option value='Sony 11.6 Laptop 8GB 256GB'>Sony 11.6 Laptop 8GB 256GB</option>");
										pw.print("<option value='Sony VAIO Pro SVP1321HGXBI'>Sony VAIO Pro SVP1321HGXBI</option>");
										pw.print("<option value='Sonos One'>Sonos One</option>");
										pw.print("<option value='Echo Dot'>Echo Dot</option>");
										pw.print("<option value='Certified Refurbished All-new Echo'>Certified Refurbished All-new Echo</option>");
										pw.print("<option value='Google WiFi system Home'>Google WiFi system Home</option>");
										pw.print("<option value='Amazon Tap - Alexa'>Amazon Tap - Alexa</option>");
										pw.print("<option value='HIDEit Google Home Mount'>HIDEit Google Home Mount</option>");
										pw.print("<option value='Sonos Revolve  360 Speaker'>Sonos Revolve  360 Speaker</option>");
										pw.print("<option value='Sonos PLAY:1 Compact Wireless Smart Speaker'>Sonos PLAY:1 Compact Wireless Smart Speaker</option>");
										pw.print("<option value='Google Touch Wireless Bluetooth V4.0 Portable Speaker'>Google Touch Wireless Bluetooth V4.0 Portable Speaker</option>");
										pw.print("<option value='Sonos Night Light Bluetooth Speaker'>Sonos Night Light Bluetooth Speaker</option>");
								















                                pw.print("</td>");
			pw.print("<tr>");
			     pw.print("<td> <input type='checkbox' name='queryCheckBox' value='productPrice'> Select </td>");
                                pw.print("<td> Product Price: </td>");
                              pw.print(" <td>");
                                  pw.print("  <input type='number' name='productPrice' value = '0' size=10  /> </td>");
						pw.print("<td>");
					pw.print("<input type='radio' name='comparePrice' value='EQUALS_TO' checked> Equals <br>");
					pw.print("<input type='radio' name='comparePrice' value='GREATER_THAN'> Greater Than <br>");
					pw.print("<input type='radio' name='comparePrice' value='LESS_THAN'> Less Than");
					pw.print("</td></tr>");				
                            
  			
			pw.print("<tr><td> <input type='checkbox' name='queryCheckBox' value='reviewRating'> Select </td>");
                               pw.print(" <td> Review Rating: </td>");
                               pw.print(" <td>");
                                   pw.print(" <select name='reviewRating'>");
                                       pw.print(" <option value='1' selected>1</option>");
                                       pw.print(" <option value='2'>2</option>");
                                       pw.print(" <option value='3'>3</option>");
                                     pw.print("   <option value='4'>4</option>");
                                      pw.print("  <option value='5'>5</option>");
                                pw.print("</td>");
				pw.print("<td>");
				pw.print("<input type='radio' name='compareRating' value='EQUALS_TO' checked> Equals <br>");
				pw.print("<input type='radio' name='compareRating' value='GREATER_THAN'> Greater Than"); 
			pw.print("</td></tr>");
			
			pw.print("<tr>");
								pw.print("<td> <input type='checkbox' name='queryCheckBox' value='retailerCity'> Select </td>");
                                pw.print("<td> Retailer City: </td>");
                                pw.print("<td>");
                                pw.print("<input type='text' name='retailerCity' /> </td>");
								
                            pw.print("</tr>");
							
							 pw.print("<tr>");
								pw.print("<td> <input type='checkbox' name='queryCheckBox' value='retailerZipcode'> Select </td>");
                               pw.print(" <td> Retailer Zip code: </td>");
                               pw.print(" <td>");
                                    pw.print("<input type='text' name='retailerZipcode' /> </td>");
					        pw.print("</tr>");
				pw.print("<tr><td>");
					pw.print("<input type='checkbox' name='extraSettings' value='GROUP_BY'> Group By");
								pw.print("</td>");
								pw.print("<td>");
								pw.print("<select name='groupByDropdown'>");
                                pw.print("<option value='GROUP_BY_CITY' selected>City</option>");
                                pw.print("<option value='GROUP_BY_PRODUCT'>Product Name</option>");                                        
                                pw.print("</td><td>");
								pw.print("<input type='radio' name='dataGroupBy' value='Count' checked> Count <br>");
								pw.print("<input type='radio' name='dataGroupBy' value='Detail'> Detail <br>");
								pw.print("</td></tr>");
								
									
			
						pw.print("<tr>");
                                pw.print("<td colspan = '4'> <input type='submit' value='Find Data' class='btnbuy' /> </td>");
                            pw.print("</tr>");
							
							
		pw.print("</table>");	
		pw.print("</div></div></div>");			
		utility.printHtml("Footer.html");
	
	
	
			
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
