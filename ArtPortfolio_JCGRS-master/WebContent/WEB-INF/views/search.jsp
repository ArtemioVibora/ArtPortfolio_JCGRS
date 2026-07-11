<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>

<body>
    <c:url var = "search" value = "/Artportfolio">
        <c:param name = "page" value = "retrieve"/>
    </c:url>

    <form  method = "post">
        <input type="hidden" name="unusualAction" value="retrieve"/>
        <label>Type In Artist Name: </label>
        <input type = "text" name = "Artistname" required/>
        <button type = "submit" id = "getFromDatabase">Submit</button>
    </form>


    <c:set var = "show" value="${not empty postAspects}" />
    <div id = "goThroughArtWork" style = "${show ? 'display:block' : 'display:none'}">
        <c:forEach var = "item" items = "${postAspects}">
         <p><img src = "data:image/png;base64,${item.convertedPicture}"></p>
         <p>${item.artistName}</p>
         <p>${item.artName}</p>
        </c:forEach>
    </div>


</body>


</html>