function myPosition(){
	navigator.geolocation
			.getCurrentPosition((position) => {
			document.getElementById("LAT").value=position.coords.latitude;
    		document.getElementById("LNT").value=position.coords.longitude;	
			});
}