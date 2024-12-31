const apiUrl = 'http://localhost:5051/';

window.onload = () => {
    fetchData("wind-farm");
};


async function fetchData(route) {
    try {
        const response = await fetch(apiUrl + route);

        if (!response.ok) {
            throw new Error("Erro ao buscar dados: " + response.status);
        }

        const data = await response.json();

        const farmsContainer = document.getElementById("farms-container");
        farmsContainer.innerHTML = "";

        data.forEach((farm) => { 
            const farmDiv = document.createElement("div");
        farmDiv.classList.add("farm-item");
        farmDiv.textContent = farm.name;


        const deleteButton = document.createElement("button");
        deleteButton.classList.add("delete-btn");
        deleteButton.innerHTML = '<i class="fas fa-trash"></i>';

        deleteButton.addEventListener('click', (event) => {
            event.stopPropagation();
            modalDeleteWF(farm.id);
            removeFarmFromStorage(farm.name);
    });

    farmDiv.appendChild(deleteButton);
    farmsContainer.appendChild(farmDiv);

    farmDiv.addEventListener('click', () => {
        localStorage.setItem("farmName", farm.name);
        localStorage.setItem("farmId", farm.id);
        localStorage.setItem("turbinesData", JSON.stringify(farm.windTurbines));
        window.location.href = "parque.html";
    });
        });

    } catch (error) {
        console.error(error);
        const farmsContainer = document.getElementById("farms-container");
        farmsContainer.innerHTML = "<p>Ocorreu um erro ao buscar os parques.</p>";
    }
}


async function createWindFarm(name, location) {
    const data = {
        name: name,
        location: location
    };

    console.log("Enviando dados:", data);  

    try {
        const response = await fetch(apiUrl + 'wind-farm', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error('Erro ao enviar dados: ' + response.status);
        }

        const responseData = await response.json();

        console.log('Dados enviados com sucesso:', responseData);
    } catch (error) {
        console.error('Erro:', error);
    }

    window.location.reload();
}


async function removeWindFarm(id) {
    console.log("Excluindo parque eólico:", id);

    try {
        const response = await fetch(apiUrl + 'wind-farm/' + id, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error('Erro ao excluir dados: ' + response.status);
        }

        const responseData = await response.json();

        console.log('Dado excluído com sucesso:', responseData);
    } catch (error) {
        console.error('Erro:', error);
    }

    window.location.reload();
}


async function createWindTurbine(idCode, model, lat, lon, idWF) {
    const data = {
        idCode: idCode,
        model: model,
        latitude: lat,
        longitude: lon,
        windFarmId: idWF
    };

    console.log("Enviando dados:", data);  
    try {
        const response = await fetch(apiUrl + 'wind-turbine', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error('Erro ao enviar dados: ' + response.status);
        }

        const responseData = await response.json();

        console.log('Dados enviados com sucesso:', responseData);
    } catch (error) {
        console.error('Erro:', error);
    }

    window.location.reload();
}

async function removeWindTurbine(id) {
    console.log("Excluindo turbina eólica:", id);

    try {
        const response = await fetch(apiUrl + 'wind-turbine/' + id, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error('Erro ao excluir dados: ' + response.status);
        }

        const responseData = await response.json();

        console.log('Dado excluído com sucesso:', responseData);
    } catch (error) {
        console.error('Erro:', error);
    }

    window.location.reload();
}

const parkId = localStorage.getItem('farmId'); 
const parkName = localStorage.getItem('farmName'); 
document.getElementById('windFarmInfo').textContent = `Parque Eólico ${parkName}`;

const turbinesData = JSON.parse(localStorage.getItem('turbinesData'));
const turbinesContainer = document.getElementById('turbines-container');
turbinesContainer.innerHTML = '';

if (turbinesData && turbinesData.length > 0) {
    turbinesData.forEach((turbine) => {
        const turbineDiv = document.createElement('div');
        turbineDiv.classList.add('turbine-item');
        turbineDiv.textContent = `Turbina ${turbine.idCode}`;
        

        const deleteButton = document.createElement("button");
        deleteButton.classList.add("delete-btn");
        deleteButton.innerHTML = '<i class="fas fa-trash"></i>'; 

        deleteButton.addEventListener('click', (event) => {
            event.stopPropagation();  
            modalDeleteWT(turbine.id);  
            removeWindTurbineFromStorage(turbine.id);
        });

        turbineDiv.appendChild(deleteButton);  
        turbinesContainer.appendChild(turbineDiv);  
    });
} else {
    turbinesContainer.innerHTML = "<p>Sem turbinas disponíveis.</p>";
}

