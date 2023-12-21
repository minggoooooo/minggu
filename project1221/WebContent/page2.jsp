<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>2번 페이지</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#selectPathButton").click(function() {
                var selectedPath = "선택된 경로"; // 선택한 경로 값을 변수에 저장

                // 선택한 경로를 1번 페이지로 전달
                window.opener.postMessage(selectedPath, window.origin);
                window.close(); // 2번 페이지 닫기
            });
        });
    </script>
</head>
<body>
    <h1>2번 페이지</h1>
    <button type="button" id="selectPathButton">경로 선택</button>
</body>
</html>
