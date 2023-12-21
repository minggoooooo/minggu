function updateCount(no, id) {

   console.log(no);

   $.ajax({
      url: "RecommendServlet",
      type: "post",
      data: {
         "no": no,
         "id": id
      },
      success: function(recommend) {
         if (recommend === "error") {
            alert("오류 발생.");
         } else if (recommend.includes("already")) {
            var count = recommend.replace("already", "");
            // 좋아요 버튼의 색상을 검정색으로 변경하는 코드를 여기에 추가하십시오.
            $(".i_alike").removeClass("i_alike").addClass("i_like");
            $("#board.text").text(count);

         } else {
            $(".i_like").removeClass("i_like").addClass("i_alike");
            $("#board.text").text(recommend);

         }
      }
   });
   return false; // a태그라 상단 이동 방지 위해서

}

function noneMem() {
   alert("크루 멤버가 아닙니다.");
}
recommend.js