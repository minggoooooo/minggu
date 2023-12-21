let button1 = document.getElementById("1");
let button2 = document.getElementById("2");
let button3 = document.getElementById("3");

button1.addEventListener('click', function() { // 사이트 소개 클릭했을 때
	window.location.href = "introduce.jsp";
});

button2.addEventListener('click', function() { // 코스 찾기 클릭했을 때
	window.location.href = "firstpage.jsp";
});

button3.addEventListener('click', function() { // 마이 페이지 클릭했을 때
	window.location.href = "MyPage.jsp";
});



//Get the contentsWrapper element
const contentWrapper = document.getElementById('contentWrapper ');

// Add an event listener to prevent the dropdown-content from hiding when hovering over contentsWrapper
contentWrapper.addEventListener('mouseover', function(event) {
	// Check if the event target is a dropdown-content element
	const isDropdownContent = event.target.classList.contains('dropdown-content');

	// If the mouse is over the dropdown-content, do nothing (prevent hiding)
	if (isDropdownContent) {
		event.stopPropagation(); // Prevent the event from reaching the contentsWrapper
	}
});



function performLogin() {
	// 로그인 수행 로직, 서버와의 상호작용 등을 수행합니다.
	// 성공적인 로그인이라고 가정하고 loggedIn을 true로 설정합니다.
	var loggedIn = true; // 서버로부터의 로그인 성공 응답을 기반으로 설정하세요.

	if (loggedIn) {
		var loginLink = document.querySelector('.top-login a');
		loginLink.textContent = "로그아웃";
		loginLink.href = "logout.servlet"; // 로그아웃 서블릿에 대한 링크

		// "로그아웃"을 클릭한 후 로그아웃 로직
		loginLink.onclick = function(event) {
			event.preventDefault(); // 기본 링크 동작 방지

			// AJAX나 폼 제출을 통해 로그아웃을 수행합니다.
			fetch("logout.servlet", {
				method: 'POST' // 설정에 따라 GET이나 다른 메소드를 사용할 수 있습니다.
				// 필요한 경우 다른 헤더나 바디 내용을 추가하세요.
			})
				.then(function(response) {
					if (response.ok) {
						alert("로그아웃되었습니다.");
						window.location.href = "main.jsp"; // 로그아웃 후 메인 페이지로 리디렉션
					} else {
						alert("로그아웃에 실패했습니다.");
					}
				})
				.catch(function(error) {
					console.error('로그아웃 중 오류 발생:', error);
				});
		};
	}
}




