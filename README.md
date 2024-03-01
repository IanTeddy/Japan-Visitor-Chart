# 📊 JavaFx with bar chart

The goal of this project is to create a graphical user interface with JavaFx that will allow users to read information from a MySQL database and display it as chart data. I chose the data of "Visitor Arrivals & Japanese Overseas Travelers in 2023" from <https://www.jnto.go.jp/statistics/data/20231220_monthly.pdf>


## 🚀 Feature
- Implemented connectivity between a Java application and a MySQL database using local data for the first time in the DatabaseConnector class.
- Utilized two datasets: (1) Visitor Arrivals in Japan and (2) Japanese Overseas Travelers, both containing monthly data throughout the year.
- Incorporated radio buttons to toggle between displaying the two datasets.
- Integrated external CSS to style scenes, including background images and text colors.

## :thought_balloon: Process
Initially, I began with four FXML files and four controller classes, each corresponding to an FXML file. However, after creating separate FXML files for TableView and GraphView, I realized redundancy in my approach. Since both datasets I'm using share the same number of columns and rows, implementing a function to switch displayed data allowed me to streamline the process. As a result, I only required one controller for TableView and one for GraphView. This realization helped me avoid writing duplicate code, ultimately saving time.

## 📚 Learning & Improvement
- Add app icon to window




## 🎥 Video
https://github.com/IanTeddy/Japan-Visitor-Chart/assets/136382277/79588c10-91e2-4cba-ae80-fd3c229d28b9

