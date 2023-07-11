//Se le da acceso al usuario que ya se encuentra registrado
const user = JSON.parse(localStorage.getItem('login_success')) || false

if(!user){
    window.location.href='iniciosesionLS.html'
}
//Referenciar los datos con el DOM del html
const logout = document.querySelector('#logout')

logout.addEventListener('click', ()=>{
    alert('Hasta luego, vuelve pronto')
    localStorage.removeItem('login_success')
    window.location.href='login.html'
})
