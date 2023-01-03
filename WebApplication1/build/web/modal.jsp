<div class="modal fade" id="defaultModal" tabindex="-1" role="dialog" aria-labelledby="defaultModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content" id="modalContent">
            <div class="modal-header">
                <h5 class="modal-title" id="defaultModalLabel">Error</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" id="modalBody"> </div>
            <div class="modal-footer">
                <button type="button" class="btn mb-2 btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<button type="button" class="btn mb-2 btn-primary" data-toggle="modal" data-target="#defaultModal" id="modalTrigger" style="display: none;"> Launch demo modal </button>

<script type="text/javascript">

    function showErrorModal(msg) {
        document.getElementById("defaultModalLabel").innerHTML = "Error";
        document.getElementById("modalBody").innerHTML = msg;
        document.getElementById("modalContent").style.border = "2px red solid";
        showModal();
    }

    function showSuccessModal(msg) {
        document.getElementById("defaultModalLabel").innerHTML = "Success";
        document.getElementById("modalBody").innerHTML = msg;
        document.getElementById("modalContent").style.border = "2px blue solid";
        showModal();
    }

    function showModal() {
        document.getElementById("modalTrigger").click();
    }
</script>