//
//  HistoricalGraphViewControllerViewController.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 4/24/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit
import Charts

class HistoricalGraphViewControllerViewController: UIViewController {
    
    @IBOutlet weak var backButton: UIBarButtonItem!
    @IBOutlet weak var lineChart: LineChartView!
    var months: [String]!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lineChart.chartDescription?.text = ""
        
        lineChart.transform = CGAffineTransform(rotationAngle: CGFloat.init(3.14/2))
        
        let purityReports = Model.sharedInstance.getAllPurityReports()
        let year = Model.sharedInstance.getHistoricalYear()
        let lat = Double(Model.sharedInstance.getHistoricalLat())
        let long = Double(Model.sharedInstance.getHistoricalLong())
        
        let latLow = lat - 1.0
        let longLow = long - 1.0
        let latHigh = lat + 1.0
        let longHigh = long + 1.0
        
        
        
        var virusTotals = Array(repeating: 0.0, count: 12)
        var contaminantTotals = Array(repeating: 0.0, count: 12)
        
        var virusNum = Array(repeating: 0.0, count: 12)
        var contaminantNum = Array(repeating: 0.0, count: 12)
        
        if (purityReports != nil && purityReports.count != 0) {
            for report in purityReports {
                let calendar = Calendar.current
                let reportYear = calendar.component(.year, from: report.getDate())
                if (reportYear == year && report.getLatitude() <= latHigh &&
                    report.getLatitude() >= latLow && report.getLongitude() <= longHigh && report.getLongitude() >= longLow) {
                    let reportMonth = (calendar.component(.month, from: report.getDate())) - 1
                    virusTotals[reportMonth] = virusTotals[reportMonth] + Double(report.getVirusPPM())
                    contaminantTotals[reportMonth] = contaminantTotals[reportMonth] + Double(report.getContaminantPPM())
                    virusNum[reportMonth] = virusNum[reportMonth] + 1.0
                    contaminantNum[reportMonth] = contaminantNum[reportMonth] + 1.0
                }
            }
        }
        
        var virusPPM = Array(repeating: 0.0, count: 12)
        var contaminantPPM = Array(repeating: 0.0, count: 12)
        
        for i in 0..<12 {
            if virusNum[i] != 0 {
                virusPPM[i] = virusTotals[i] / virusNum[i]
            }
            
            if contaminantNum[i] != 0 {
                contaminantPPM[i] = contaminantTotals[i] / contaminantNum[i]
            }
        }
        
        
        months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
        let PPM = [20.0, 4.0, 6.0, 3.0, 12.0, 16.0, 4.0, 18.0, 2.0, 4.0, 5.0, 4.0]
        
        setChart(dataPoints: months, virusPPM: virusPPM, contPPM: contaminantPPM)
        
        // Do any additional setup after loading the view.
    }
    
    func setChart(dataPoints: [String], virusPPM: [Double], contPPM: [Double]) {
        lineChart.noDataText = "You need to provide data for the chart."
        
        var virusDataEntries: [ChartDataEntry] = []
        var contDataEntries: [ChartDataEntry] = []
        
        for i in 0..<dataPoints.count {
            let dataEntry = ChartDataEntry(x: Double(i), y: virusPPM[i])
            virusDataEntries.append(dataEntry)
            let newDataEntry = ChartDataEntry(x: Double(i), y: contPPM[i])
            contDataEntries.append(newDataEntry)
        }
        
        let virusChartDataSet = LineChartDataSet(values: virusDataEntries, label: "Virus PPM")
        let contChartDataSet = LineChartDataSet(values: contDataEntries, label: "Contaminant PPM")
        let chartData = LineChartData(dataSets: [virusChartDataSet, contChartDataSet])
        lineChart.data = chartData
        let xAxis = XAxis()
        lineChart.xAxis.valueFormatter = IndexAxisValueFormatter(values: months)
        lineChart.xAxis.granularity = 1.0
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func onBack(sender: UIBarButtonItem) {
        self.performSegue(withIdentifier: "unwindToWaterReports", sender: nil)
    }
    
    
    /*
     // MARK: - Navigation
     
     // In a storyboard-based application, you will often want to do a little preparation before navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
     // Get the new view controller using segue.destinationViewController.
     // Pass the selected object to the new view controller.
     }
     */
    
}
