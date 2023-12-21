// 이메일 주소부분 선택여부에 따라 직접 입력창이 활성화/비활성화 되도록 설정
function email_check() {
	
	if (document.frm.emailList.options[document.frm.emailList.selectedIndex].id == "write") {
		document.frm.email2.readonly = false;
		document.frm.email2.value = "";
		document.frm.email2.focus();
	} else {
		document.frm.email2.readonly = true;
		document.frm.email2.value = document.frm.emailList.options[document.frm.emailList.selectedIndex].value;
	}

}

/*if(document.frm.emailList.options[document.frm.emailList.selectedIndex].value == '0') {
		document.frm.email2.disabled = true;
		document.frm.email2.value = "";
	} 
*/


// 비밀번호 불일치시 form 제출했을 때 비밀번호 재확인 input 아래에 빨간 글씨로 경고메시지 나오게 함
/*function pwDoubleCheck() {
	var form = document.querySelector("form");
	
	if(document.frm.pw.value !== document.frm.pwCheck.value) {
		var errorMsg = document.createElement("p");
		errorMsg.id = "errorMsg";
		errorMsg.style.color = "red";
		errorMsg.textContent = "비밀번호가 맞지 않습니다.";
		form.insertBefore(errorMsg, form.querySelector(".name"));
		return false;
	}
}*/



// 아이디 중복체크창 새로 뜨게 함
function idCheck() {
	var url = "IdCheckServlet?id=" + document.frm.id.value;
	window.open(url, "_blank", "scrollbars=yes, width=400, height=200");
}


// 아이디가 중복되지 않아 사용가능한 경우 자동으로 회원가입창 아이디 input에 입력되도록 함
function idUseOk() {
	window.opener.frm.id.value = document.idCheckForm.id.value;
	window.opener.frm.dupliId.value = document.idCheckForm.id.value;
	window.opener.frm.nickname.value = document.idCheckForm.id.value;
	
	self.close();
}

