async function createUser() {
    //берет данные из страницы
    await fetch("http://localhost:8080/api/admin/roles")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let el = document.createElement("option");
                el.text = role.name.substring(5);
                el.value = role.id;
                $('#newUserRoles')[0].appendChild(el);
            })
        })

    const form = document.forms["formNewUser"];

    form.addEventListener('submit', addNewUser)

    function addNewUser(e) {
        e.preventDefault();
        let newUserRoles = [];
        for (let i = 0; i < form.roles.options.length; i++) {
            if (form.roles.options[i].selected) newUserRoles.push({
                id : form.roles.options[i].value,
                name : form.roles.options[i].name
            })
        }
        fetch("http://localhost:8080/api/admin", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: form.name.value,
                lastname: form.lastname.value,
                age: form.age.value,
                password: form.password.value,
                email: form.email.value,
                roles: newUserRoles
            })
        }).then(() => {
            form.reset();
            allUsers();
            $('#allUsersTable').click();
        })
    }

}