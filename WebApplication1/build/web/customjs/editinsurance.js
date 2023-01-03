function load() {

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {

            var response = request.responseText;
//            alert(response);
            loadTable(response);
        }
    }

    request.open("GET", "LoadInsurance", true);
    request.send();
}

function loadTable(resp) {

//    alert(resp);


    var table = document.getElementById("table");
    table.innerHTML = "<thead><tr role='row'><th>ID</th><th>Provider</th><th>Provider Address</th><th>Starting Date</th><th>Finishing Date</th><th>Plan</th><th>Employee Id</th><th>Employee Name</th><th></th></tr></thead>";

    if (resp === "[]") {
        return;
    }

    if (resp === "not found") {
        return;
    }
    var ar = JSON.parse(resp);

    if (ar.length > 0) {
        for (var i in ar) {
            var item = ar[i];
            loadRows(i, item);
        }
    } else {
        loadRows(0, ar);

    }

}

function loadRows(i, item) {

    var d = new Date(item.start);
    var e = new Date(item.end);

    var table = document.getElementById("table");
    table.innerHTML += "<tbody>\n\
<tr>\n\
<td>" + item.id + "</td>\n\
<td><input type='text' value='" + item.provider + "' /></td>\n\
<td><textarea>" + item.providerAddress + "</textarea></td>\n\
<td><input type='date' value='" + getDateString(d) + "' /></td>\n\
<td><input type='date' value='" + getDateString(e) + "' /></td>\n\
<td><input type='text' value='" + item.plan + "' /></td>\n\
<td><input type='text' value='" + item.idEmp + "' /></td>\n\
<td>" + item.empName + "</td>\n\
<td><button class='btn btn-sm dropdown-toggle more-horizontal' type='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><span class='text-muted sr-only'>Action</span></button><div class='dropdown-menu dropdown-menu-right'><a class='dropdown-item' href='#' onclick='edit(" + i + ")' >Edit</a><a class='dropdown-item text-danger' href='#' onclick='remove( " + item.id + " );'>Remove</a></div></td></tr></tbody>";

}

function getDateString(d) {
    return d.getFullYear().toString() + '-' + (d.getMonth() + 1).toString().padStart(2, '0') + '-' + d.getDate().toString().padStart(2, '0');
}

function remove(id) {

    var option = confirm("Do you really need to remove?");

    if (option) {
        var request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            if (request.status === 200 && request.readyState === 4) {

                var response = request.responseText;
                load();
                showSuccessModal(response);
            }
        }

        request.open("GET", "DeleteInsurance?id=" + id, true);
        request.send();
    }
}

function searchById() {
    document.getElementById("search2").value = "";
    document.getElementById("search3").value = "";

    var search = document.getElementById("idValue").value;

    if (search === "") {
        load();
        return;
    }

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {

            var response = request.responseText;

            loadTable(response);

        }
    }

    request.open("GET", "SearchInsuranceById?id=" + search, true);
    request.send();
}

function search_two() {
    document.getElementById("idValue").value = "";
    document.getElementById("search3").value = "";

    var search = document.getElementById("search2").value;

    if (search === "") {
        load();
        return;
    }

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {

            var response = request.responseText;

            loadTable(response);
        }
    }

    request.open("GET", "SearchInsuranceByEmployee?empid=" + search, true);
    request.send();
}

function search_three() {
    document.getElementById("idValue").value = "";
    document.getElementById("search2").value = "";

    var search = document.getElementById("search3").value;

    if (search === "") {
        load();
        return;
    }

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {

            var response = request.responseText;

            loadTable(response);
        }
    }

    request.open("GET", "SearchInsuranceByProvider?provider=" + search, true);
    request.send();
}

function edit(rowNo) {
    var table = document.getElementById("table");
    var row = table.rows[rowNo + 1]; //because of the header row

    var rowCells = row.children;

    var id = rowCells[0].childNodes[0].data;
    var provider = rowCells[1].childNodes[0].value;
    var address = rowCells[2].childNodes[0].value;
    var start = rowCells[3].childNodes[0].value;
    var end = rowCells[4].childNodes[0].value;
    var plan = rowCells[5].childNodes[0].value;
    var empid = rowCells[6].childNodes[0].value;

//    alert(id + name + birthday + nic + email + mobile + address);

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {

            var response = request.responseText;
            showSuccessModal(response);
        }
    }

    request.open("GET", "EditInsurance?id=" + id + "&provider=" + provider + "&address=" + address + "&start=" + start + "&end=" + end + "&plan=" + plan + "&empid=" + empid, true);
    request.send();
}