
var customOverlay;
/* 경로 정보에 관한 내용들이 들어갈 오버레이창 */


var overlayOn = false,	//오버레이를 기본값으로 숨김
	container = document.getElementById('container'),	// 로드뷰 + 지도 컨테이너
	mapWrapper = document.getElementById('mapWrapper'),		//지도 + 버튼 넣을 곳
	mapContainer = document.getElementById('map'),	// 지도만 넣을 곳
	rvContainer = document.getElementById('roadview'),	//로드뷰
	low_container = document.getElementById('low-container'),	//경로 정보들 표시할 곳
	routeList = [],	//현재 거리 기준으로 들어있는 경로들
	currentPos = new kakao.maps.LatLng(firstlat, firstlng),
	routeForDistance = [],
	polylines = [],
	address;
	
	
// 지도의 중앙 정하기
// 맵 센터를 바꿔주고 옵션에 level과 함께 센터를 바꿔줄 생각
var mapCenter = new kakao.maps.LatLng(firstlat, firstlng),
	mapOption = {
		center: mapCenter,
		level: 3
	};

//맵 생성 메소드
var map = new kakao.maps.Map(mapContainer, mapOption);
//로드뷰 생성 메소드
var rv = new kakao.maps.Roadview(rvContainer);
//좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
// 로드뷰는 특정 위치와 가까운 panoid값을 정해줘서 로드뷰를 띄워 줄 수 있음
var rvClient = new kakao.maps.RoadviewClient();



/* 코스 정보 값 저장 위한 fun 오버레이 띄우는거 */
/* 이건 초기값 없을 때 사용*/
function setview(index) {
	/* items 는 jsp 파일에 각 코스의 파일정보들 끌어온 것*/
	let courseArray = items[index];
	// 해당하는 인덱스에 있는 코스 파일 가져오기

	if (!courseArray) {
		console.error("정보없음.");
		return;
	}

	let location = courseArray.location;	//코스명
	let length = courseArray.length;	//길이
	let expect_time = courseArray.expect_time;	//예상시간
	let difficulty = courseArray.difficulty;	//난이도

	//커스텀 오버레이를 누를때마다 초기화 하기 위해 만듬
	if (customOverlay) {
		customOverlay.setMap(null); // 기존 오버레이 제거
		customOverlay = null; // 오버레이 변수 초기화
	}

	//오버레이에 넣을 값
	var content = '<div class="overlaybox">' +
		'    <div class="courseTitle">코스 소개</div>' +
		'    <ul>' +
		'        <li class="up">' +
		'            <span class="number">코스명</span>' +
		'            <span class="title">' + location + '</span>' +
		'        </li>' +
		'        <li>' +
		'            <span class="number">코스길이</span>' +
		'            <span class="title">' + length + '</span>' +
		'        </li>' +
		'        <li>' +
		'            <span class="number">예상 소요 시간</span>' +
		'            <span class="title">' + expect_time + '</span>' +
		'        </li>' +
		'        <li>' +
		'            <span class="number">난이도</span>' +
		'            <span class="title">' + difficulty + '</span>' +
		'        </li>' +
		'    </ul>' +
		'</div>';

	// 경로의 처음에 위치하게끔 point 설정
	var position = values[index][0];
	var [lat, lng] = position.split(',');
	var point = new kakao.maps.LatLng(lat, lng);

	customOverlay = new kakao.maps.CustomOverlay({
		position: point,
		content: content,
		xAnchor: 0.3,
		yAnchor: 0.91
	});

	// 커스텀 오버레이를 지도에 표시합니다
	customOverlay.setMap(map);

	return customOverlay; // 생성된 customOverlay 반환
}


