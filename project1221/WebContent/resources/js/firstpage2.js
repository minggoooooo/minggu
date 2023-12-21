
var customOverlay;
/* 경로 정보에 관한 내용들이 들어갈 오버레이창 */


var overlayOn = false,	//오버레이를 기본값으로 숨김
	container = document.getElementById('container'),	// 로드뷰 + 지도 컨테이너
	mapWrapper = document.getElementById('mapWrapper'),		//지도 + 버튼 넣을 곳
	mapContainer = document.getElementById('map'),	// 지도만 넣을 곳
	routeList = [],	//현재 거리 기준으로 들어있는 경로들
	currentPos,		//현재 위치 저장용
	currentlat,
	currentlng,
	routeForDistance = [],
	address;

// 지도의 중앙 정하기
// 맵 센터를 바꿔주고 옵션에 level과 함께 센터를 바꿔줄 생각
var mapCenter = new kakao.maps.LatLng(37.566826, 126.978656),
	mapOption = {
		center: mapCenter,
		level: 6
	};

//맵 생성 메소드
var map = new kakao.maps.Map(mapContainer, mapOption);

// 경로들 가져오기
function getRoute() {
	//폴리라인 삭제
	routeList= [];	
	routeForDistance= [];
	// 파일의 갯수만큼 반복하는 메소드
	for (var i = 0; i < keys.length; i++) {

		// 1000에 1키로임
		distanceBar = document.getElementById('distanceBar').value * 1000
		console.log(distanceBar);
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
				strokeColor: 'red',
				strokeOpacity: 0.9,
				strokeStyle: 'solid'
			});


			// routeList.push([keys[i], polyline]);
			routeList.push(keys[i]);
		}
	}
}
// 지오로케이션 
function getCurrentLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var lat = position.coords.latitude;
			var lng = position.coords.longitude;

			// 맵 초기화 및 중심 변경 추가
			currentlat = lat;
			currentlng = lng;
			currentPos = new kakao.maps.LatLng(lat, lng);
			var mapCenter = new kakao.maps.LatLng(lat, lng);
			getRoute();
			map.setCenter(mapCenter);
			submitForm();

		}, function(error) {
			console.error(error);
			alert('현재 위치를 가져올 수 없습니다.');
		});
	} else {
		alert('Geolocation을 지원하지 않습니다.');
	}
}


function submitForm(){
	
	console.log(currentlat);
	console.log(currentlng);
	console.log(currentlng);
	console.log(routeList);
	console.log(routeForDistance);
	
	var form = document.getElementById("geolocationForm");
	form.currentlat.value = currentlat;
	form.currentlng.value = currentlng;
	form.routeList.value = JSON.stringify(routeList);
	form.routeForDistance.value = JSON.stringify(routeForDistance);
	
	form.submit();

}