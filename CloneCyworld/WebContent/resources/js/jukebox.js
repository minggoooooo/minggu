
document.addEventListener('DOMContentLoaded', function() {
    const selectAllCheckbox = document.getElementById('selectAllCheckbox');
    const subCheckboxes = document.querySelectorAll('.subCheckbox');
	const selectedValues = [];
	
    selectAllCheckbox.addEventListener('change', function() {
        const isChecked = selectAllCheckbox.checked;

        subCheckboxes.forEach(function(subCheckbox) {
            subCheckbox.checked = isChecked;
        });
    });

    subCheckboxes.forEach(function(subCheckbox) {
        subCheckbox.addEventListener('change', function() {
            if (!subCheckbox.checked) {
                selectAllCheckbox.checked = false;
            }
        });
    });


});
function validateForm() {
        var checkboxes = document.getElementsByName('checkbox');
        var isChecked = false;

        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked) {
                isChecked = true;
                break;
            }
        }

        if (!isChecked) {
            alert('최소 하나 이상의 체크박스를 선택하세요.');
            return false;
        }

        return true;
    }
	
	function addList(){
		if(validateForm())
		 document.musicfrm.submit();
	}

    function addMusic() {
        var url = "addMusic.jsp?owner_id=" + document.musicfrm.owner_id.value;
        window.open(url, "_blank", "scrollbars=yes, width=450, height=200");
    }

    function musicOk() {
        document.getElementById('musicForm').submit();
    }

	