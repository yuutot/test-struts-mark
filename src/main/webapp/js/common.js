function buildGist(dataSet) {

    var labels = [];
    var data = []
    for (var i = 0; i < dataSet.length; i++) {
        labels.push(dataSet[i].date);
        data.push(dataSet[i].count);
    }
    var ctx = document.getElementById("diagram-first").getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                data: data,
                borderWidth: 1
            }]
        },
        options: {
            maintainAspectRatio: false,
            legend: {
                display: false
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
}
function buildTable(dataSet) {
    var employers = [];
    var countries = [];

    for (var i = 0; i < dataSet.length; i++) {
        employers.push(dataSet[i].employer);
        countries.push(dataSet[i].country);
    }

    employers = employers.filter(onlyUnique);
    countries = countries.filter(onlyUnique);

    var tableHead = '', tableBody = '';
    for (var c = 0; c < countries.length; c++) {
        tableHead += '<th scope="col">' + countries[c] + '</th>'
    }
    $('#table-data thead tr').append(tableHead);

    for (var e = 0; e < employers.length; e++) {
        tableBody += '<tr><th scope="row">' + employers[e] + '</th>'
        for (var j = 0; j < countries.length; j++) {
            var isEmpty = true;
            for (i = 0; i < dataSet.length; i++) {
                if (dataSet[i].employer === employers[e] && dataSet[i].country === countries[j]) {
                    tableBody += '<td>' + dataSet[i].total + '</td>'
                    isEmpty = false;
                    break;
                }
            }
            if (isEmpty) {
                tableBody += '<td>0</td>'
            }
        }
        tableBody += '</tr>'
    }
    $('#table-data tbody').append(tableBody);
}

function onlyUnique(value, index, self) {
    return self.indexOf(value) === index;
}