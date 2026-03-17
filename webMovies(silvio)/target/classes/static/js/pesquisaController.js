function montarTabela(json) {
    let tabela="";
    for(filme of json){
       tabela+=`<tr>
        <td>${filme.title}</td>
        <td>${filme.year}</td>
        <td>${filme.category}</td>
        <td>
        <img src="/posters/${filme.poster}" width="100" style="cursor: pointer" onclick="abrirPoster('${filme.poster}')">
        </td>
       </tr>`;
    }
    return tabela;
}


function pesquisarFilmes(){
    const filmes=document.getElementById('resultado');
    const filtro=document.getElementById('pesquisa').value;
    fetch("http://localhost:8080/apis/get-movies-filter?filter="+filtro)
        .then(response=>{
            if(response.status === 200)
                return response.json().
                then(json=>{
                   filmes.innerHTML=montarTabela(json);
                })
            else{
                alert("Não há resultados")
            }
        })
        .catch(Error=>filme.innerHTML=Error);
}

function pesquisarGenero(){
    const genero = document.getElementById("genero").value;

    fetch("http://localhost:8080/apis/list-genre/" + genero)
        .then(response => {
            if(response.status === 200)
                return response.json().then(json=>{
                    resultado.innerHTML = montarTabela(json);
                });
            else
                alert("Nenhum resultado");
        });
}

function pesquisarAno(){
    const filmes = document.getElementById('resultado');
    const anoInicio = document.getElementById('anoInicio').value;
    const anoFim = document.getElementById('anoFim').value;

    fetch("http://localhost:8080/apis/list-year/" + anoInicio + "/" + anoFim)
        .then(response=>{
            if(response.status === 200)
                return response.json().then(json=>{
                    filmes.innerHTML = montarTabela(json);
                })
            else{
                alert("Nenhum resultado");
            }
        })
        .catch(error => filmes.innerHTML = error);
}

function abrirPoster(nome){

    document.getElementById("posterGrande").src = "http://localhost:8080/posters/" + nome;
    document.getElementById("posterModal").style.display = "block";

}

function fecharPoster(){
    document.getElementById("posterModal").style.display = "none";
}