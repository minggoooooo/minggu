var playlist = [];

function setPlaylist(data) {
   playlist = data;
   // 플레이리스트를 받아와서 player를 초기화하는 함수 호출
   initializePlayer();
}

var currentVideoIndex = 0;
var player;

function onYouTubeIframeAPIReady() {
   // 초기화를 위해 player를 생성하는 코드만 남겨둠
   player = new YT.Player('player', {
      height: '0',
      width: '0',
      videoId: playlist[currentVideoIndex] ,
      playerVars: {
         controls: 0,
      },
      events: {
         'onReady': onPlayerReady,
      },
   });
}

function initializePlayer() {
   // 플레이리스트가 비어있거나 player가 이미 초기화된 경우 반환
   if (playlist.length === 0 || player) {
      return;
   }

   // player를 생성하고 첫 번째 동영상을 재생
   player = new YT.Player('player', {
      height: '0',
      width: '0',
      videoId: playlist[currentVideoIndex],
      playerVars: {
         controls: 0,
      },
      events: {
         'onReady': onPlayerReady,
      },
   });
}

function playVideo() {
   player.playVideo();
}

function stopVideo() {
   player.stopVideo();
}

function toggleVideoTitle(){
	var videoTitleContainer = document.getElementById('videoTitleContainer');
    
    // 현재 표시 여부 확인 후 토글
    if (videoTitleContainer.style.display === 'none') {
        videoTitleContainer.style.display = 'block';
    } else {
        videoTitleContainer.style.display = 'none';
    }
}

function onPlayerReady(event) {
   // 동영상 로딩이 완료되면 사용자 정의 이미지 버튼을 활성화
   document.getElementById('playButton').style.display = 'block';
   document.getElementById('stopButton').style.display = 'block';
}