// 경로들 가져오기
function getRoute() {
	//폴리라인 삭제
	routeList= [];
	nullForPolyline();
	routeForDistance= [];
	marker.setMap(map);
	console.log(keys.length);
	console.log(currentPos);
	// 파일의 갯수만큼 반복하는 메소드
	for (var i = 0; i < keys.length; i++) {

		// 1000에 1키로임
		distanceBar = document.getElementById('distanceBar').value * 1000
		let isInRadiusForAnyPoint = false;
		//values 배열
		var coordinateList = values[i];
		// values에 담겨있는 위도 경도를 담기 위한 배열
		var linePath = [];

		var polyline;
		var distance;

		// foreach 구문은 각 요소에 대해 이 함수를 실행한다
		// 즉 values 배열의 첫번쨰 방부터 마지막 방까지 실행이 되게됨.
		coordinateList.forEach(function(coordinate) {
			// values 배열엔 [33.45042, 126.57091] 예를 들고자 쓴 위도 경도
			// 이런식으로 [위도,경도]로 되어있는데 쉼표 기준으로 첫번째 요소를 lat에
			// 두번째 요소를 lng에 넣음
			var [lat, lng] = coordinate.split(',');
			// 저장된 위도 경도 위치를 카카오 맵 api에 맞게 설정
			var point = new kakao.maps.LatLng(lat, lng);
			linePath.push(point);
		});

		for (let k = 0; k < linePath.length; k++) {

			polyline = new kakao.maps.Polyline({
				map: map,
				path: [
					linePath[k],
					currentPos
				],
				strokeWeight: 2,
				strokeColor: '#FF00FF',
				strokeOpacity: 0,
				strokeStyle: 'dashed'
			});

			// 한 점과 중앙 사이의 거리
			distance = Math.round(polyline.getLength());

			if (distance < distanceBar) {
				
				isInRadiusForAnyPoint = true;
				routeForDistance.push(distance/1000);
				break;
			}
		}
		// setview(location, length, expect_time, difficulty, index)

		if (isInRadiusForAnyPoint) {
			// 여러개의 포인트를 연결하여 다각형 , 선을 만들 수 있는 기능
			polyline = new kakao.maps.Polyline({
				path: linePath,
				// 선두께
				strokeWeight: 10,
				strokeColor: '#64fa0a',
				strokeOpacity: 0.9,
				strokeStyle: 'solid'
			});


			// routeList.push([keys[i], polyline]);
			routeList.push(keys[i]);
			polylines[i] = polyline;
			polyline.setMap(null); // 일단 모두 숨김
		}
	}
	 console.log('Updated routeList:', routeList);
}

// 전달받은 좌표(position)에 가까운 로드뷰의 파노라마 ID를 추출하여
// 로드뷰를 설정하는 함수입니다
function toggleRoadview(position) {
	rvClient.getNearestPanoId(position, 500, function(panoId) {
		// 파노라마 ID가 null 이면 로드뷰를 숨깁니다
		if (panoId === null) {
			toggleMapWrapper(true, position);
		} else {
			toggleMapWrapper(false, position);

			// panoId로 로드뷰를 설정합니다
			rv.setPanoId(panoId, position);
		}
	});
}

// 로드뷰에 좌표가 바뀌었을 때 발생하는 이벤트를 등록합니다 
kakao.maps.event.addListener(rv, 'position_changed', function() {

	// 현재 로드뷰의 위치 좌표를 얻어옵니다 
	var rvPosition = rv.getPosition();

	// 지도의 중심을 현재 로드뷰의 위치로 설정합니다
	map.setCenter(rvPosition);

	// 지도 위에 로드뷰 도로 오버레이가 추가된 상태이면
	if (overlayOn) {
		// 마커의 위치를 현재 로드뷰의 위치로 설정합니다
		marker.setPosition(rvPosition);
	}
});

// 마커 이미지를 생성합니다
var markImage = new kakao.maps.MarkerImage(
	'https://t1.daumcdn.net/localimg/localimages/07/2018/pc/roadview_minimap_wk_2018.png',
	new kakao.maps.Size(26, 46),
	{
		// 스프라이트 이미지를 사용합니다.
		// 스프라이트 이미지 전체의 크기를 지정하고
		spriteSize: new kakao.maps.Size(1666, 168),
		// 사용하고 싶은 영역의 좌상단 좌표를 입력합니다.
		// background-position으로 지정하는 값이며 부호는 반대입니다.
		spriteOrigin: new kakao.maps.Point(705, 114),
		offset: new kakao.maps.Point(13, 46)
	}
);

// 드래그가 가능한 마커를 생성합니다
var marker = new kakao.maps.Marker({
	image: markImage,
	position: mapCenter,
	draggable: true
});

