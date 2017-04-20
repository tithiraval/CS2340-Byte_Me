//
//  Model.swift
//  ThirstQuencher
//
//  Created by Dhurv Garg on 2/23/17.
//  Copyright © 2017 Hunter. All rights reserved.
//

import Foundation
import UIKit
import Firebase

class Model {
    static let sharedInstance = Model()
    
    private var users = [String: User]()
    private var sourceReports = [SourceReport]()
    private var purityReports = [PurityReport]()
    
    private var currentUser: FIRUser?
    
    private var ref: FIRDatabaseReference!
    
    
    private init() {
        ref = FIRDatabase.database().reference(withPath: "USERS")
    }
    
    func setCurrentUser() {
        currentUser = FIRAuth.auth()?.currentUser
    }
    
    func addUser(fromViewController: UIViewController, name: String, id: String, password: String, accountType: AccountType, emailAddress: String) {
        
        let newUser = User(name: name, id: id, password: password, accountType: accountType, emailAddress: emailAddress)
        
        let registerConfirmed = UIAlertController(title: "Registration confirmed!", message: "Person was registered in the system.", preferredStyle: UIAlertControllerStyle.alert)
        registerConfirmed.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
        registerConfirmed.addAction(UIAlertAction(title: "Sign In", style: UIAlertActionStyle.default, handler: { action in
            fromViewController.performSegue(withIdentifier: "showSignIn", sender: nil)
        }))
        
        FIRAuth.auth()?.createUser(withEmail: emailAddress, password: password) {(user, error) in
            if error != nil {
                var message = ""
                if let errCode = FIRAuthErrorCode(rawValue: error!._code) {
                    
                    switch errCode {
                    case .errorCodeInvalidEmail:
                        message = "The email inputted is invalid"
                    case .errorCodeEmailAlreadyInUse:
                        message = "The email inputted is already in use"
                    case .errorCodeWeakPassword:
                        message = "The password is too weak (shorter than 6 char)"
                    default:
                        print("Create User Error: \(error)")
                    }
                }
                let alert = UIAlertController(title: "Error", message: message, preferredStyle: UIAlertControllerStyle.alert)
                alert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
                fromViewController.present(alert, animated: true, completion: nil)
            } else {
                FIRAuth.auth()?.signIn(withEmail: emailAddress, password: password) {(user, error) in
                    // ...
                }
                let userID = FIRAuth.auth()?.currentUser?.uid
                let userDict = newUser.toDict()
                self.ref.child(userID!).setValue(userDict)
                fromViewController.present(registerConfirmed, animated: true, completion:nil)
            }
        }
    }
    
