// function changeColor() {
//     const element = document.getElementById("demo");
//     element.style.color = "#e74c3c";
//     element.textContent = "Welcome to my home!";
// }
//
// // script.js
// // fetch('/api/time')
// //     .then(response => response.text())
// //     .then(time => {
// //         document.getElementById("time").textContent = `Now is: ${time}`;
// // });
//
// // script.js（动态获取服务器时间）
// // function fetchServerTime() {
// //     fetch('/api/time')
// //         .then(response => response.text())
// //         .then(time => {
// //             document.getElementById("server-time").textContent =
// //                 `轮询: ${time}`;
// //         });
// // }
// // // 页面加载后立即获取一次
// // fetchServerTime();
// // // 每秒轮询一次
// // setInterval(fetchServerTime, 1000);
//
// // 获取sse时间，动态
// const eventSource1 = new EventSource('/sse/time');
//
// eventSource1.onmessage = function(event) {
//     document.getElementById("sse-time").textContent =
//         `${event.data}`;
// };
//
// // 获取sse日期等信息
// const eventSource2 = new EventSource('/sse/time-stream');
//
// eventSource2.onmessage = function (event) {
//     const data = JSON.parse(event.data);
//     updateTimeDisplay(data);
// };
//
// function updateTimeDisplay(data) {
//     document.getElementById('current-date').textContent = data.date;
//     document.getElementById('day-of-week').textContent = data.dayOfWeek;
//     document.getElementById('day-of-year').textContent = data.dayOfYear;
//     document.getElementById('week-of-year').textContent = data.weekOfYear;
//     document.getElementById('festival').textContent = data.festival;
// }