// 마커에 dragend 이벤트를 등록합니다
kakao.maps.event.addListener(marker, 'dragend', function(mouseEvent) {

	// 현재 마커가 놓인 자리의 좌표입니다 
	var position = marker.getPosition();

	// 마커가 놓인 위치를 기준으로 로드뷰를 설정합니다
	toggleRoadview(position);
});
toggleOverlay(false);

//지도에 클릭 이벤트를 등록합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

	// 지도 위에 로드뷰 도로 오버레이가 추가된 상태가 아니면 클릭이벤트를 무시합니다 
	if (!overlayOn) {
		return;
	}

	// 클릭한 위치의 좌표입니다 
	var position = mouseEvent.latLng;

	// 마커를 클릭한 위치로 옮깁니다
	marker.setPosition(position);

	// 클락한 위치를 기준으로 로드뷰를 설정합니다
	toggleRoadview(position);
});




// 지도를 감싸고 있는 div의 크기를 조정하는 함수입니다
function toggleMapWrapper(active, position) {
	if (active) {

		// 지도를 감싸고 있는 div의 너비가 100%가 되도록 class를 변경합니다 
		container.className = '';

		// 지도의 크기가 변경되었기 때문에 relayout 함수를 호출합니다
		map.relayout();

		// 지도의 너비가 변경될 때 지도중심을 입력받은 위치(position)로 설정합니다
		map.setCenter(position);
	} else {

		// 지도만 보여지고 있는 상태이면 지도의 너비가 50%가 되도록 class를 변경하여
		// 로드뷰가 함께 표시되게 합니다
		if (container.className.indexOf('view_roadview') === -1) {
			container.className = 'view_roadview';

			// 지도의 크기가 변경되었기 때문에 relayout 함수를 호출합니다
			map.relayout();

			// 지도의 너비가 변경될 때 지도중심을 입력받은 위치(position)로 설정합니다
			map.setCenter(position);
		}
	}
}

// 지도 위의 로드뷰 도로 오버레이를 추가,제거하는 함수입니다
function toggleOverlay(active) {
	if (active) {
		overlayOn = true;

		// 지도 위에 로드뷰 도로 오버레이를 추가합니다
		map.addOverlayMapTypeId(kakao.maps.MapTypeId.ROADVIEW);

		// 지도 위에 마커를 표시합니다
		marker.setMap(map);

		// 로드뷰의 위치를 내가 볼 위치를 중심으로
		toggleRoadview(currentPos);
	} else {
		overlayOn = false;

		// 지도 위의 로드뷰 도로 오버레이를 제거합니다
		map.removeOverlayMapTypeId(kakao.maps.MapTypeId.ROADVIEW);

		// 지도 위에 마커를 지워요~
		marker.setMap(null);

	}
}

// 지도 위의 로드뷰 버튼을 눌렀을 때 호출되는 함수입니다
function setRoadviewRoad() {
	var control = document.getElementById('roadviewControl');

	// 버튼이 눌린 상태가 아니면
	if (control.className.indexOf('active') === -1) {
		control.className = 'active';

		// 로드뷰 도로 오버레이가 보이게 합니다
		toggleOverlay(true);
	} else {
		control.className = '';

		// 로드뷰 도로 오버레이를 제거합니다
		toggleOverlay(false);
	}
}

// 로드뷰에서 X버튼을 눌렀을 때 로드뷰를 지도 뒤로 숨기는 함수입니다
function closeRoadview() {
	var position = marker.getPosition();
	toggleMapWrapper(true, position);
}





// 오버레이 제거 및 HTML 요소 삭제 함수
function removeOverlay() {
	if (customOverlay) {
		customOverlay.setMap(null); // 오버레이 제거
		customOverlay = null; // 오버레이 변수 초기화

		// HTML 요소 삭제
		var overlayElement = document.querySelector('.overlaybox');
		if (overlayElement) {
			overlayElement.remove();
		}
	}
}
// 모든 폴리라인 없애는 메소드
function nullForPolyline() {
	//커스텀 오버레이를 누를때마다 초기화 하기 위해 만듬
	
	if (customOverlay) {
		customOverlay.setMap(null); // 오버레이 제거
		customOverlay = null; // 오버레이 변수 초기화

		// HTML 요소 삭제
		var overlayElement = document.querySelector('.overlaybox');
		if (overlayElement) {
			overlayElement.remove();
		}
	}
}


