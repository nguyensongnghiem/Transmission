google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawTransTypeChart);
function drawTransTypeChart() {
  var transTypeChartData = transTypeLabels.map(function(value, index) {
    return [value, transTypeData[index]] ;
  });
  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Loại TD');
  data.addColumn('number', 'Số trạm');
  data.addRows(transTypeChartData)
  var options = {
    pieHole: 0.4,
    legend:'right',
    width: 500,
    height: 340,

  };

  var chart = new google.visualization.PieChart(document.getElementById('transTypeChart'));
  chart.draw(data, options);
}