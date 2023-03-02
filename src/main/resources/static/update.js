$('#edit').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('id');
    showEditModal(id);
})

async function showEditModal(id) {
    let user = await getUser(id);
    let form = document.forms["formEditUser"];
    form.id.value = user.id;
    form.name.value = user.username;
    form.lastname.value = user.lastname;
    form.age.value = user.age;
    form.email.value = user.email;
    form.password.value = '';

    $('#rolesEditUser').empty();
    await fetch("http://localhost:8080/api/admin/roles")
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let el = document.createElement("option");
                el.text = role.name.substring(5);
                el.value = role.id;
                $('#rolesEditUser')[0].appendChild(el);
            })
        });
}

function updateUser() {
    const editForm = document.forms["formEditUser"];
    editForm.addEventListener("submit", ev => {
        ev.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < editForm.roles.options.length; i++) {
            if (editForm.roles.options[i].selected) editUserRoles.push({
                id : editForm.roles.options[i].value,
                name : editForm.roles.options[i].text
            })
        }
        fetch("http://localhost:8080/api/admin/" + editForm.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: editForm.id.value,
                username: editForm.name.value,
                lastname: editForm.lastname.value,
                age: editForm.age.value,
                password: editForm.password.value,
                email: editForm.email.value,
                roles: editUserRoles
            })
        }).then(() => {
            $('#editFormCloseButton').click();
            allUsers();
        })
    })
}