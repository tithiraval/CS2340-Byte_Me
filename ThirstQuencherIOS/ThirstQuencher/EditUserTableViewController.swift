//
//  EditUserTableViewController.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 3/8/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class EditUserTableViewController: UITableViewController {
    @IBOutlet weak var nameField: UITextField!
    @IBOutlet weak var emailField: UITextField!
    @IBOutlet weak var homeField: UITextField!
    
    var name: String?
    var email: String?
    var address: String?

    override func viewDidLoad() {
        super.viewDidLoad()
        
        Model.sharedInstance.getCurrentUserName(from: self)
        Model.sharedInstance.getCurrentUserEmail(from: self)
        Model.sharedInstance.getCurrentUserAddress(from: self)
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func onCancel(_ sender: UIBarButtonItem) {
        self.performSegue(withIdentifier: "unwindToMainFromEdit", sender: nil)
    }
    
    @IBAction func onAdd(_ sender: UIBarButtonItem) {
        if (name != nameField.text && nameField.text != "") {
            name = nameField.text!
        }
        if (email != emailField.text && emailField.text != "") {
            email = emailField.text!
        }
        if (address != homeField.text && homeField.text != "") {
            address = homeField.text!
        }
        Model.sharedInstance.editUser(name: name!, email: email!, address: address!)
        self.performSegue(withIdentifier: "unwindToMainFromEdit", sender: nil)
    }
    
}
