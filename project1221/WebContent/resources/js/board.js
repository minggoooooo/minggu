document.addEventListener('DOMContentLoaded', function() {
	const locationBar = document.getElementById('location_bar');
	const popup = document.getElementById('popup');
	const addressMenu = document.querySelector('.address-menu');
	const addressTab = document.querySelector('.address-tab');
	const settingsButton = document.querySelector('.address-setting');

	settingsButton.addEventListener('click', function(event) {
		event.preventDefault();
		const selectedAddress = addressMenu.querySelector('button.selected').textContent;
		const selectedTab = addressTab.querySelector('button.selected').textContent;

		// 선택한 주소와 탭 내용을 locationBar에 넣기
		locationBar.value = `${selectedAddress} ${selectedTab}`;
		// 팝업 닫기가 아닌 다른 동작이므로 주석처리
		// popup.style.display = 'none'; 
	});

	function clearSelection(parentElement) {
		const buttons = parentElement.querySelectorAll('button');
		buttons.forEach(button => {
			button.classList.remove('selected');
		});
	}

	addressMenu.addEventListener('click', function(event) {
		if (event.target.tagName === 'BUTTON') {
			clearSelection(addressMenu);
			event.target.classList.add('selected');
		}
	});

	addressTab.addEventListener('click', function(event) {
		if (event.target.tagName === 'BUTTON') {
			clearSelection(addressTab);
			event.target.classList.add('selected');
		}
	});

	// 주소와 탭을 선택할 때마다 팝업 창이 유지되도록 수정
	[addressMenu, addressTab].forEach(element => {
		element.addEventListener('click', function(event) {
			event.stopPropagation(); // 팝업이 닫히지 않도록 함
		});
	});

	locationBar.addEventListener('click', function(event) {
		event.stopPropagation();
		if (popup.style.display === 'none' || popup.style.display === '') {
			popup.style.display = 'block';
		} else {
			popup.style.display = 'none';
		}
	});

	document.addEventListener('click', function(event) {
		if (event.target !== locationBar) {
			popup.style.display = 'none';
		}
	});
});

// 페이지 버튼 클릭 이벤트
document.querySelectorAll('#pagination .page').forEach(item => {
	item.addEventListener('click', function(e) {
		e.preventDefault();
		document.querySelector('#pagination .active').classList.remove('active');
		this.classList.add('active');
		// 여기에 페이지 번호에 따른 데이터 추가
	});
});

// 이전 버튼 클릭 이벤트
document.querySelector('#pagination .prev').addEventListener('click', function(e) {
	e.preventDefault();
	let active = document.querySelector('#pagination .active');
	if (active.previousElementSibling.classList.contains('page')) {
		active.classList.remove('active');
		active.previousElementSibling.classList.add('active');
		// 여기에 페이지 번호에 따른 데이터 로드 코드 추가
	}
});

// 다음 버튼 클릭 이벤트
document.querySelector('#pagination .next').addEventListener('click', function(e) {
	e.preventDefault();
	let active = document.querySelector('#pagination .active');
	if (active.nextElementSibling.classList.contains('page')) {
		active.classList.remove('active');
		active.nextElementSibling.classList.add('active');
		// 여기에 페이지 번호에 따른 데이터 로드 코드를 추가
	}
});

