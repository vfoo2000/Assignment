<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
    <style>
        .all-page{
            background-color: rgb(248, 248, 248);
            min-height: 100vh;
            padding-top: 150px;
        }

        .container {
            width: 400px;
        }

        .btn-login {
            background-color: rgba(0, 206, 129, 255);
            color: white;
        }

        .btn-forgot:hover {
            border: 0px;
        }

        .bg-lightgray {
            background: lightgray;
        }
    </style>
</head>

<body>
    <div class="all-page">
        <div class="container border-black border rounded-top py-2 ps-3 bg-lightgray">
            Register
        </div>
        <div class="container border-black border bg-white">
            <div>
                <f:form action="${pageContext.request.contextPath}/register" modelAttribute="newRegister" method="POST">
                    <div class="">
                        <div class="input-group my-3">
                            <f:input type="text" class="form-control" path="userName" placeholder="User name" maxlength="50"/>
                            <f:errors path="userName" cssClass="mb-3 text-danger"/>
                        </div>
                        <div class="input-group mb-3">
                            <f:input type="email" class="form-control" path="email" placeholder="E-mail" maxlength="50"/>
                            <f:errors path="email" cssClass="mb-3 text-danger"/>
                        </div>
                        <div class="input-group mb-3">
                            <f:input type="password" class="form-control" path="password" placeholder="Password" maxlength="50"/>
                            <f:errors path="password" cssClass="mb-3 text-danger"/>
                        </div>
                        <div class="input-group mb-3">
                            <f:input type="password" class="form-control" path="rePassword" placeholder="Re Password"/>
                            <f:errors path="rePassword" cssClass="mb-3 text-danger"/>
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-login w-100">Register</button>
                        </div>
                    </div>
                </f:form>
                <div class="mb-3">
                    <a href="${pageContext.request.contextPath}/login" class="text-decoration-none">
                        <span>Click here to Login</span>
                    </a>
                </div>
            </div>
        </div>
    </div>    
</body>

</html>