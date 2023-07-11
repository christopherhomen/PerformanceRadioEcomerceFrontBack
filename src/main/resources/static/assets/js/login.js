//Iniciaizar una variable que capture los datos del DOM
//en el HTML
const logiForm = document.querySelector('#loginForm');

//Inicializaremos un evento que nos envie la info
//se abrevia event con e
logiForm.addEventListener('submit',(e) =>{
    e.preventDefault()

    //Variable que captura datos del correo en el DOM
    const email = document.querySelector('#email').value;
    const password = document.querySelector('#password').value;

    //la variable constante user inicializa y traer los 
    //datos que estan en el localStorage

    //JSON.parse nos especifica en donde se 
    //alamcena la informacion de un arreglo

    // || [] significa que en el LocalStorage
    //se estan almacenando la informacion en un arreglo
    const Users =JSON.parse(localStorage.getItem('users')|| [])

    //Inicializamos una variable validUser que es para
    //validar inofrmacion    
    //find() busca elementos
    //comprueba si los datos que ingreso al logearme existen 
    const validUsers = Users.find(user => user.email === email && user.password === password)
    

    //En caso de no concordar los datos, muestra una alerta 
    if(!validUsers){
        return Swal.fire('Usuario y/o contrase.. incorrectas')
    }

    //Si son correctos, mostrara un mensaje de Bienvenida
    // Muestra la pop-up    
    Swal.fire({
        title: `Bienvenido ${validUsers.name}`,
        timer: 2000,
        timerProgressBar: true,
        didOpen: () => {
            Swal.showLoading()
        }
    }).then((result) => {
        if (result.dismiss === Swal.DismissReason.timer) {
            localStorage.setItem('login_success', JSON.stringify(validUsers))
            window.location.href='index.html'
        }
    })
})
