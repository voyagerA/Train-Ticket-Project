<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat"
contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.trainticket.TrainDAO" %>
<%@ page import="com.trainticket.Train" %>
<%@ page import="com.trainticket.Ticket" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Train Ticket</title>
<link rel="stylesheet" type="text/css" href=main.css>
</head>
<body>
<div class="main ticketMain">
	<h1>Your Train Ticket Information</h1>
<%-- Variable Declaration --%>
<%! int trainNum, passengerNumber; %>
<%! Date travelDate; %>
<%! String pnr, trainName, source, destination, dateStr; %>
<%! double ticketPrice; %>
<%! int yyyy,mm,dd; %>
<%! Ticket ticket; %>
<%! Train train; %>
<%! TrainDAO trainDao = new TrainDAO(); %>

<%-- Application Logic --%>
<%
// Get train number, number of passengers, and travel date from user
trainNum = Integer.parseInt(request.getParameter("trainNum"));
passengerNumber = Integer.parseInt(request.getParameter("passengerNumber"));
dateStr = request.getParameter("travelDate");

if (trainNum == 0 || passengerNumber == 0 || dateStr == null) {
	%>
	<h3 class="errorMsg">Something Went Wrong</h3>
	<a href="index.jsp"><button class="btn back">Try Again</button></a>
	<%
} else {

	// Create train object with train number
	train = trainDao.findTrainNo(trainNum);

	// If train object returned is null, an invalid tran number was given
	if (train == null) {
		%><h3 class="errorMsg">Train with given train number does not exist</h3>
		<a href="index.jsp"><button class="btn back">Try Again</button></a><%
	} else {
		// Split date string into month, year, and day ints
		String[] dateArr = dateStr.split("-");
		yyyy = Integer.parseInt(dateArr[0]);
		mm = Integer.parseInt(dateArr[1]);
		dd = Integer.parseInt(dateArr[2]);

		// Create a date object using date format
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			travelDate = formatter.parse(dd+"/"+mm+"/"+yyyy);
	    } catch (Exception e) {
	    	%><h3 class="errorMsg">Something Went Wrong</h3>
	    	<a href="index.jsp"><button class="btn back">Try Again</button></a><%
	    }


		Date currentDate = new Date();
		if (travelDate.before(currentDate)) {
			%><h3 class="errorMsg">Travel Date is before current date</h3>
			<a href="index.jsp"><button class="btn back">Try Again</button></a><%
		} else {

			// create ticket object with travel date and train
			ticket = new Ticket(travelDate, train);

			// Loop using number of passengers and add passengers to ticket
			for (int i=0; i<passengerNumber; i++) {
				String name = request.getParameter("pName" + i);
				int age = Integer.parseInt(request.getParameter("pAge" + i));
				char gender = request.getParameter("pGender" + i).charAt(0);

				// if name was left blank, or if age was less than 0
				if (name.equals("") || name == null) name = "Blank Name";
				if (age<0) age=0;

				// add passenger to ticket
				ticket.addPassenger(name,gender,age);
			}

			// Generate Ticket and Save
			ticket.generatePNR();
			ticket.writeTicket();
			StringBuilder ticketOutput = ticket.generateTicket();

			%>
			<pre id="ticket"><%= ticketOutput.toString() %></pre>


			<h3>Your Ticket Has Been Saved!</h3>

			<a href="index.jsp"><button class="another">Generate Another Ticket</button></a>
			<%
		}
	}
}
%>
<button onclick="window.print()">Print Your Ticket</button>
</div>

</body>
</html>