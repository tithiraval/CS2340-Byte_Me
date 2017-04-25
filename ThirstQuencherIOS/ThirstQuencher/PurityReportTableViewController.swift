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
    @IBOutlet weak var locationLabel: UILabel!
    
    @IBOutlet weak var waterConditionPicker: UIPickerView!
    @IBOutlet weak var waterConditionLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var datePicker: UIDatePicker!
    
    //@IBOutlet weak var virusLabel: UILabel!
    @IBOutlet weak var virusDetail: UILabel!
    //@IBOutlet weak var contamLabel: UILabel!
    @IBOutlet weak var contamDetail: UILabel!
    
    
    
    
    
    var waterConditionData: [String] = [String]()
    var currentUserName = ""
    
    var didSelectLocation = false
    
    private var lat = 32.10
    private var long = -83.23
    
    private var virusPPM = 0
    private var contaminantPPM = 0
    
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        datePickerChanged()
        togglePickers(whichPicker: 3)
        
        waterConditionPicker.delegate = self
        waterConditionPicker.dataSource = self
        waterConditionData = ["Safe", "Treatable", "Unsafe"]
        
        nameLabel.text = "Purity Report #" + String(Model.sharedInstance.getNewPurityReportNumber())
        Model.sharedInstance.getCurrentUserName(from: self)
        
    }
    
    @IBAction func onAdd(_ sender: UIBarButtonItem) {
        if (didSelectLocation) {
            if (Model.sharedInstance.addNewPurityReport(date: datePicker.date, latitude: lat, longitude: long, waterCondition: waterConditionLabel.text!, virusPPM: virusPPM, contaminantPPM: contaminantPPM, name: currentUserName)) {
                self.performSegue(withIdentifier: "unwindToMainFromSource", sender: nil)
            }
        } else {
            let locationAlert = UIAlertController(title: "Error", message: "Did not select location.", preferredStyle: UIAlertControllerStyle.alert)
            locationAlert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
            self.present(locationAlert, animated: true, completion:nil)
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
        } else if indexPath.section == 3 && indexPath.row == 0 { //virus
            let virusAlert = UIAlertController(title: "Virus PPM", message: "", preferredStyle: UIAlertControllerStyle.alert)
            let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {
                (action) -> Void in
                let virusPPMTextField = virusAlert.textFields![0] as UITextField
                if (virusPPMTextField.text != "") {
                    self.virusDetail.text = virusPPMTextField.text! + " PPM"
                    self.virusPPM = Int(virusPPMTextField.text!)!
                }
            })
            
            virusAlert.addTextField(configurationHandler: {(textField) -> Void in
                    textField.placeholder = "Virus PPM"
                    textField.keyboardType = UIKeyboardType.numberPad
            })
            
            virusAlert.addAction(okAction)
            virusAlert.addAction(UIAlertAction(title: "Cancel", style: UIAlertActionStyle.cancel, handler: nil))
            self.present(virusAlert, animated: true, completion: nil)
        } else if indexPath.section == 3 && indexPath.row == 1 { //contaminant
            let contamAlert = UIAlertController(title: "Contaminant PPM", message: "", preferredStyle: UIAlertControllerStyle.alert)
            let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {
                (action) -> Void in
                let contamPPMTextField = contamAlert.textFields![0] as UITextField
                if (contamPPMTextField.text != "") {
                    self.contamDetail.text = contamPPMTextField.text! + " PPM"
                    self.contaminantPPM = Int(contamPPMTextField.text!)!
                }
            })
            contamAlert.addTextField(configurationHandler: {(textField) -> Void in
                textField.placeholder = "Contaminant PPM"
                textField.keyboardType = UIKeyboardType.numberPad
            })
            contamAlert.addAction(okAction)
            contamAlert.addAction(UIAlertAction(title: "Cancel", style: UIAlertActionStyle.cancel, handler: nil))
            self.present(contamAlert, animated: true, completion: nil)
        }
        if indexPath.section == 1 && indexPath.row == 2 {
            Model.sharedInstance.modifyingReport(which: 1)
            self.performSegue(withIdentifier: "showMapFromPurity", sender: nil)
        }
    }
    
    var datePickerHidden = false
    var waterCondPickerHidden = false
    
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if datePickerHidden && indexPath.section == 1 && indexPath.row == 1 {
            return 0
        } else if waterCondPickerHidden && indexPath.section == 2 && indexPath.row == 1 {
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
    
    @IBAction func unwindToPurityReport(segue: UIStoryboardSegue) {
        lat = Model.sharedInstance.getLat()
        long = Model.sharedInstance.getLong()
        self.locationLabel.text = "Latitude: " + String(format:"%.2f", lat) + ", Longitude: " + String(format:"%.2f", long)
        didSelectLocation = true
    }
    
}
