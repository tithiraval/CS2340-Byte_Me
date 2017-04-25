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
        
        
        months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
        let PPM = [20.0, 4.0, 6.0, 3.0, 12.0, 16.0, 4.0, 18.0, 2.0, 4.0, 5.0, 4.0]
        
        setChart(dataPoints: months, values: PPM)
        
        // Do any additional setup after loading the view.
    }
    
    func setChart(dataPoints: [String], values: [Double]) {
        lineChart.noDataText = "You need to provide data for the chart."
        
        var dataEntries: [BarChartDataEntry] = []
        
        for i in 0..<dataPoints.count {
            let dataEntry = BarChartDataEntry(x: Double(i), y: values[i])
            dataEntries.append(dataEntry)
        }
        
        
        let chartDataSet = BarChartDataSet(values: dataEntries, label: "Units Sold")
        let chartXValues = BarChartDataSet(values: months, label: "Months")
        let chartData = BarChartData(xVals: months, dataSet: chartDataSet)
        lineChart.data = chartData

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
