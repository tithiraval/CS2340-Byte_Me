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

        // Do any additional setup after loading the view.
    
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // When Login Button is Pressed
    @IBAction func attemptLogin(_ sender: UIButton) {
        let userAlert = UIAlertController(title: "Username Error", message: "The username inputted does not exist.", preferredStyle: UIAlertControllerStyle.alert)
        userAlert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
        let passAlert = UIAlertController(title: "Password Error", message: "Password does not match the user.", preferredStyle: UIAlertControllerStyle.alert)
        passAlert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
        let username = usernameTextField.text
        let password = passTextField.text
        let testingUsername = ["user1", "user2", "user3"]
        let testingPassword = ["pass1", "pass2", "pass3"]
        var userExists = false
        if (!(username == "")) {
            userExists = testingUsername.contains(username!)
        }
        
        if (!userExists) {
            self.present(userAlert, animated: true, completion: nil)
        } else if (!(testingPassword[testingUsername.index(of: username!)!] == password!)) {
            self.present(passAlert, animated: true, completion: nil)
        } else {
            self.performSegue(withIdentifier: "toSuccessfulLogin", sender: nil)
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
