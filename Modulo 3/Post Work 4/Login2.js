const users = [{ email: "myemail@mail.com", password: "securePassword" }]
let counter = 0;

const login = (userCredentials) => {
    for (let i = 0; i < users.length; i++) {

        if (userCredentials.email === users[0].email && userCredentials.password === users[0].password) {

            //users.counter = 0;
            getToken(users);
            return "Bienvenido al sistema";


        } else if (userCredentials.email === users[0].email && userCredentials.password != users[0].password) {
            //users.counter += 1;
            counter++;
            if (counter > 2) {

                return "Máxima cantidad de intentos alcanzada, espere 5 minutos";
            }
            return "Contraseña incorrecta";

        }
        return "User not found";
    }

}

const getToken = (userCredentials) => {
    for (let i = 0; i < users.length; i++) {
        if (userCredentials.email === users[i].email && userCredentials.password === users[i].password) {

            var emailBeforeEncode = users[i].email;

            var buff = new Buffer(emailBeforeEncode);
            var encodedEmail = buff.toString('base64');

            return encodedEmail;

        }

    }

}



module.exports = { login, getToken };
