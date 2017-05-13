var getUtilList = function(){
	var requeteGetUtil = new XMLHttpRequest();
	console.log("test getUtilList");
	requeteGetUtil.open("GET","inscriptionUtil/list_user/add_user");
	console.log("test getUtilList après GET");
	requeteGetUtil.responseType = "json";
	requeteGetUtil.onload = function(){
		console.log("test requeteGetUtil_début");
		var l = this.response.length;
		for(var i=0 ; i<l ; i++){
			var liUtil = document.createElement("li");
			liUtil.className= "list-group-item text-center";
			liUtil.textContent = "Email :"+this.response[i].email;
			console.log("test requeteGetUtil_fin");
			
			document.getElementById("listOfUtilisateur").appendChild(liUtil);
		}
	}
	requeteGetUtil.send();
};

var addUtilisateur = function(email, mdp, nom, prenom){
	var requeteAddUtilisateur = new XMLHttpRequest();
	requeteAddUtilisateur.open("POST","pages/utilisateur/ajouter_utilisateur");
	requeteAddUtilisateur.responseType = "json";
	console.log("test ajouterUtilisateur_début/fin");
	
	var link =+"&email="+email+"&mdp="+mdp+"&nom="+nom+"&prenom="+prenom;
	requeteAddUtilisateur.onload = function(){
		console.log("test requeteAddUtilisateur_début");
		var liUtilisateur = document.createElement("li");
		liUtilisateur.className="list-group-item textCenter";
		liUtilisateur.textContent = txt;
		document.getElementById("email").value="";
		document.getElementById("mdp").value="";
		document.getElementById("nom").value="";
		document.getElementById("prenom").value="";
		document.getElementById("listOfUtilisateur").appendChild(liUtilisateur);
		console.log("test requeteAddUtilisateur_fin");
	}
	requeteAddUtilisateur.send(link);
};

window.onload = function(){
	getUtilList();
	
	console.log("test on load");
	document.getElementById("ajouterUtilisateur").onclick = function(){
		console.log("test win.onload_addUtil_début");
		addUtilisateur(
				document.getElementById("email").value,
				document.getElementById("mdp").value,
				document.getElementById("nom").value,
				document.getElementById("prenom").value);
				
		console.log("test win.onload_addUtilisateur_fin");
		window.location.reload;
	};
};