// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Pie Chart Example
var ctx = document.getElementById("transPieChart");
var myPieChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: transTypeLabels,
        datasets: [{
            data: transTypeData,
            backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745', '#2889A7FF'],
        }],
    },
    options: {
        tooltips: {
            enabled: true
        },
        plugins: {
            datalabels: {
                formatter: (value, ctx) => {
                    let sum = 0;
                    let dataArr = ctx.chart.data.datasets[0].data;
                    console.log(dataArr)
                    dataArr.map(data => {
                        sum += data;
                    });
                    let percentage = (value*100 / sum).toFixed(2)+"%";
                    return percentage;
                },
                color: '#fff',
            }
        }
    }
});
