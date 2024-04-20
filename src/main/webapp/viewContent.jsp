<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Content</title>
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
                            <a href="" class="text-decoration-none">
                                <i class="fas fa-sign-out-alt pe-2"></i>Logout</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>

        <div class="navbar-left sidebar-sticky bg-light border">
            <ul class="nav flex-column">
                <form action="${pageContext.request.contextPath}/viewContent" method="GET">
                    <li class="nav-item border-bottom p-1">
                        <div class="input-group p-2 w-auto">
                            <input type="search" name="keyword" class="form-control" placeholder="Search..." value="${param.keyword}">
                            <button class="input-group-text" type="submit">
                                <i class="fa-solid fa-magnifying-glass  align-content-center"></i></button>
                        </div>
                    </li>
                </form>
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
                <h1>View Content</h1>
            </div>
            <div class="border rounded-top bg-light mt-3 p-1">View Content List</div>
            <div class="container p-3 border rounded-bottom">
                <table class="table table-striped table-bordered ">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Title</th>
                            <th>Brief</th>
                            <th>Created Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${listOfContent}" varStatus="no">
                            <tr>
                                <td>${no.count}</td>
                                <td>${c.title}</td>
                                <td>${c.brief}</td>
                                <td>${c.createDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:if test="${currentPage != 1}">
                        <li class="page-item">
                            <a class="page-link"
                               href="${pageContext.request.contextPath}/viewContent?page=${currentPage - 1}&keyword=${param.keyword}">
                                Previous
                            </a>
                        </li>
                        </c:if>

                        <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item active">
                                    <a class="page-link" href="">
                                            ${i}
                                    </a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link"
                                        href="${pageContext.request.contextPath}/viewContent?page=${i}&keyword=${param.keyword}">
                                            ${i}
                                    </a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                        </c:forEach>

                        <c:if test="${currentPage lt noOfPages}">
                            <li class="page-item">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/viewContent?page=${currentPage + 1}&keyword=${param.keyword}">
                                        Next
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>

            </div>
        </div>
    </div>
</body>

</html>