<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 상세보기</title>
    <style>
        .container{
            height: 100vh;
            display: flex;
            flex-direction: column;
        }
        .image{
            height: 40%;
            background: no-repeat center/cover;
        }
        .title{
            height: 20%;
            font-size: 2em;
            padding: 20px;
        }
        .desc{
            height: 30%;
            font-size: 1.2em;
            padding: 20px;
        }
        .buttons{
            height: 10%;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            padding-right: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="image" style="background-image: url('image.jpg');"></div>
        <div class="title">제목</div>
        <div class="desc">내용</div>
        <div class="buttons">
            <button onclick="location.href='edit.jsp'">수정</button>
            <button onclick="location.href='delete.jsp'">삭제</button>
        </div>
    </div>
</body>
</html>