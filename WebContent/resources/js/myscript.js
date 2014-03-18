/**
 * Affiche la le help.
 * @param obj champs pour lequel le help sera affiché
 * @param text
 * @return
 */
function showHelp(objLeftPos, idTopPos, text){
	var helpBox = document.getElementById("helpBox");
	var topPosObj = document.getElementById(idTopPos);
	var left = findPosX(objLeftPos);
	var top = findPosY(topPosObj);
	helpBox.style.display = "inline";
	helpBox.style.left = (left + 263) + "px";
	helpBox.style.top = top + "px";
}
function hideHelp(text){
	var helpBox = document.getElementById("helpBox");
	helpBox.style.display = "none";
}

/*Retourne la position X de l'élément indiqué.*/
function findPosX(obj)
{
	var curleft=0;
	if(obj.offsetParent)
	{
		while(obj.offsetParent)
		{
			curleft+=obj.offsetLeft
			obj=obj.offsetParent;
		}
	}else if(obj.x)curleft+=obj.x;
	return curleft;
}

/*Retourne la position Y de l'élément indiqué.*/
function findPosY(obj)
{
	var curtop=0;
	if(obj&&obj.offsetParent)
	{
		while(obj.offsetParent)
		{
			curtop+=obj.offsetTop	
			obj=obj.offsetParent;
		}
	}else if(obj&&obj.y)
	{
		curtop+=obj.y;
	}
	return curtop;
}

/**
 * Cache ou affiche la liste des messages errors.
 * @param id
 */
function shouldShowErrorsBox(id){
	var errorsBox = document.getElementById("errorsBox");
	var obj = document.getElementById(id);
	if(obj !=null && obj.childNodes.length != 0){
		errorsBox.style.display = "block";
	}else{
		errorsBox.style.display = "none";
	}
}

function showHideOtherBox(){
	
	
	var e = document.getElementById("contract-type-id");
	var selectedOption = e.options[e.selectedIndex].value;
	
	console.log(selectedOption);
	
	if(selectedOption !=null && selectedOption == '1'){
		document.getElementById("stock-option-section-id").style.display = "block";;
		document.getElementById("action-section-id").style.display = "none";;
		document.getElementById("titre-section-id").style.display = "none";;
	}else if(selectedOption !=null && selectedOption == '2'){
		document.getElementById("stock-option-section-id").style.display = "none";;
		document.getElementById("action-section-id").style.display = "block";;
		document.getElementById("titre-section-id").style.display = "none";;
	}else if(selectedOption !=null && selectedOption == '3'){
		document.getElementById("stock-option-section-id").style.display = "none";;
		document.getElementById("action-section-id").style.display = "none";;
		document.getElementById("titre-section-id").style.display = "block";;
	}
}

function showHide() {
	console.log("merde");
    var ele = document.getElementById("hideDiv");
    console.log(ele);
    if(ele.style.display == "block") {
            ele.style.display = "none";
      }
    else {
        ele.style.display = "block";
    }
}