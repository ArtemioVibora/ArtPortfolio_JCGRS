<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>


<body>

    <c:url var = "Insert" value = "/Artportfolio">
        <c:param name = "page" value = "insert"/>
    </c:url>


        <c:url var = "search" value = "/Artportfolio">
            <c:param name = "page" value = "retrieve"/>
        </c:url>

    <a href = "${Insert}">Photo Submission</a>
    <a href = "${search}">Artist Viewing</a>
</body>
</html>