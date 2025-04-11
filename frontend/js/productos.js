function registerDrink() {
    return new Promise(async (resolve) => {
        // alert("hola");
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        }

        let bodyContent = JSON.stringify({
            "id": 0,
            "name": document.getElementById("nombre").value,
            "price": document.getElementById("precio").value,
            "volume": document.getElementById("volumen").value,
            "stock": document.getElementById("stock").value
        });

        let response = await fetch("http://localhost:8080/api/v1/drinks/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.text();
        console.log(data);
        getDrink();

    });
}

function getDrink(){
    return new Promise(async (resolve) =>{
        var url="http://localhost:8080/api/v1/explorer/";
     
        let headersList = {
           "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json" 
        }

        let data = await response.JSON();
        //innerHTML="" limpiar la lista
        var container = document.getElementById("");
        container.innerHTML="";
        data.forEach(register => {
            // console.log(register);
            //se crea la columna
            var col=document.createElement("div");
            col.className="col-lg-3 col-md-4 col-sm-6 pb-1";
            //creación de la tarjeta
            var card=document.createElement("div");
            card.className="team-item bg-white mb-4";
            //contenedor de la imagen
            var containerImage=document.createElement("div");
            containerImage.className="team-img position-relative overflow-hidden";

            //Imagen
            var image=document.createElement("img");
            image.className="img-fluid w-100";
            image.src=register["imageExplorer"];

            //contenedor texto
            var containertText=document.createElement("div");
            containertText.className="text-center py-4";
            //titulo
            var title=document.createElement("h5");
            title.className="text-truncate";
            title.innerText=register["name"];

            //descripción
            var description=document.createElement("p");
            // title.className="text-truncate";
            description.innerHTML="Age:"+register["age"]+"<br> Reputation:"+register["reputation"];

            var btnEdit=document.createElement("button");
            btnEdit.className="btn btn-warning";
            btnEdit.innerText="Edit";
            
            var btnDelete=document.createElement("button");
            btnDelete.className="btn btn-danger";
            btnDelete.innerText="Delete";
            
            //se agrega al documento
            containerImage.appendChild(image);
            card.appendChild(containerImage);

            containertText.appendChild(title);
            containertText.appendChild(description);
            card.appendChild(containertText);
            card.appendChild(btnEdit);
            card.appendChild(btnDelete);

            col.appendChild(card);
            container.appendChild(col);
        });
    });

}