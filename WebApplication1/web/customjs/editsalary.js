function load() {

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {

            var response = request.responseText;
//            alert(response);
            loadTable(response);
        }
    }

    request.open("GET", "LoadSalary", true);
    request.send();
}

function loadTable(resp) {

//    alert(resp);


    var table = document.getElementById("table");
    table.innerHTML = "<thead><tr role='row'><th>ID</th><th>Month</th><th>Salary (Rs.)</th><th>Status</th><th>Employee id</th><th>Employee Name</th><th></th></tr></thead>";

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

    var d = new Date(item.month);

    var table = document.getElementById("table");
    table.innerHTML += "<tbody>\n\
<tr>\n\
<td>" + item.idSalary + "</td>\n\
<td><input type='month' value='" + getDateString(d) + "' /></td>\n\
<td><input type='text' value='" + item.salary + "' /></td>\n\
<td><input type='text' value='" + item.status + "' /></td>\n\
<td><input type='number' value='" + item.idEmp + "' /></td>\n\
<td>" + item.empName + "</td>\n\
<td><button class='btn btn-sm dropdown-toggle more-horizontal' type='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'><span class='text-muted sr-only'>Action</span></button><div class='dropdown-menu dropdown-menu-right'><a class='dropdown-item' href='#' onclick='edit(" + i + ")' >Edit</a><a class='dropdown-item text-danger' href='#' onclick='remove( " + item.idSalary + " );'>Remove</a></div></td></tr></tbody>";

}

function getDateString(d) {
    return d.getFullYear().toString() + '-' + (d.getMonth() + 1).toString().padStart(2, '0');
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

        request.open("GET", "DeleteSalary?idsalary=" + id, true);
        request.send();
    }
}

function searchById() {
    document.getElementById("search2").value = "";

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

    request.open("GET", "SearchSalaryById?idsalary=" + search, true);
    request.send();
}

function search_two() {
    document.getElementById("idValue").value = "";

    var search = document.getElementById("search2").value;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.status === 200 && request.readyState === 4) {

            var response = request.responseText;

            loadTable(response);
        }
    }

    request.open("GET", "SearchSalaryByMonth?month=" + search, true);
    request.send();
}

function edit(rowNo) {
    var table = document.getElementById("table");
    var row = table.rows[rowNo + 1]; //because of the header row

    var rowCells = row.children;

    var idsalary = rowCells[0].childNodes[0].data;
    var month = rowCells[1].childNodes[0].value;
    var salary = rowCells[2].childNodes[0].value;
    var status = rowCells[3].childNodes[0].value;
    var emp = rowCells[4].childNodes[0].value;

//    alert(id + name + birthday + nic + email + mobile + address);

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.status == 200 && request.readyState == 4) {

            var response = request.responseText;
            showSuccessModal(response);
        }
    }

    request.open("GET", "EditSalary?idsalary=" + idsalary + "&month=" + month + "&salary=" + salary + "&status=" + status + "&emp=" + emp, true);
    request.send();
}