function initMap() {
    if (turbinesData && turbinesData.length > 0) {
        let totalLat = 0;
        let totalLon = 0;

        turbinesData.forEach((turbine) => {
            totalLat += turbine.latitude;
            totalLon += turbine.longitude;
        });

        const avgLat = totalLat / turbinesData.length;
        const avgLon = totalLon / turbinesData.length;

        var map = L.map('mapId').setView([avgLat, avgLon], 13); 

        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);

        turbinesData.forEach((turbine) => {
            L.marker([turbine.latitude, turbine.longitude]).addTo(map)
                .bindPopup(`${turbine.idCode}`)
                .openPopup();
        });
    }

}


async function goToPage(pageName) {
    window.location.href = pageName;
}

if (window.location.pathname.includes('parque.html')) {
    initMap();
}

async function modalNewWF() {
    var modal = document.getElementById("myModal");
    var span = document.getElementsByClassName("close")[0];

    modal.style.display = "block";

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
    }
}

async function modalDeleteWF(id) {

    var modal = document.getElementById("deleteModal");
    var span = document.getElementsByClassName("close")[0];
    var yesBtn = document.getElementById("yesBtn");
    var noBtn = document.getElementById("noBtn");

    modal.style.display = "block";

    yesBtn.onclick = function(){
        removeWindFarm(id);
        window.location.reload();
    }

    span.onclick = function() {
        closeModal("deleteModal");
    }

    noBtn.onclick = function(){
        closeModal("deleteModal");
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            closeModal("deleteModal");
        }
    }
}

async function modalDeleteWT(id) {

    var modal = document.getElementById("deleteModalWT");
    var span = document.getElementsByClassName("close")[0];
    var yesBtnWT = document.getElementById("yesBtnWT");
    var noBtnWT = document.getElementById("noBtnWT");

    modal.style.display = "block";

    yesBtnWT.onclick = function(){
        removeWindTurbine(id);
        window.location.reload();
    }

    span.onclick = function() {
        closeModal("deleteModalWT");
    }

    noBtnWT.onclick = function(){
        closeModal("deleteModalWT");
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            closeModal("deleteModalWT");
        }
    }
}

async function modalNewWT() {
    var modal = document.getElementById("newWT");
    var span = document.getElementsByClassName("close")[0];

    modal.style.display = "block";

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}



async function cadastroWFOK() {
    var name = document.getElementById("editWindFarmName");
    var loc = document.getElementById("editWindFarmLoc");
    var msgError = document.getElementsByClassName("errorMsg")[0];

    if (name !== null && loc !== null && name.value.trim() !== "" && loc.value.trim() !== "") {
        createWindFarm(name.value, loc.value);
    } else{
        msgError.innerHTML = '<span class="error-message">Preencha os dados corretamente!</span>';
        
        setTimeout(function() {
            msgError.innerHTML = ''; 
        }, 3000); 
    }
}

document.getElementById("cadastroOKBtn").addEventListener("click", () => {
    if(parkId){
        cadastroWTOK(parkId);
    }
});

async function cadastroWTOK(idFarm) {
    var idCode = document.getElementById("editWindTurbineIdCode");
    var model = document.getElementById("editWindTurbineModel");
    var lat = document.getElementById("editWindTurbineLat");
    var lon = document.getElementById("editWindTurbineLon");

    var msgError = document.getElementsByClassName("errorMsg")[0];

    if (idCode !== null && model !== null && lat !== null && lon !== null &&
        idCode.value.trim() !== "" && model.value.trim() !== "" && lat.value.trim() !== "" && lon.value.trim() !== "") {
        createWindTurbine(idCode.value, model.value, lat.value, lon.value, idFarm);
    } else{
        msgError.innerHTML = '<span class="error-message">Preencha os dados corretamente!</span>';
        
        setTimeout(function() {
            msgError.innerHTML = ''; 
        }, 3000); 
    }
}

async function closeModal(modalId) {
    var modal = document.getElementById(modalId);

    modal.style.display = "none";
}