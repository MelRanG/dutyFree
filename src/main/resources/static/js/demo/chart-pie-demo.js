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
colorDataBrandHover = [
  "#97BDA9",
  "#5E6661",
  "#859BA8",
  "#5F829C",
  "#1F304E",
  "#699064",
];

$(function () {
  let labelDataCategory = [];
  let priceDataCategory = [];
  $.ajax({
    url: `http://localhost:8080/admin/sales/category`,
    type: "GET",
    success: function (data) {
      let categoryData = data.categorySales;
      console.log(categoryData);
      categoryData.forEach((dataByCategory) => {
        priceDataCategory.push(dataByCategory.totalSales);
        labelDataCategory.push(dataByCategory.category);
      });
      console.log(priceDataCategory);
      console.log(labelDataCategory);
      var ctx = document.getElementById("myPieChart");
      var myPieChart = new Chart(ctx, {
        type: "doughnut",
        data: {
          labels: labelDataCategory,
          datasets: [
            {
              data: priceDataCategory,
              backgroundColor: colorDataBrand,
              hoverBackgroundColor: ["#97BDA9", "#5E6661"],
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
    },
    error: function () {
      console.error("Error fetching exchange rates.");
    },
  });
});
