//
//  PurityReportTableViewController.swift
//  ThirstQuencher
//
//  Created by Wiqas Nassar on 3/28/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class PurityReportTableViewController: UITableViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var numberLabel: UILabel!
    @IBOutlet weak var locationTextField: UITextField!
    
    @IBOutlet weak var waterConditionPicker: UIPickerView!
    @IBOutlet weak var waterConditionLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var datePicker: UIDatePicker!
    
    @IBOutlet weak var virusLabel: UILabel!
    @IBOutlet weak var contamLabel: UILabel!
    
    
    var waterConditionData: [String] = [String]()
    
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        datePickerChanged()
        togglePickers(whichPicker: 3)
        
        waterConditionPicker.delegate = self
        waterConditionPicker.dataSource = self
        waterConditionData = ["Safe", "Treatable", "Unsafe"]
        
        nameLabel.text = "Purity Report #" + String(Model.sharedInstance.getNewSourceReportNumber())
        numberLabel.text = "Reported by " + Model.sharedInstance.getCurrentUserName()
        
    }
    
    @IBAction func onAdd(_ sender: UIBarButtonItem) {
        if (Model.sharedInstance.addNewPurityReport(date: datePicker.date, location: locationTextField.text!, waterCondition: waterConditionLabel.text!, virusPPM: virusLabel.text!, contaminantPPM: contamLabel.text!)) {
            self.performSegue(withIdentifier: "unwindToMainFromSource", sender: nil)
        }
    }
    
    @IBAction func onCancel(_ sender: UIBarButtonItem) {
        self.performSegue(withIdentifier: "unwindToMainFromSource", sender: nil)
    }
    
    override var prefersStatusBarHidden: Bool {
        return true
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        
        // Dispose of any resources that can be recreated.
    }
    
    func datePickerChanged () {
        dateLabel.text = DateFormatter.localizedString(from: datePicker.date, dateStyle: DateFormatter.Style.short, timeStyle: DateFormatter.Style.short)
    }
    
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if indexPath.section == 1 && indexPath.row == 0 {
            togglePickers(whichPicker: 1)
        } else if indexPath.section == 2 && indexPath.row == 0 {
            togglePickers(whichPicker: 2)
        } else if indexPath.section == 2 && indexPath.row == 2 {
            togglePickers(whichPicker: 3)
        }
    }
    
    var datePickerHidden = false
    var waterTypePickerHidden = false
    var waterCondPickerHidden = false
    
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if datePickerHidden && indexPath.section == 1 && indexPath.row == 1 {
            return 0
        } else if waterTypePickerHidden && indexPath.section == 2 && indexPath.row == 1 {
            return 0
        } else if waterCondPickerHidden && indexPath.section == 2 && indexPath.row == 3 {
            return 0
        } else {
            return super.tableView(tableView, heightForRowAt: indexPath)
        }
    }
    
    func togglePickers(whichPicker: Int) {
        
        if (whichPicker == 1) {
            datePickerHidden = !datePickerHidden
        } else if (whichPicker == 2) {
            waterCondPickerHidden = !waterCondPickerHidden
        } else {
            datePickerHidden = !datePickerHidden
            waterTypePickerHidden = !waterTypePickerHidden
            waterCondPickerHidden = !waterCondPickerHidden
        }
        
        tableView.beginUpdates()
        tableView.endUpdates()
        
    }
    
    @IBAction func datePickerValue(_ sender: UIDatePicker) {
        datePickerChanged()
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if (pickerView == waterConditionPicker) {
            return waterConditionData.count
        } else {
            return 1
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if (pickerView == waterConditionPicker) {
            return waterConditionData[row]
        } else {
            return "null"
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if (pickerView == waterConditionPicker) {
            waterConditionLabel.text = waterConditionData[row]
        } else {
            
        }
    }
    
}
