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
        let text = usernameTextField.text
        let testingUsername = "bob"
        if (testingUsername == text) {
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
