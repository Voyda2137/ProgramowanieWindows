<template>
    <div class="login-register-form">
        <h1>{{ isLogin ? 'Login' : 'Register' }} User</h1>
        <form v-if="isLogin" @submit.prevent="loginUser">
            <div class="form-group">
                <label for="login">Login:</label>
                <input type="text" id="login" v-model="loginData.login" required />
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <div class="password-field">
                    <input :type="showPassword ? 'text' : 'password'" id="password" v-model="loginData.password" required />
                    <span class="password-toggle" @click="togglePasswordVisibility">
            <i :class="['fas', showPassword ? 'fa-eye-slash' : 'fa-eye']"></i>
          </span>
                </div>
            </div>
            <button type="submit">Login</button>
        </form>
        <form v-else @submit.prevent="registerUser">
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" v-model="registerData.email" required />
            </div>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" v-model="registerData.name" required />
            </div>
            <div class="form-group">
                <label for="surname">Surname:</label>
                <input type="text" id="surname" v-model="registerData.surname" required />
            </div>
            <div class="form-group">
                <label for="login">Login:</label>
                <input type="text" id="login" v-model="registerData.login" required />
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <div class="password-field">
                    <input :type="showPassword ? 'text' : 'password'" id="password" v-model="registerData.password" required />
                    <span class="password-toggle" @click="togglePasswordVisibility">
            <i :class="['fas', showPassword ? 'fa-eye-slash' : 'fa-eye']"></i>
          </span>
                </div>
            </div>
            <div class="form-group">
                <label for="repeat-password">Repeat Password:</label>
                <div class="password-field">
                    <input :type="showPassword ? 'text' : 'password'" id="repeat-password" v-model="repeatPassword" required />
                    <span class="password-toggle" @click="togglePasswordVisibility">
            <i :class="['fas', showPassword ? 'fa-eye-slash' : 'fa-eye']"></i>
          </span>
                </div>
            </div>
            <button type="submit">Register</button>
        </form>
        <p class = "loginOrRegister">
            {{ isLogin ? "Don't have an account?" : "Already have an account?" }}
            <a href="#" @click="toggleFormMode">{{ isLogin ? 'Register' : 'Login' }}</a>
        </p>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import '@fortawesome/fontawesome-free/css/all.css';

const isLogin = ref(true);

const loginData = ref({
    login: '',
    password: ''
});

const registerData = ref({
    email: '',
    name: '',
    surname: '',
    login: '',
    password: ''
});

const repeatPassword = ref('');
const showPassword = ref(false);

const loginUser = () => {
    console.log('User logged in:', loginData.value);
};

const registerUser = () => {
    if (registerData.value.password !== repeatPassword.value) {
        alert('Password and Repeat Password must match.');
        return;
    }

    console.log('User registered:', registerData.value);
};

const toggleFormMode = () => {
    isLogin.value = !isLogin.value;
};

const togglePasswordVisibility = () => {
    showPassword.value = !showPassword.value;
};
</script>

<style scoped>
.login-register-form {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
}
.loginOrRegister {
    color: #333;
}
h1 {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
}

.form-group {
    margin-bottom: 20px;
}

label {
    display: block;
    font-weight: bold;
    color: #555;
}

input[type="email"],
input[type="text"],
input[type="password"] {
    width: 300px;
    padding: 8px;
    font-size: 16px;
    border: 1px solid #ccc;
    border-radius: 4px;
    color: #333;
    background-color: #fff;
    transition: width 0.3s;
}

.password-field {
    position: relative;
}

.password-field input[type="password"] {
    padding-right: 38px;
}

.password-field .password-toggle {
    position: absolute;
    top: 50%;
    right: 10px;
    transform: translateY(-50%);
    cursor: pointer;
    z-index: 1;
    color: #999; /* Adjust the color of the eye icon */
    font-size: 18px; /* Increase the size of the eye icon */
}

button[type="submit"] {
    display: block;
    width: 100%;
    padding: 10px;
    background-color: #4caf50;
    color: #fff;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    cursor: pointer;
}

button[type="submit"]:hover {
    background-color: #45a049;
}

p {
    text-align: center;
    margin-top: 20px;
}

a {
    color: #007bff;
    text-decoration: none;
}
</style>
