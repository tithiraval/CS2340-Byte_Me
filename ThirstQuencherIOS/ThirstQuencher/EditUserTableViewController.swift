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
    
    private var name = Model.sharedInstance.getCurrentUserName()
    private var email = Model.sharedInstance.getCurrentUserEmail()
    private var address = Model.sharedInstance.getCurrentUserAddress()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        nameField.text = Model.sharedInstance.getCurrentUserName()
        emailField.text = Model.sharedInstance.getCurrentUserEmail()
        homeField.text = Model.sharedInstance.getCurrentUserAddress()
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func onCancel(_ sender: UIBarButtonItem) {
        self.performSegue(withIdentifier: "unwindToMainFromEdit", sender: nil)
    }
    
    @IBAction func onAdd(_ sender: UIBarButtonItem) {
        if (name != nameField.text && nameField.text != nil) {
            name = nameField.text!
        }
        if (email != emailField.text && emailField.text != nil) {
            email = emailField.text!
        }
        if (address != homeField.text && homeField.text != nil) {
            address = homeField.text!
        }
        Model.sharedInstance.editUser(name: name, email: email, address: address)
        self.performSegue(withIdentifier: "unwindToMainFromEdit", sender: nil)
    }
    
}
