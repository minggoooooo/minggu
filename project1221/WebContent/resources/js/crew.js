var result = false;
function crewNameCheck() {
   // 입력값
   var crewName = document.getElementById('crewName').value;
   
   // 모든 단기크루명 가져오기
   var existingCrews = document.getElementById('existingCrews').innerHTML;
   var existingCrew = existingCrews.split(' ');      // 배열로 단기 크루이름들이 들어감
	console.log(existingCrews);

   // 모든 장기크루명 가져오기
   var existingLongCrews = document.getElementById('existingLongCrews').innerHTML;
   var existingLongCrew = existingLongCrews.split(' ');      // 배열로 장기크루 이름들이 들어감
	console.log(existingLongCrews);

   // 경고박스
   var warningMessageContainer = document.getElementById('warningMessageContainer');

   // 기존 경고 메시지 삭제
   warningMessageContainer.innerHTML = "";


   // 입력한 크루명과 기존 단기크루명들 비교
   for (var i = 0; i < existingCrew.length - 1; i++) {
      if (existingCrew[i] == crewName) {
         var warningMessage = document.createElement('p');
         warningMessage.style.color = 'red';
         warningMessage.textContent = "이미 존재하는 크루명입니다.";
         warningMessageContainer.appendChild(warningMessage);
         result = false;
         return;
      }
   }

   // 입력한 크루명과 기존 장기크루명들 비교
   for (var i = 0; i < existingLongCrew.length - 1; i++) {
      if (existingLongCrew[i] == crewName) {
         var warningMessage = document.createElement('p');
         warningMessage.style.color = 'red';
         warningMessage.textContent = "이미 존재하는 크루명입니다.";
         warningMessageContainer.appendChild(warningMessage);
         result = false;
         return;

      }
   }

   result = true;
}
function isResult() {
   return result;
}