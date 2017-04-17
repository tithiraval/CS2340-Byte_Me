//
//  SignInViewController.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 2/15/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class SignInViewController: UIViewController {

    @IBOutlet weak var usernameTextField: UITextField!
    @IBOutlet weak var passTextField: UITextField!
    @IBOutlet weak var loginButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        guard let stack = self.navigationController?.viewControllers else { return }
        let welcome = stack.first!
        self.navigationController?.viewControllers = [welcome, self]

        // Do any additional setup after loading the view.
    
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // When Login Button is Pressed
    @IBAction func attemptLogin(_ sender: UIButton) {
        let nullAlert = UIAlertController(title: "Error", message: "One of the fields is empty.", preferredStyle: UIAlertControllerStyle.alert)
        nullAlert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
        
        let username = usernameTextField.text
        let password = passTextField.text
        
        var wasAlertPresented = false
        
        if (username == "" || password == "") {
            wasAlertPresented = true
            self.present(nullAlert, animated: true, completion:nil)
        }
        
        if (!wasAlertPresented) {
            Model.sharedInstance.logIn(fromViewController: self, email: username!, password: password!)
        }
        
        
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
