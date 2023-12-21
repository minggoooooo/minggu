document.addEventListener("DOMContentLoaded", function() {
  const buttons = document.querySelectorAll('.sort');
  let selectedButton = null;

  buttons.forEach(button => {
    button.addEventListener('click', function() {
      if (selectedButton !== this) {
        if (selectedButton) {
          selectedButton.style.backgroundColor = 'white'; 
        }
        selectedButton = this;
        selectedButton.style.backgroundColor = '#55e755'; 
        setTimeout(() => {
          switch (selectedButton.id) {
            case 'home':
              window.location.href = 'crewactivity.jsp'; 
              break;
            case 'board':
              window.location.href = 'table.jsp'; 
              break;
            case 'date':
              window.location.href = 'schedule.jsp'; 
              break;
            case 'photobook':
              window.location.href = 'album.jsp'; 
              break;
            default:
              break;
          }
        }, 2000);
      }
    });
  });
});