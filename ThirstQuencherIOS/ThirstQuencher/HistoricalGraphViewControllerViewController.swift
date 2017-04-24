//
//  HistoricalGraphViewControllerViewController.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 4/24/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class HistoricalGraphViewControllerViewController: UIViewController {
    
    @IBOutlet weak var backButton: UIBarButtonItem!


    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
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
