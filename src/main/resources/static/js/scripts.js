

function addToCard(id){
    alert('OK'+id)
}


$.ajax({
  type: 'POST',
  url: 'http://localhost:8080',
  data: model,
  success: success,
  dataType: dataType
});