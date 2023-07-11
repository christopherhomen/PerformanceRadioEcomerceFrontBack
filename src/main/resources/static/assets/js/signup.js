// /*Código del registro:/Inicializar una variable que reciba datos del DOM*/
// const signupForm = document.querySelector('#signupForm')

// //Inicializaremos un evento para que nos envíe los datos con el botón
// signupForm.addEventListener('submit', (e) => {
//     e.preventDefault()

//     //Referenciar los datos
//     const name = document.querySelector('#name').value
//     const email = document.querySelector('#email').value
//     const password = document.querySelector('#password').value

//     //Trae los datos almacenados del arreglo que se nombra (users)
//     //Nos convierte los datos en JSON en el localstorage
//     const Users = JSON.parse(localStorage.getItem('users')) || []

//     //la función find() nos ayuda a buscar los datos almacenados
//     //Nos comprueba si el correo que se ingresa es igual al que esta guardado en el localstorage
//     const isUserRegistered = Users.find(user => user.email === email)

//     //Realizaremos una comprobación
//     if(isUserRegistered) {
//         return alert('El usuario ya esta registrado! , intentalo con otro')
//     }
//     //Agregaremos los datos al objeto
//     Users.push({name: name, email: email, password: password})
//     localStorage.setItem('users', JSON.stringify(Users))

//     alert('Registro Exitoso')
//     window.location.href = 'iniciosesionLS.html'
// })

const signupForm = document.querySelector('#signupForm')

signupForm.addEventListener('submit', (e) => {
    e.preventDefault()

    const name = document.querySelector('#name').value
    const number = document.querySelector('#number').value
    const email = document.querySelector('#email').value
    const password = document.querySelector('#password').value

    const Users = JSON.parse(localStorage.getItem('users')) || []

    const isUserRegistered = Users.find(user => user.email === email)

    const isNumberRegistered = Users.find(user => user.number=== number)

    const isPasswordRegistred = Users.find(user => user.password===password)

    const numerString = number.toString;

     //---------Validaciones campos vacios-----------///

    if(name==="" || number==="" || email==="" || password===""){
        return Swal.fire('Tiene campos vacios, por favor, rellenarlos')
    }

    

    //------Valida contraseña : si existe, si no, que cumpla que tenga minimo 2 Mayusculas y 3 digitos-//


    if(isPasswordRegistred){
        return Swal.fire('Esta contraseña ya esta')
    }else{
        let mayuscul = password.match(/[A-Z]/g);
        let digitos = password.match(/[0-9]/g)
        let totalMayuscul = mayuscul ? mayuscul.length:0;
        let totalDigitos = digitos ? digitos.length:0;
        if(totalMayuscul<2 && totalDigitos<3){
            return Swal.fire('Su contraseña necesita al menos dos mayusculas y 3 digitos')
        }
    }

    //--------Valida si el email ya esta-------//

    if(isUserRegistered) {
        return Swal.fire('El usuario ya esta registrado! , intentalo con otro')
    }

    //--Verifica si el numero ingresado ya existe---///

    if(isNumberRegistered){
        return Swal.fire('Uppsss.. el numero ya esta registrado! , intentalo con otro')
    }

    //--------Validacion de longitud del numero------//

    /*if(numerString.length<5){
        return Swal.fire('No es un numero valido, digite un numero de longitud mas grande')
    }*/

    //--Se envia el objeto con los diferentes atributos del user-///

    
    Users.push({name: name, number:number, email: email, password: password})
    localStorage.setItem('users', JSON.stringify(Users))

    Swal.fire({
        title: 'Registro Exitoso',
        timer: 2000,
        timerProgressBar: true,
        didOpen: () => {
            Swal.showLoading()
        }
    }).then((result) => {
        if (result.dismiss === Swal.DismissReason.timer) {
            window.location.href = 'iniciosesionLS.html'
        }
    })
})
