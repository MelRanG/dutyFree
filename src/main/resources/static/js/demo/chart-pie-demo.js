// Set new default font family and font color to mimic Bootstrap's default styling
(Chart.defaults.global.defaultFontFamily = "Nunito"),
  '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = "#858796";

// Pie Chart Example

colorDataBrand = [
  "#BBE5CF",
  "#717E75",
  "#CAE1EF",
  "#7BADD2",
  "#324C7C",
  "#89BD82",
];
colotDataBrandHover = [
  "#97BDA9",
  "#5E6661",
  "#859BA8",
  "#5F829C",
  "#1F304E",
  "#699064",
];
var ctx = document.getElementById("myPieChart");
var myPieChart = new Chart(ctx, {
  type: "doughnut",
  data: {
    labels: ["Direct", "Referral", "Social", "a", "b", "c"],
    datasets: [
      {
        data: [100, 100, 100, 100, 100, 100],
        backgroundColor: colorDataBrand,
        hoverBackgroundColor: colotDataBrandHover,
        hoverBorderColor: "rgba(234, 236, 244, 1)",
      },
    ],
  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: "#dddfeb",
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false,
    },
    cutoutPercentage: 80,
  },
});