    func logIn(fromViewController: UIViewController, email: String, password: String) {
        FIRAuth.auth()?.signIn(withEmail: email, password: password) { (user, error) in
            if error != nil {
                var message = ""
                if let errCode = FIRAuthErrorCode(rawValue: error!._code) {
                    
                    switch errCode {
                    case .errorCodeInvalidEmail:
                        message = "The email inputted is invalid"
                    case .errorCodeUserDisabled:
                        message = "The user's account is disabled"
                    case .errorCodeWrongPassword:
                        message = "The password is invalid"
                    case .errorCodeOperationNotAllowed:
                        message = "Email and password accounts are not enabled"
                    default:
                        print("Create User Error: \(error)")
                    }
                }
                let alert = UIAlertController(title: "Error", message: message, preferredStyle: UIAlertControllerStyle.alert)
                alert.addAction(UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil))
                fromViewController.present(alert, animated: true, completion: nil)
            } else {
                self.setCurrentUser()
                fromViewController.performSegue(withIdentifier: "toSuccessfulLogin", sender: nil)
            }
        }
    }
    
    func logout() {
        try! FIRAuth.auth()!.signOut()
        setCurrentUser()
        currentUser = nil
    }
    
    func checkIfUSER(fromViewController: LoggedInViewController) {
        ref.child(currentUser!.uid).observeSingleEvent(of: .value, with: {(snapshot) in
            let value = snapshot.value as? NSDictionary
            let accountType = value?["accountType"] as? String ?? ""
            if (accountType == "USER") {
                fromViewController.purityReportButton.isHidden = true
            }
        })
    }
    
    func editUser(name: String, email: String, address: String) {
        let userID = currentUser!.uid
        ref.child(userID).observeSingleEvent(of: .value, with: {(snapshot) in
            let value = snapshot.value as? NSDictionary
            let accountType = value?["accountType"] as? String ?? ""
            let password = value?["password"] as? String ?? ""
            let title = value?["title"] as? String ?? ""
            let username = value?["username"] as? String ?? ""
            let dict = [
                "accountType": accountType,
                "emailAddress": email,
                "homeAddress": address,
                "password": password,
                "title": title,
                "username": username,
                "Name": name
            ]
            self.ref.child(userID).setValue(dict)
        })
    }
    
    func getCurrentUserName(from: UIViewController) {
        if (from is EditUserTableViewController) {
            ref.child(currentUser!.uid).observeSingleEvent(of: .value, with: {(snapshot) in
                let value = snapshot.value as? NSDictionary
                let name = value?["Name"] as? String ?? ""
                (from as! EditUserTableViewController).nameField.text = name
                (from as! EditUserTableViewController).name = name
            })
        } else if (from is PurityReportTableViewController || from is SourceReportTableViewController) {
            ref.child(currentUser!.uid).observeSingleEvent(of: .value, with: {(snapshot) in
                let value = snapshot.value as? NSDictionary
                let name = value?["Name"] as? String ?? ""
                if (from is PurityReportTableViewController) {
                    (from as! PurityReportTableViewController).numberLabel.text = "Reported by " + name
                } else {
                    (from as! SourceReportTableViewController).numberLabel.text = "Reported by " + name
                }
                
            })
        }
    }
    
    func getCurrentUserEmail(from: UIViewController) {
        if (from is EditUserTableViewController) {
            ref.child(currentUser!.uid).observeSingleEvent(of: .value, with: {(snapshot) in
                let value = snapshot.value as? NSDictionary
                let email = value?["emailAddress"] as? String ?? ""
                (from as! EditUserTableViewController).emailField.text = email
                (from as! EditUserTableViewController).email = email
            })
            
        }
    }
    
    func getCurrentUserAddress(from: UIViewController) {
        if (from is EditUserTableViewController) {
            ref.child(currentUser!.uid).observeSingleEvent(of: .value, with: {(snapshot) in
                let value = snapshot.value as? NSDictionary
                let address = value?["homeAddress"] as? String ?? ""
                (from as! EditUserTableViewController).homeField.text = address
                (from as! EditUserTableViewController).address = address
            })
            
        }
    }
    
    func populateReports() {
        var ref = FIRDatabase.database().reference(withPath: "SOURCE_REPORTS")
        ref.observe(.value, with: {snapshot in
            var newSourceReports = [SourceReport]()
            for item in snapshot.children {
                let sourceReport = snapshot.value as! [String: AnyObject]
                
            }
        })
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    func addNewSourceReport(date: Date, location: String, waterType: String, waterCondition: String) -> Bool {
        let newSourceReport = SourceReport(date: date, number: (sourceReports.count + 1), name: "NAME", location: location, waterType: waterType, waterCondition: waterCondition)
        sourceReports.append(newSourceReport)
        return true
    }
    
    func addNewPurityReport(date: Date, location: String, waterCondition: String, virusPPM: String, contaminantPPM: String) -> Bool {
        let newPurityReport = PurityReport(date: date, number: (purityReports.count + 1), name: "NAME", location: location, waterCondition: waterCondition, virusPPM: virusPPM, contaminantPPM: contaminantPPM)
        purityReports.append(newPurityReport)
        return true
    }
    
    
    func getAllSourceReports() -> [SourceReport] {
        return sourceReports
    }
    
    func getAllPurityReports() -> [PurityReport] {
        return purityReports
    }
    
    
    func getNewSourceReportNumber() -> Int {
        return sourceReports.count + 1
    }
    
    func getNewPurityReportNumber() -> Int {
        return purityReports.count + 1
    }

}
