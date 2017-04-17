//
//  LoggedInViewController.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 2/27/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class LoggedInViewController: UIViewController {
    
    @IBOutlet weak var purityReportButton: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let logoutButton: UIBarButtonItem = UIBarButtonItem(title: "Log out", style: .plain, target: self, action: #selector(LoggedInViewController.goToLogin))
        let editUserButton: UIBarButtonItem = UIBarButtonItem(title: "Edit User", style: .plain, target: self, action:
            #selector(LoggedInViewController.editUser))
        
        logoutButton.tintColor = UIColor(red: 121/255, green: 204/255, blue: 255/255, alpha: 1)
        editUserButton.tintColor = UIColor(red: 121/255, green: 204/255, blue: 255/255, alpha: 1)
        
        self.navigationItem.rightBarButtonItem = editUserButton
        self.navigationItem.leftBarButtonItem = logoutButton
        
        Model.sharedInstance.checkIfUSER(fromViewController: self)

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func goToLogin() {
        Model.sharedInstance.logout()
        self.performSegue(withIdentifier: "logOutFromInitial", sender: nil)
    }
    
    func editUser() {
        self.performSegue(withIdentifier: "toEditUser", sender: nil)
    }
    

    @IBAction func unwindToMain(segue: UIStoryboardSegue) {}

}