// 지도에 확대 축소 컨트롤을 생성한다
var zoomControl = new kakao.maps.ZoomControl();

// 지도의 우측에 확대 축소 컨트롤을 추가한다
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

function listclear() {
	var courseListDiv = document.getElementById("courseList");

	// 기존의 자식 요소들을 모두 제거
	while (courseListDiv.firstChild) {
		courseListDiv.removeChild(courseListDiv.firstChild);
	}

}

function getAddress() {

	new daum.Postcode({
		oncomplete: function(data) {
			// 선택한 주소 정보
			address = data.address;

			// 주소 정보를 콘솔에 출력
			console.log('Selected Address:', address);
			
			listclear();
			
			// 카카오 지도에 주소에 대한 마커 표시
			displayMap(address);

		
			// 플레이스홀더 업데이트
			updatePlaceholder(address);
		},
	    onclose: function(state) {
	        //state는 우편번호 찾기 화면이 어떻게 닫혔는지에 대한 상태 변수 이며, 상세 설명은 아래 목록에서 확인하실 수 있습니다.
	        if(state === 'FORCE_CLOSE'){
	            //사용자가 브라우저 닫기 버튼을 통해 팝업창을 닫았을 경우, 실행될 코드를 작성하는 부분입니다.
	
	        } else if(state === 'COMPLETE_CLOSE'){
				 setTimeout(function() {
                   submitForm();
                    // 여기에 추가로 실행할 동작을 작성하세요.
                }, 1500);
			}
	    }
	}).open();
	
}

function updatePlaceholder(address) {
	// "addressInput"이라는 ID를 가진 입력 요소를 찾습니다.
	var addressInput = document.getElementById('addressInput');

	// 플레이스홀더 텍스트를 업데이트합니다.
	addressInput.placeholder = '선택한 주소: ' + address;
}

function displayMap(address) {
	// 주소를 좌표로 변환하는 Geocoder 객체 생성
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소를 좌표로 변환
	geocoder.addressSearch(address, function(result, status) {
		if (status === kakao.maps.services.Status.OK) {
			// 마커의 위치 변경
			currentPos = new daum.maps.LatLng(result[0].y, result[0].x);
			firstlat = result[0].y;
			firstlng = result[0].x;
			marker.setPosition(new daum.maps.LatLng(result[0].y, result[0].x));
			marker.setMap(map);
			
			
			getRoute();
			// 지도의 중심을 마커 위치로 변경
			map.setCenter(new daum.maps.LatLng(result[0].y, result[0].x));
		}
	});
	
	
	
}

// 해당 코스로 중심 잡기
function courseview(index) {
	var position = values[index][0];
	var [lat, lng] = position.split(',');
	var point = new kakao.maps.LatLng(lat, lng);
	currentPos = point;
	map.setCenter(point);
	marker.setPosition(point);
	marker.setMap(map);
	if (overlayOn)
		toggleRoadview(point);
}

// a 태그 클릭 이벤트 처리
function handleATagClick(index) {
	// 모든 폴리라인을 숨김
	for (const polyline of polylines) {
		if(polyline){
    		polyline.setMap(null);
		}
	}
	
	// 클릭된 a 태그의 인덱스에 해당하는 폴리라인을 표시
	polylines[index].setMap(map);
	
}

function view(index){
	handleATagClick(index)
	courseview(index)
}

function submitForm(){
	
	getRoute();
	
	
	if( $('#addressInput').attr('placeholder') === "초기 위치"){
		address = '초기 위치';
	} else {
		address = $('#addressInput').attr('placeholder');
	}
	
	var selectedValue = $('input[name="array"]:checked').val();

	// 선택된 거리 값 가져오기
	var selectedBar = $("#distanceBar").val();

	
	var form = document.getElementById("geolocationForm");
	form.selectedValue.value=selectedValue;
	form.selectedBar.value=selectedBar;
	form.currentlat.value = firstlat;
	form.currentlng.value = firstlng;
	form.address.value = address;
	form.routeList.value = JSON.stringify(routeList);
	form.routeForDistance.value = JSON.stringify(routeForDistance);
	
	form.submit();

}


// id값과 이름값을 넘기기 위한 것
function sendCourse(id, name) {
    var selectedData = {
        id: id,
        name: name
    };

    window.opener.postMessage(selectedData, window.origin);
    window.close();
}