//
//  WaterReports.swift
//  ThirstQuencher
//
//  Created by Wiqas Nassar on 3/14/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class WaterReports: UITableViewController {
    
    @IBOutlet weak var toggle: UIBarButtonItem!
    
    private var toggled = false
    
    @IBAction func toggleCredit(_ sender: UIBarButtonItem) {
        toggled = !toggled
        if (toggled) {
            toggle.title = "Show Source Reports"
            self.navigationItem.title = "Water Purity Reports"
        } else {
            toggle.title = "Show Purity Reports"
            self.navigationItem.title = "Water Source Reports"
        }
        tableView.reloadData()
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
        
        self.navigationController?.setToolbarHidden(false, animated: true)
        toggle.title = "Show Purity Reports"
        self.navigationItem.title = "Water Source Reports"
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source
    
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }
 
 

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return toggled ? Model.sharedInstance.getAllSourceReports().count : Model.sharedInstance.getAllSourceReports().count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "RepNum", for: indexPath)

        if (toggled) {
            cell.textLabel?.text = String(Model.sharedInstance.getAllPurityReports()[indexPath.row].getNum())
            
            let source = Model.sharedInstance.getAllPurityReports()[indexPath.row]
            let date = source.getDate()
            cell.detailTextLabel?.text = (DateFormatter.localizedString(from: date, dateStyle: DateFormatter.Style.short, timeStyle: DateFormatter.Style.short));
        } else {
            cell.textLabel?.text = String(Model.sharedInstance.getAllSourceReports()[indexPath.row].getNum())
            
            let source = Model.sharedInstance.getAllSourceReports()[indexPath.row]
            let date = source.getDate()
            cell.detailTextLabel?.text = (DateFormatter.localizedString(from: date, dateStyle: DateFormatter.Style.short, timeStyle: DateFormatter.Style.short));
        }
        

        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        var reportAlert: UIAlertController
        if (toggled) {
            let reportSelected = Model.sharedInstance.getAllPurityReports()[indexPath.row]
            reportAlert = UIAlertController(title: "Purity Report " + String(reportSelected.getNum()), message: "Reported by " + reportSelected.getName() + "\n" + "Located at: " + reportSelected.getLocation() + "\n" + "Water Condition: " + reportSelected.getWaterCondition() + "\n" + "Virus PPM: " + reportSelected.getVirusPPM() + "\n" + "Contaminant PPM: " + reportSelected.getContaminantPPM(), preferredStyle: UIAlertControllerStyle.alert)
            reportAlert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
        } else {
            let reportSelected = Model.sharedInstance.getAllSourceReports()[indexPath.row]
            reportAlert = UIAlertController(title: "Source Report " + String(reportSelected.getNum()), message: "Reported by " + reportSelected.getName() + "\n" + "Located at: " + reportSelected.getLocation() + "\n" + "Water Type: " + reportSelected.getWaterType() + "\n" + "Water Condition: " + reportSelected.getWaterCondition(), preferredStyle: UIAlertControllerStyle.alert)
            reportAlert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
        }
        
        self.present(reportAlert, animated: true, completion: nil)

    }
    

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
