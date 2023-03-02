

function searchingWifi(){
    if(document.getElementById("LAT").value=='' || document.getElementById("LNT").value=='' ){
        alert("좌표값은 필수입니다.");
        return false;
    }

    document.getElementById("searchingForm").onsubmit;
}

function getPosition(){
    if(navigator.geolocation){

        navigator.geolocation.getCurrentPosition(UpdateValue);
    }else{
        alert('fail');
    }
}
function UpdateValue(pos){

    document.getElementById("LAT").value=pos.coords.latitude;
    document.getElementById("LNT").value=pos.coords.longitude;
}