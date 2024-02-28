var visibility = false
function changeLoginVisibility(){
    var check = document.getElementById("login").style.visibility;
    if(check == hidden){
        document.getElementById("login").classList.add("visible");
        document.getElementById("login").classList.remove("invisible");
    }
    else{
        document.getElementById("login").classList.add("invisible");
        document.getElementById("login").classList.remove("visible");
    }
}

console.log("hallo ich wurde gefunden");