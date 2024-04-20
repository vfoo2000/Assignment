<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
    <style>
        .navbar-top {
            height: 50px;
        }

        .all-page {
            padding-top: 50px;
            display: inline-flex;
            width: 100%;
            min-height: 100vh;
        }

        .navbar-left {
            width: 20%;
        }

    </style>
</head>

<body>
    <div class="all-page">
        <div>
            <nav class="navbar navbar-top fixed-top navbar-light px-3 bg-light border">
                <div>CMS</div>
                <div class="dropdown pe-3">
                    <a class="dropdown-toggle text-decoration-none" type="button" data-bs-toggle="dropdown"
                        aria-expanded="false">
                        <i class="fa-solid fa-user pe-2"></i> ${currentAccount.firstName}
                    </a>
                    <ul class="dropdown-menu p-0 mt-2 dropdown-menu-end">
                        <li class="dropdown-item border-bottom p-2">
                            <a href="${pageContext.request.contextPath}/editEmployee" class="text-decoration-none">
                            <i class="fa-solid fa-user pe-2"></i>User Profile</a>
                        </li>
                        <li class="dropdown-item p-2">
                            <a href="${pageContext.request.contextPath}/logout" class="text-decoration-none">
                            <i class="fas fa-sign-out-alt pe-2"></i>Logout</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>

        <div class="navbar-left sidebar-sticky bg-light border">
            <ul class="nav flex-column">
                <li class="nav-item border-bottom p-1">
                    <div class="input-group p-2 w-auto">
                        <input type="search" class="form-control" placeholder="Search...">
                        <button class="input-group-text">
                            <i class="fa-solid fa-magnifying-glass  align-content-center"></i>
                        </button>
                    </div>
                </li>
                <li class="nav-item border-bottom p-1">
                    <a class="nav-link" href="${pageContext.request.contextPath}/viewContent">
                        <i class="fa-solid fa-table-cells"></i>
                        View contents
                    </a>
                </li>
                <li class="nav-item border-bottom p-1">
                    <a class="nav-link" href="${pageContext.request.contextPath}/addContent">
                        <i class="fa-regular fa-pen-to-square"></i>
                        Form content
                    </a>
                </li>
            </ul>
        </div>

        <div class="container p-5">
            <div class="border-bottom">
                <h1>Edit Profile</h1>
            </div>
            <c:if test="${not empty editMessageSubmit}">
                <div class="border rounded bg-success text-white mt-3 p-2 ps-3">
                    ${editMessageSubmit}
                </div>
            </c:if>
            <div class="border rounded-top bg-light mt-3 p-1 ps-3 ">Profile Form Elements</div>
            <f:form class="border rounded-bottom p-3" action="${pageContext.request.contextPath}/editEmployee" modelAttribute="editProfile" method="POST">
                <div class="mb-3">
                    <label class="form-label"><b>First name</b></label>
                    <f:input type="text" path="firstName" class="form-control" placeholder="${currentAccount.firstName}" maxlength="50"/>
                </div>
                <div class="mb-3">
                    <label class="form-label"><b>Last name</b></label>
                    <f:input type="text" path="lastName" class="form-control" placeholder="${currentAccount.lastName}" maxlength="50"/>
                </div>
                <div class="mb-3">
                    <label class="form-label"><b>Email</b></label>
                    <f:input type="email" path="email" class="form-control border-0 ps-0 bg-white" placeholder="${currentAccount.email}" maxlength="50" disabled="true"/>
                </div>
                <div class="mb-3">
                    <label class="form-label"><b>Phone number</b></label>
                    <f:input type="tel" path="phone" class="form-control" placeholder="${currentAccount.phone}"/>
                </div>
                <div class="mb-3">
                    <label class="form-label"><b>Description</b></label>
                    <f:textarea path="description" class="form-control" placeholder="${currentAccount.description}"/>
                </div>
                <div>
                    <button class="btn border" type="submit">Submit Button</button>
                    <button class="btn border" type="reset">Reset Button</button>                    
                </div>
            </f:form>

        </div>
    </div>
</body>

</html>