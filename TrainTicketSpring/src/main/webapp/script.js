// store html elements to variables
var ticketForm = document.getElementById("ticketForm");
var passengers = document.getElementById("passengers");
var another = document.getElementById("anotherPassenger");
var dateField = document.getElementById("date");

// When Generate button is clicked, display the ticket form and hide the welcome div
document.getElementById("generate").addEventListener("click", function(){
	document.getElementById("ticketForm").style.display = "flex";
	document.getElementById("welcome").style.display = "none";
});
// validate the train number
function myFunction(){
// get the value of the input field with id
let x=document.getElementById("trains").value;

let text;
let text1;
if(isNaN(x) || x<1001 || x>1006){
text="Please enter train number between 1001 and 1006";
document.getElementById("demo").innerHTML=text;
} else {
text1="Train available";
document.getElementById("demo").innerHTML=text1;
}
}
// generate the correct number of passenger fields whenever the user selects a number of passengers from dropdown
document.getElementById("numberOfPassengers").addEventListener("change", function(){
	// display submit button and clear passengers div
	document.getElementById("submit").style.display="block";
	passengers.innerHTML = "";

	// lookup number of passengers user has selected
	var passengerCount = parseInt(this.options[this.selectedIndex].value);

	// loop and add form fields to passengers innerHTML
	for (let i=0; i<passengerCount; i++) {
		passengers.innerHTML += `
			<label class="passengerNumber"><strong>Passenger ${i+1}</strong></label>
			<div class="passengerDetails">
				<div class="pRow">
					<label>Name:</label>
					<input class="inputField" type="text" name="pName${i}" placeholder="Full Name" autocomplete="off" required />
				</div>
				<div class="pRow">
					<label>Age:</label>
					<input class="inputField" type="number" name="pAge${i}" min="0" max="100" required />
				</div>
				<div class="pRow">
					<label>Gender:</label>
					<select name="pGender${i}" class="inputField" required>
						<option value="M" selected>Male</option>
						<option value="F">Female</option>
					</select>
				</div>
			</div>
			`
	}
});

// update date input field when train is selected
document.getElementById("trains").addEventListener("change", function(){
	// lookup today's date and get individual components
	var today = new Date();
	var dd = String(today.getDate() + 1).padStart(2, '0');
	var mm = String(today.getMonth() + 1).padStart(2, '0');
	var yyyy = today.getFullYear();

	// create a minimum date and maximum date (1 year of possible dates)
	var minDate = yyyy + '/' + mm + '/' + dd;
	var maxDate = (yyyy + 1) + '/' + mm + '/' + dd;

	// set the min and max date attributes for input date field. set initial value to min
	dateField.setAttribute("value", minDate);
	dateField.setAttribute("min", minDate);
	dateField.setAttribute("max", maxDate);

	// enable the date field and number of passengers field
	dateField.disabled=false;
	document.getElementById("numberOfPassengers").disabled=false;
});