function carregarFilmeAleatorio(){
    const filme=document.getElementById('filme-sugerido');
    fetch("http://localhost:8080/apis/random-movie")
        .then(response=>response.json())
        .then(json => {
            filme.innerHTML=`${json.title}, lançado em ${json.year}, gênero de ${json.category}`;
        })
        .catch(Error=>filme.innerHTML=Error);
}