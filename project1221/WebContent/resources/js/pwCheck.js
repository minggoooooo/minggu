// 비밀번호 유효성 검사
// 8자이상, 특수문자+숫자+영문자 조합, 같은 문자 4번 이상 x, 띄어쓰기 x

 document.querySelector('form').onsubmit = function pwCheck() {
	var pw = document.getElementById('pw').value;
	
	if(document.getElementById('pwCheck'))
	var pwCheck = document.getElementById('pwCheck').value;
	
	
	if(pw.length < 8) {
		alert("비밀번호는 8자 이상으로 작성해주세요!");
		document.getElementById('pw').focus();
		return false;
	}
	
	let reg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,}$/
	if(!reg.test(pw)) {
		alert("비밀번호는 8자 이상, 숫자, 대문자, 특수문자를 모두 포함해야 합니다");
		document.getElementById('pw').focus();
		return false;
	}            
	
	if(/(\w)\1\1\1/.test(pw)) {
		alert("같은 문자를 4번 이상 연속해서 사용하실 수 없습니다");
		document.getElementById('pw').focus();
		return false;
	}

	if(pwCheck == "") {
		alert("비밀번호를 확인해주세요");
		document.getElementById('pwCheck').focus();
		return false;
	}
	
	if(pw != pwCheck) {
		alert("비밀번호를 다시 확인해주세요");
		document.getElementById('pw').focus();
		return false;
	}
	
	return true;
}