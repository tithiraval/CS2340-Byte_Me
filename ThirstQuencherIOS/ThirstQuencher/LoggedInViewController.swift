//
//  LoggedInViewController.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 2/27/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class LoggedInViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let logoutButton: UIBarButtonItem = UIBarButtonItem(title: "Log out", style: .plain, target: self, action: #selector(LoggedInViewController.goToLogin))
        
        self.navigationItem.rightBarButtonItem = logoutButton;

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func goToLogin() {
        self.performSegue(withIdentifier: "logOutFromInitial", sender: nil)
    }
    

    @IBAction func unwindToMain(segue: UIStoryboardSegue) {}

}
