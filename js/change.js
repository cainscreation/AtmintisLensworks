$(document).ready(function(){
    window.setTimeout(function(){
        $(".containerwhite").addClass("");
    },10000);
});
function myFunction() {
   var element = document.getElementById("myDIV");
   element.classList.toggle("mystyle");
}
