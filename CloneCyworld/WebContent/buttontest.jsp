<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <style>
        .container {
            display: flex;
            width: 300px; /* 네모난 박스의 너비 */
            height: 200px; /* 네모난 박스의 높이 */
            border: 1px solid #000; /* 테두리 스타일 지정 */
            flex-direction: column;
            justify-content: center;
            align-items: flex-end;
        }

        .button-container {
            width: 50px;
            height: 50px;
            border-radius: 50%; /* 타원형 모양 */
            background-color: #3498db;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #fff;
            cursor: pointer;
            margin-top: 10px; /* 버튼의 상하 간격 조절 */
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="button-container">버튼 1</div>
        <div class="button-container">버튼 2</div>
        <div class="button-container">버튼 3</div>
        <div class="button-container">버튼 4</div>
        <div class="button-container">버튼 5</div>
    </div>
</body>
</html>