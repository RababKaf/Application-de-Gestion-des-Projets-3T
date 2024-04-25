<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Se Connecter</title>
    <style>
    
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background: url('img/sky.jpg') center center fixed;
            background-size: cover;
        }

        .login-container {
            background-color: #F2F0F23B;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            border-radius: 30px;
            overflow: hidden;
            width: 650px;
            clip-path: polygon(0 0, 100% 0, 100% 100%, 0 100%);
        }

        .login-header {
            background-color:#149CCC;
            color: #ffffff;
            text-align: center;
            padding: 18px;
        }

        .login-form {
            padding: 40px;
            background-color: #F2F0F23B; 
            border-radius: 0 0 30px 30px; 
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #5E9DC2;
            font-size: 16px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 12px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #5E9DC2;
            border-radius: 8px;
            font-size: 16px;
        }

        button {
            background-color: #E1711E85;
            color: #ffffff;
            padding: 14px;
            border: none;
            border-radius: 15px;
            cursor: pointer;
            width: 100%;
            font-size: 18px;
        }

        button:hover {
            background-color: #E1711EAD; 
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h2>Se Connecter</h2>
        </div>
        <div class="login-form">
            <form action="AuthentServlet" method="post">
                <label for="email">Email :</label>
                <input type="email" id="email" name="email" required>

                <label for="password">Mot de passe :</label>
                <input type="password" id="password" name="password" required>

                <button type="submit">Se connecter</button>
            </form>
        </div>
    </div>
</body>
</html>
