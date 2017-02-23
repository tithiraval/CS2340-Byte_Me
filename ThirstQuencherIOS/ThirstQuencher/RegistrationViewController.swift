//
//  RegistrationViewController.swift
//  ThirstQuencher
//
//  Created by Wiqas Nassar on 2/20/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import UIKit

class RegistrationViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource{

    @IBOutlet weak var username: UITextField!
    @IBOutlet weak var pass: UITextField!
    @IBOutlet weak var Name: UITextField!
    @IBOutlet weak var confirmPass: UITextField!
    @IBOutlet weak var accountType: UIPickerView!
    
    var accountTypeData: [String] = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        
        self.accountType.delegate = self
        
        self.accountType.dataSource = self
        
        accountTypeData = ["User", "Worker", "Admin", "Manager"]
    }
    
    @IBAction func onRegister(_ sender: UIButton) {
        let user = username.text
        let password = pass.text
        let name = Name.text
        let confirm = confirmPass.text
        let accountType = 
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return accountTypeData.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return accountTypeData[row]
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
